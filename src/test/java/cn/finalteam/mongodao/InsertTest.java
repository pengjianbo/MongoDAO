package cn.finalteam.mongodao;

import cn.finalteam.mongodao.dao.TeacherDao;
import cn.finalteam.mongodao.model.Student;
import cn.finalteam.mongodao.model.Teacher;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Desction:
 * Author:Jianbo.Peng
 * Date:2017/7/7 上午12:43
 */
public class InsertTest extends BasicTest{

    TeacherDao teacherDao;

    @Before
    public void init(){
        teacherDao = new TeacherDao(mongoConfig);
    }

    @Test
    public void insertOneTest() throws Exception{
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

        List<String> courses = new ArrayList<String>();
        courses.add("计算机应用");
        courses.add("C语言程序设计");
        teacher.setCourse(courses);
        teacherDao.insertOne(teacher);
    }

    @Test
    public void insertManyTest() throws Exception{
        Teacher teacher1 = new Teacher();
        teacher1.setName("马老师many");
        teacher1.setAge(45);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        teacher1.setBirthday(sdf.parse("1972-01-01").getTime());
        Map<String, List<Student>> classes = new HashMap<String, List<Student>>();
        List<Student> students = new ArrayList<Student>();
        for(int i = 0; i < 10; i++){
            Student student = new Student();
            student.setName("彭学生many" + i);
            student.setAge(new Random().nextInt(10) + 10);
            students.add(student);
        }
        classes.put("计高072", students);
        teacher1.setClasses(classes);
        teacher1.setMan(true);

        List<String> courses = new ArrayList<String>();
        courses.add("计算机应用");
        courses.add("C语言程序设计");
        teacher1.setCourse(courses);

        students.clear();

        Teacher teacher2 = new Teacher();
        teacher2.setName("马老师many 002");
        teacher2.setAge(45);
        teacher2.setBirthday(sdf.parse("1979-01-01").getTime());
        Map<String, List<Student>> classes02 = new HashMap<String, List<Student>>();
        for(int i = 0; i < 10; i++){
            Student student = new Student();
            student.setName("彭学生many 002" + i);
            student.setAge(new Random().nextInt(10) + 10);
            students.add(student);
        }
        classes02.put("计高071", students);
        teacher2.setClasses(classes02);
        teacher2.setMan(true);

        teacher2.setCourse(courses);

        List<Teacher> teacherList = new ArrayList<Teacher>();
        teacherList.add(teacher1);
        teacherList.add(teacher2);

        teacherDao.insertMany(teacherList);
    }
}
