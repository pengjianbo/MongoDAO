package cn.finalteam.mongodao;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/6/24 上午12:09
 */
public class MongoDao<C extends BasicMongoRepo> extends AbstractMongoDao<C> {

    public final MongoCollection<Document> mongoCollection;

    public MongoDao(MongoConfig mongoConfig){
        super(mongoConfig);
        this.entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
        String collectionName = entityClass.getSimpleName();
        MongoClient mongoClient = new MongoClient(mongoConfig.getServerAddressList(), mongoConfig.getMongoClientOptions());
        MongoDatabase database = mongoClient.getDatabase(mongoConfig.getDatabaseName());
        this.mongoCollection = database.getCollection(collectionName);
    }

    @Override
    public void insertMany(List<C> list){
        List<Document> documentList = new ArrayList<Document>();
        for(C c:list) {
            long time = System.currentTimeMillis();
            c.setCreateTime(time);
            c.setUpdateTime(time);
            try {
                Document document = BeanUtils.toDocument(c);
                documentList.add(document);
            }catch (Throwable throwable){
                throw new RuntimeException(String.format("insertMany 对象转Document异常 exception=%s", CommonUtils.getStackTrace(throwable)));
            }
        }

        mongoCollection.insertMany(documentList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insertOne(C c){
        long time = System.currentTimeMillis();
        c.setCreateTime(time);
        c.setUpdateTime(time);
        try {
            Document document = BeanUtils.toDocument(c);
            mongoCollection.insertOne(document);
        }catch (Throwable throwable){
            throw new RuntimeException(String.format("insertOne 对象转Document异常 exception=%s", CommonUtils.getStackTrace(throwable)));
        }
    }

    @Override
    public C findById(String id){
        try {
            ObjectId _idobj = new ObjectId(id);
            Document document = mongoCollection.find(Filters.eq("_id", _idobj)).first();
            C c = BeanUtils.toBean(document, entityClass);
            return c;
        }catch (Throwable throwable){
            throw new RuntimeException(String.format("findById 出现异常 exception=%s", CommonUtils.getStackTrace(throwable)));
        }
    }

    @Override
    public List<C> find(){
        FindIterable<Document> iterable = mongoCollection.find();
        return getListByCursor(iterable);
    }

    @Override
    public List<C> find(Bson filter){
        FindIterable<Document> iterable = mongoCollection.find(filter);
        return getListByCursor(iterable);
    }

    @Override
    public C findOne(Bson filter){
        try {
            FindIterable<Document> iterable = mongoCollection.find(filter);
            MongoCursor<Document> cursor = iterable.iterator();
            if (cursor.hasNext()) {
                Document document = cursor.next();
                return BeanUtils.toBean(document, entityClass);
            }
        }catch (Throwable throwable){
            throw new RuntimeException(String.format("findOne 出现异常 exception=%s", CommonUtils.getStackTrace(throwable)));
        }

        return null;
    }

    @Override
    public List<C> findPage(int pageNo, int pageSize){
        return findPage(null, pageNo, pageSize);
    }

    @Override
    public List<C> findPage(Bson filter, int pageNo, int pageSize){
        return findPage(filter, pageNo, pageSize, null);
    }

    @Override
    public List<C> findPage(int pageNo, int pageSize, Bson sort){
        return findPage(null, pageNo, pageSize, sort);
    }

    /**
     * 分页查找
     * @param filter 查找条件
     * @param pageNo 分页号[1-无穷大)
     * @param pageSize 分页大小
     * @param sort 排序字段
     * @return
     */
    @Override
    public List<C> findPage(Bson filter, int pageNo, int pageSize, Bson sort){
        int skip = (pageNo - 1) * pageSize;
        FindIterable<Document> iterable;
        if(filter != null) {
            iterable = mongoCollection.find(filter).skip(skip).limit(pageSize);
        } else {
            iterable = mongoCollection.find().skip(skip).limit(pageSize);
        }
        if(sort != null){
            iterable = iterable.sort(sort);
        }
        return getListByCursor(iterable);
    }

    private List<C> getListByCursor(FindIterable<Document> iterable){
        try {
            MongoCursor<Document> cursor = iterable.iterator();
            if (cursor.hasNext()) {
                List<C> list = new ArrayList<C>();
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    DBObject b = (DBObject)com.mongodb.util.JSON.parse(document.toJson());
                    list.add(BeanUtils.toBean(document, entityClass));
                }
                return list;
            }
        }catch (Throwable throwable){
            throw new RuntimeException(String.format("getListByCursor 出现异常 exception=%s", CommonUtils.getStackTrace(throwable)));
        }

        return null;
    }

    /**
     * 更新多条记录
     * @param filter 更新条件<code>com.mongodb.client.model.Filters</code>
     * @param updateDoc 需要更新内容key-value字典
     * @return
     */
    @Override
    public long updateMany(Bson filter, Document updateDoc) {
        updateDoc.put("updateTime", System.currentTimeMillis());
        UpdateResult result = mongoCollection.updateMany(filter, new Document("$set", updateDoc));
        return result.getMatchedCount();
    }

    /**
     * 更新多条记录
     * @param filter 更新条件
     * @param c 更新的对象（对象中有包含int, long,boolean等基本数据类型且未设置字段值的会覆盖成默认值，装箱的基本数据类型除外）
     * @return
     */
    @Override
    public long updateMany(Bson filter, C c){
        try {
            c.setUpdateTime(System.currentTimeMillis());
            Document updateDoc = BeanUtils.toDocument(c);
            UpdateResult result = mongoCollection.updateMany(filter, new Document("$set", updateDoc));
            return result.getMatchedCount();
        }catch (Throwable throwable){
            throw new RuntimeException(String.format("updateMany 出现异常 exception=%s" , CommonUtils.getStackTrace(throwable)));
        }
    }

    /**
     * 更新单条记录
     * @param filter 更新条件
     * @param updateDoc 需要更新内容key-value字典
     * @return
     */
    @Override
    public long updateOne(Bson filter, Document updateDoc) {
        updateDoc.put("updateTime", System.currentTimeMillis());
        UpdateResult result = mongoCollection.updateOne(filter, new Document("$set", updateDoc));
        return result.getMatchedCount();
    }

    @Override
    public long updateOne(Bson filter, C c){
        try {
            c.setUpdateTime(System.currentTimeMillis());
            Document updateDoc = BeanUtils.toDocument(c);
            UpdateResult result = mongoCollection.updateOne(filter, new Document("$set", updateDoc));
            return result.getMatchedCount();
        }catch (Throwable throwable){
            throw new RuntimeException(String.format("updateOne 出现异常 exception=%s", CommonUtils.getStackTrace(throwable)));
        }
    }

    @Override
    public long updateOne(C c){
        Bson bson = Filters.eq("_id", c.getId());
        long u = updateOne(bson, c);
        return u;
    }

    @Override
    public long upateMany(Bson filter, List<C> updateList){
        long count = 0;
        try {
            for (C c : updateList) {
                Document updateDoc = BeanUtils.toDocument(c);
                UpdateResult result = mongoCollection.updateMany(filter, new Document("$set", updateDoc));
                long u = result.getMatchedCount();
                if (u > 0) {
                    count++;
                }
            }
        }catch (Throwable throwable){
            throw new RuntimeException(String.format("upateMany 出现异常 exception=%s", CommonUtils.getStackTrace(throwable)));
        }
        return count;
    }

    @Override
    public long upateMany(List<C> updateList){
        long count = 0;
        for(C c:updateList) {
            long u = updateOne(c);
            if(u > 0){
                count ++;
            }
        }
        return count;
    }

    @Override
    public long delete(C c){
        try {
            Document deleteDoc = BeanUtils.toDocument(c);
            DeleteResult result = mongoCollection.deleteOne(deleteDoc);
            return result.getDeletedCount();
        }catch (Throwable throwable){
            throw new RuntimeException(String.format("delete 出现异常 exception=%s", CommonUtils.getStackTrace(throwable)));
        }
    }

    @Override
    public long delete(Bson filter){
        DeleteResult result = mongoCollection.deleteOne(filter);
        return result.getDeletedCount();
    }

    @Override
    public long deleteMany(List<C> deleteList){
        long count = 0;
        for (C c:deleteList){
            long d = delete(c);
            if(d > 0){
                count++;
            }
        }
        return count;
    }

    @Override
    public long deleteMany(Bson filter) {
        DeleteResult deleteResult = mongoCollection.deleteMany(filter);
        return deleteResult.getDeletedCount();
    }

    @Override
    public long count() {
        return mongoCollection.count();
    }

    @Override
    public long count(Bson filter) {
        return mongoCollection.count(filter);
    }
}
