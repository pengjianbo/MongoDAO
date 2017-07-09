package cn.finalteam.mongodao.cfg;

import cn.finalteam.mongodao.MongoConfig;
import com.mongodb.*;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/6/25 上午7:10
 */
public class FtMongoConfig {

    public static MongoConfig getMongoConfig(){
        MongoConfig mongoConfig = new MongoConfig();
        mongoConfig.setServerAddress(new ServerAddress("127.0.0.1", 27017));

        try {
            CompositeConfiguration config = new CompositeConfiguration();
            config.addConfiguration(new PropertiesConfiguration("mongo.cfg"));
            String user = config.getString("user");
            String passwd = config.getString("passwd");
            String dbname = config.getString("dbname");
            mongoConfig.setUsername(user);
            mongoConfig.setPassword(passwd);
            mongoConfig.setDatabaseName(dbname);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder()
                .socketKeepAlive(true)
                .connectTimeout(5000)
                .socketTimeout(5000)
                .readPreference(ReadPreference.primary())
                .connectionsPerHost(5)
                .maxWaitTime(5000)
                .threadsAllowedToBlockForConnectionMultiplier(30)
                .build();
        mongoConfig.setMongoClientOptions(mongoClientOptions);
        return mongoConfig;
    }
}
