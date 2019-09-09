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
 //=================================step2:实体类相关设置，可以完全跳过step2===============================================
        /*设置table的第一列即实体的第一个字段不为自增主键(默认表的第一列是自增主键)
         *
         * 如果启用下面这行代码，在插入数据的时候，没有设置主键id的值，那么mybatis-plus会使用雪花算法生成id插入，
         * 可能会报和数据库id类型不一致的错误。
         *
         * 不启用会在第一个字段加上自增主键的注解
         * */
        //codeConfig.setFirstFieldNoId();

        /*//设置生成的实体类的文件位置
        codeConfig.setEntryLocation("F:\\JavaProject\\GeneratorCode\\src\\main\\java\\com\\guotie\\mdc\\sys\\bubase\\entity\\");*/
```
#### step3
```java
//=================================step3:如果service不需要继承关系可以跳过整个step3===============================================
    /*step3.1:如果service需要继承，那么下面两个设置2选1即可。如果不需要继承关系，可以不设置，两个都注销即可*/

        //设置为继承mybatis-plus的service
        codeConfig.setServiceParentPlus();

        /*//设置service继承其他类
        codeConfig.setServiceParentOther("cn.swust.generator.base.service.BaseService");
        codeConfig.setServiceImplParentOther("cn.swust.generator.base.service.impl.BaseServiceImpl");*/

        /*step3.2:如果entry和controller需要继承，不需要继承可以不设置*/
      /*  codeConfig.setEntryParent("");
        codeConfig.setControllerParent("");*/
```
#### step4
```java
 //=================================step4:controller相关设置,可以跳过整个step4===============================================

        /*//设置生成的controller里面不带基本方法
        codeConfig.setExcludeControllerMethod();*/
        /*//设置方法参数需要校验不为null
        codeConfig.setControllerMethodNeedValid();*/
        /*//设置生成的方法为restful请求
        codeConfig.setMethodRestful();*/
```
#### step5
```java
//=================================step5:设置需要的表list===============================================
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
#### step6
```java
//=================================step6:执行===============================================
tableNames.forEach(tableName -> {
    codeConfig.codeGenerator(tableName, "com.example.study.dataFilter.test");
});
```

如果需要自定义，可以自行修改在resources下的模板。

## 注:
参考https://github.com/Tellsea/mybatis-plus-code更改
