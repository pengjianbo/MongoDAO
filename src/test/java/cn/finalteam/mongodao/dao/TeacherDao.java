package cn.finalteam.mongodao.dao;

import cn.finalteam.mongodao.MongoConfig;
import cn.finalteam.mongodao.MongoDao;
import cn.finalteam.mongodao.model.Teacher;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/6/25 上午2:36
 */
public class TeacherDao extends MongoDao<Teacher> {

    public TeacherDao(MongoConfig mongoConfig){
        super(mongoConfig);
    }
}
