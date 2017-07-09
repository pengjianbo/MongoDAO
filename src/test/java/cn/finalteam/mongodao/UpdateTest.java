package cn.finalteam.mongodao;

import cn.finalteam.mongodao.dao.TeacherDao;
import cn.finalteam.mongodao.model.Teacher;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/7/9 下午7:05
 */
public class UpdateTest extends BasicTest {

    TeacherDao teacherDao;

    @Before
    public void init(){
        teacherDao = new TeacherDao(mongoConfig);
    }

    @Test
    public void updateTest(){
        Bson filter = Filters.eq("name", "马老师");
        Document document = new Document();
        document.put("isMan", false);
        long l = teacherDao.updateOne(filter, document);
        System.out.println(l);
    }
}
