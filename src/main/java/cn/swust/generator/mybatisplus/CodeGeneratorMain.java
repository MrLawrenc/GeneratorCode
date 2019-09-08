package cn.swust.generator.mybatisplus;

import java.util.ArrayList;
import java.util.List;


public class CodeGeneratorMain {

    public static void main(String[] args) {
        // 数据库时区问题解决方案
        // SHOW VARIABLES LIKE '%time_zone%'
        // SET GLOBAL time_zone='+8:00'

        //=================================step1:传入数据库连接信息构造对象===============================================
        //构造器参数分别为url driver username password
        MybatisPlusCodeConfig codeConfig = new MybatisPlusCodeConfig("jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC",
                "com.mysql.jdbc.Driver", "root", "admin");


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

        //=================================step3:设置表===============================================
        List<String> tableNames = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tableNames.add("user");//表名
            tableNames.add("role");//表名
            tableNames.add("user_role");//表名
            tableNames.add("permission");//表名
            tableNames.add("role_per");//表名
            tableNames.add("mydata");//表名
        }


        //=================================step4:执行===============================================
        tableNames.forEach(tableName -> {
            codeConfig.codeGenerator(tableName, "com.example.study.dataFilter.test");
        });
    }
}
