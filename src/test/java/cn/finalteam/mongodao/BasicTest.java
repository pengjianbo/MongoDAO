package cn.finalteam.mongodao;


import cn.finalteam.mongodao.cfg.FtMongoConfig;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/7/7 上午12:44
 */
public class BasicTest {

    static MongoConfig mongoConfig;

    static {
        mongoConfig = FtMongoConfig.getMongoConfig();
    }
}
