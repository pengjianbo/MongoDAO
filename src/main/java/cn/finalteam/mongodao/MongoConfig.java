package cn.finalteam.mongodao;

import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/6/23 下午11:18
 */
public class MongoConfig {

    /**
     * mongodb服务器列表
     */
    private List<ServerAddress> serverAddressList;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * mongo client配置信息
     */
    private MongoClientOptions mongoClientOptions;

    public List<ServerAddress> getServerAddressList() {
        return serverAddressList;
    }

    public void setServerAddressList(List<ServerAddress> serverAddressList) {
        this.serverAddressList = serverAddressList;
    }

    public void setServerAddress(ServerAddress serverAddress) {
        serverAddressList = new ArrayList<ServerAddress>();
        serverAddressList.add(serverAddress);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public MongoClientOptions getMongoClientOptions() {
        return mongoClientOptions;
    }

    public void setMongoClientOptions(MongoClientOptions mongoClientOptions) {
        this.mongoClientOptions = mongoClientOptions;
    }
}
