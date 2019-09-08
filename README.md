# Mybatis Plus 代码生成器



核心实现功能一键生成 Controller、Service、ServiceImpl、Mapper、Mapper.xml

参考文档，[Mybatis Plus 官网文档](https://mp.baomidou.com/guide/generator.html)


## 使用方法

找到CodeGeneratorMain类，查看使用示例。

#### step1
```java
//=================================step1:传入数据库连接信息构造对象===============================================
//构造器参数分别为url driver username password
MybatisPlusCodeConfig codeConfig = new MybatisPlusCodeConfig("jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC",
        "com.mysql.jdbc.Driver", "root", "admin");
```
#### step2
```java
 //=================================step2:如果不需要继承关系可以跳过整个step2===============================================
/*step2.1:如果service需要继承，那么下面两个设置2选1即可。如果不需要继承关系，可以不设置，两个都注销即可*/

//设置为继承mybatis-plus的service
codeConfig.setServiceParentPlus();

/*//设置service继承其他类
codeConfig.setServiceParentOther("cn.swust.generator.base.service.BaseService");
codeConfig.setServiceImplParentOther("cn.swust.generator.base.service.impl.BaseServiceImpl");*/

/*step2.2:如果entry和controller需要继承，不需要继承可以不设置*/
/*  codeConfig.setEntryParent("");
codeConfig.setControllerParent("");*/
```
#### step3
```java
//=================================step3:设置需要的表list===============================================
List<String> tableNames = new ArrayList<>();
for (int i = 0; i < 6; i++) {
    tableNames.add("user");//表名
    tableNames.add("role");//表名
    tableNames.add("user_role");//表名
    tableNames.add("permission");//表名
    tableNames.add("role_per");//表名
    tableNames.add("mydata");//表名
}
```
#### step4
```java
//=================================step4:执行===============================================
tableNames.forEach(tableName -> {
    codeConfig.codeGenerator(tableName, "com.example.study.dataFilter.test");
});
```

如果需要自定义，可以自行修改在resources下的模板。

## 注:
参考https://github.com/Tellsea/mybatis-plus-code更改
