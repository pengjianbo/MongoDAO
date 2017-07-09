package cn.finalteam.mongodao;

import cn.finalteam.mongodao.dao.TeacherDao;
import cn.finalteam.mongodao.model.Teacher;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.junit.*;
import org.junit.Test;

import java.util.List;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/7/8 上午4:12
 */
public class FindTest extends BasicTest {
    TeacherDao teacherDao;

    @Before
    public void init(){
        teacherDao = new TeacherDao(mongoConfig);
    }

    @org.junit.Test
    public void findAllTest() throws Exception{
        List<Teacher> teacherList = teacherDao.find();
        System.out.println(JSON.toJSONString(teacherList));
    }

    @Test
    public void findByNameTest()throws Exception{
        Bson filter = Filters.eq("name", "马老师");
        List<Teacher> teacherList = teacherDao.find(filter);
        System.out.println(JSON.toJSONString(teacherList));
    }
}
