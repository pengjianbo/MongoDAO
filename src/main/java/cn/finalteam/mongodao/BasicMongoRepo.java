package cn.finalteam.mongodao;

import java.io.Serializable;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/6/23 下午11:46
 */
public class BasicMongoRepo implements Serializable {


    private String id;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 更新时间
     */
    private long updateTime;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}
