package cn.finalteam.mongodao;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/6/24 上午11:20
 */
public abstract class AbstractMongoDao<C> {

    protected MongoConfig mongoConfig;

    protected Class<C> entityClass;

    public AbstractMongoDao(MongoConfig mongoConfig){
        this.mongoConfig = mongoConfig;
    }

    public abstract void insertMany(List<C> list);

    public abstract void insertOne(C c);

    public abstract List<C> find();

    public abstract C findById(String id);

    public abstract List<C> find(Bson filter);

    public abstract C findOne(Bson filter);

    public abstract List<C> findPage(int pageNo, int pageSize);

    public abstract List<C> findPage(Bson filter, int pageNo, int pageSize);

    public abstract List<C> findPage(int pageNo, int pageSize, Bson sort);

    public abstract List<C> findPage(Bson filter, int pageNo, int pageSize, Bson sort);

    public abstract long updateMany(Bson filter, Document updateDoc);

    public abstract long updateMany(Bson filter, C c);

    public abstract long updateOne(Bson filter, Document updateDoc);

    public abstract long updateOne(Bson filter, C c);

    public abstract long updateOne(C c);

    public abstract long upateMany(Bson filter, List<C> updateList);

    public abstract long upateMany(List<C> updateList);

    public abstract long delete(C c);

    public abstract long delete(Bson filter);

    public abstract long deleteMany(List<C> deleteList);

    public abstract long deleteMany(Bson filter);

    public abstract long count();

    public abstract long count(Bson filter);

}
