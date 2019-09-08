# Mybatis Plus 代码生成器



核心实现功能一键生成 Controller、Service、ServiceImpl、Mapper、Mapper.xml

参考文档，[Mybatis Plus 官网文档](https://mp.baomidou.com/guide/generator.html)


## 使用方法


修改数据源配置，根据自己的实际地址修改,配置类位置为：`MybatisPlusCodeConfig`

```java
dsc.setUrl("jdbc:mysql://localhost:3306/数据库名?characterEncoding=utf8");
dsc.setDriverName("com.mysql.jdbc.Driver");
dsc.setUsername("root");
dsc.setPassword("123456");
```

接下来，找到CodeGeneratorMain类，更改使用的表名，使用Java的方式运行即可。启动类路径：`MybatisPlusCode`

如果需要自定义，可以自行修改在resources下的模板。可以在`MybatisPlusCodeConfig`类里面自定义是否需要继承mybatis-plus的父类service

## 根据https://github.com/Tellsea/mybatis-plus-code 项目修改
