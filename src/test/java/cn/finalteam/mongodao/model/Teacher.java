package cn.finalteam.mongodao.model;

import cn.finalteam.mongodao.BasicMongoRepo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/6/18 下午2:44
 */
public class Teacher extends BasicMongoRepo implements Serializable{

    private static final long serialVersionUID = 3617931430808763429L;

    //string
    private String name;
    //int
    private int age;
    //long
    private long birthday;
    //abstract map, custom bean
    private Map<String, List<Student>> classes;
    //list
    private List<String> course;
    //boolean
    private boolean isMan;

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

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public Map<String, List<Student>> getClasses() {
        return classes;
    }

    public void setClasses(Map<String, List<Student>> classes) {
        this.classes = classes;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }
}
