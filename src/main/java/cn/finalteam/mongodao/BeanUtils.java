package cn.finalteam.mongodao;

import com.alibaba.fastjson.JSON;
import com.mongodb.DBObject;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/7/9 下午3:01
 */
public class BeanUtils {

    public static <C> Document toDocument(C c){
        String jsonString = JSON.toJSONString(c);
        Document document = Document.parse(jsonString);
        return document;
    }

    public static <C extends BasicMongoRepo> C toBean(Document document, Class<C> entityClass){
        DBObject b = (DBObject)com.mongodb.util.JSON.parse(document.toJson());
        String jsonString = com.alibaba.fastjson.JSON.toJSONString(b);
        C c = JSON.parseObject(jsonString, entityClass);
        ObjectId id = document.getObjectId("_id");
        c.setId(id.toString());
        return c;
    }

}
