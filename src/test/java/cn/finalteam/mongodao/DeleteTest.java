package cn.finalteam.mongodao;

import cn.finalteam.mongodao.dao.TeacherDao;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/7/9 下午7:11
 */
public class DeleteTest extends BasicTest {
    TeacherDao teacherDao;

    @Before
    public void init(){
        teacherDao = new TeacherDao(mongoConfig);
    }

    @Test
    public void deleteTest(){
        Bson filter = Filters.eq("name", "马老师");
        long count = teacherDao.delete(filter);
        System.out.println(count);
    }
}
