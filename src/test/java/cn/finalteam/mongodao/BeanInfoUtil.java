package cn.finalteam.mongodao;

import cn.finalteam.mongodao.model.Student;
import cn.finalteam.mongodao.model.Teacher;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/7/8 下午9:15
 */
public class BeanInfoUtil {
    // 设置bean的某个属性值
//    public static void setProperty(Teacher userInfo, String userName) throws Exception {
//        // 获取bean的某个属性的描述符
//        PropertyDescriptor propDesc = new PropertyDescriptor(userName, Teacher.class);
//        // 获得用于写入属性值的方法
//        Method methodSetUserName = propDesc.getWriteMethod();
//        // 写入属性值
//        methodSetUserName.invoke(userInfo, "wong");
//        System.out.println("set userName:" + userInfo.getUserName());
//    }
//
//    // 获取bean的某个属性值
//    public static void getProperty(UserInfo userInfo, String userName) throws Exception {
//        // 获取Bean的某个属性的描述符
//        PropertyDescriptor proDescriptor = new PropertyDescriptor(userName, UserInfo.class);
//        // 获得用于读取属性值的方法
//        Method methodGetUserName = proDescriptor.getReadMethod();
//        // 读取属性值
//        Object objUserName = methodGetUserName.invoke(userInfo);
//        System.out.println("get userName:" + objUserName.toString());
//    }

    public static void main(String[] args) throws Exception{
        Teacher teacher = new Teacher();
        teacher.setName("马老师");
        teacher.setAge(45);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        teacher.setBirthday(sdf.parse("1972-01-01").getTime());
        Map<String, List<Student>> classes = new HashMap<String, List<Student>>();
        List<Student> students = new ArrayList<Student>();
        for(int i = 0; i < 10; i++){
            Student student = new Student();
            student.setName("彭学生" + i);
            student.setAge(new Random().nextInt(10) + 10);
            students.add(student);
        }
        classes.put("计高071", students);
        teacher.setClasses(classes);
        teacher.setMan(true);
        teacher.setCreateTime(System.currentTimeMillis());
        List<String> courses = new ArrayList<String>();
        courses.add("计算机应用");
        courses.add("C语言程序设计");
        teacher.setCourse(courses);

        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor("updateTime", Teacher.class);
        // 获得用于写入属性值的方法
        Method methodSetUserName = propDesc.getWriteMethod();
        // 写入属性值
        methodSetUserName.invoke(teacher, System.currentTimeMillis());
        System.out.println("set updateTime:" + teacher.getUpdateTime());

        // 获取Bean的某个属性的描述符
        PropertyDescriptor proDescriptor = new PropertyDescriptor("createTime", Teacher.class);
        // 获得用于读取属性值的方法
        Method methodGetUserName = proDescriptor.getReadMethod();
        // 读取属性值
        Object objUserName = methodGetUserName.invoke(teacher);
        System.out.println("get age:" + objUserName.toString());
    }
}
