package cn.finalteam.mongodao.model;

import java.io.Serializable;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/6/18 下午2:41
 */
public class Student implements Serializable{

    private static final long serialVersionUID = 3617931430808763429L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
