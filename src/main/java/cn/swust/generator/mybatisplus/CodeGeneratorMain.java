package cn.swust.generator.mybatisplus;

import java.util.ArrayList;
import java.util.List;


public class CodeGeneratorMain {

    public static void main(String[] args) {
        // 数据库时区问题解决方案
        // SHOW VARIABLES LIKE '%time_zone%'
        // SET GLOBAL time_zone='+8:00'

        List<String> tableNames = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tableNames.add("user");//表名
            tableNames.add("role");//表名
            tableNames.add("user_role");//表名
            tableNames.add("permission");//表名
            tableNames.add("role_per");//表名
            tableNames.add("mydata");//表名
        }

        tableNames.forEach(tableName -> {
            MybatisPlusCodeConfig.codeGenerator(tableName,"com.example.study.dataFilter.test");
        });
    }
}
