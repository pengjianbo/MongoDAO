# MongoDao
是一个mongodb使用封装库

# Maven
```xml
<dependency>
    <groupId>cn.finalteam</groupId>
    <artifactId>mongodao</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```


# 配置
## 数据连接配置
```java
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
```

## 数据操作DAO
```java
public class TeacherDao extends MongoDao<Teacher> {

    public TeacherDao(MongoConfig mongoConfig){
        super(mongoConfig);
    }
    
    //在这里可以扩展dao操作方法
}

```

# CRUD操作
## 插入

```java
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
```

## 查询
```java
Bson filter = Filters.eq("name", "马老师");
List<Teacher> teacherList = teacherDao.find(filter);
System.out.println(JSON.toJSONString(teacherList));
```
## 修改
```java
Bson filter = Filters.eq("name", "马老师");
Document document = new Document();
document.put("isMan", false);
long l = teacherDao.updateOne(filter, document);
```
## 删除
```java
Bson filter = Filters.eq("name", "马老师");
long count = teacherDao.delete(filter);
```
