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
        MybatisPlusCodeConfig codeConfig = new MybatisPlusCodeConfig("jdbc:mysql://192.168.1.85:3306/metro?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC",
                "com.mysql.jdbc.Driver", "metro", "@123456Aa");


        //=================================step2:如果service不需要继承关系可以跳过整个step2===============================================
        /*step2.1:如果service需要继承，那么下面两个设置2选1即可。如果不需要继承关系，可以不设置，两个都注销即可*/

        //设置为继承mybatis-plus的service
        codeConfig.setServiceParentPlus();

        /*//设置service继承其他类
        codeConfig.setServiceParentOther("cn.swust.generator.base.service.BaseService");
        codeConfig.setServiceImplParentOther("cn.swust.generator.base.service.impl.BaseServiceImpl");*/

        /*step2.2:如果entry和controller需要继承，不需要继承可以不设置*/
      /*  codeConfig.setEntryParent("");
        codeConfig.setControllerParent("");*/


        //=================================step3:controller相关设置,可以跳过整个step3===============================================

        /*//设置生成的controller里面不带基本方法
        codeConfig.setExcludeControllerMethod();*/
        /*//设置方法参数需要校验不为null
        codeConfig.setControllerMethodNeedValid();*/
        /*//设置生成的方法为restful请求
        codeConfig.setMethodRestful();*/

        //=================================step4:设置表===============================================
        List<String> tableNames = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            tableNames.add("base_dic");//表名
            /* tableNames.add("base_line");//表名
            tableNames.add("base_line_curvature");//表名
           tableNames.add("base_line_hstr");//表名
            tableNames.add("base_line_rf_label");//表名
            tableNames.add("base_line_roadbed");//表名
            tableNames.add("base_line_slope");//表名
            tableNames.add("base_line_switch");//表名
            tableNames.add("base_line_vstr");//表名
            tableNames.add("base_logical_pos");//表名
            tableNames.add("base_operator_line");//表名
            tableNames.add("base_version");//表名
            tableNames.add("base_org");//表名
            tableNames.add("base_position");//表名
            tableNames.add("mis_dic");//表名
            tableNames.add("stat_trck_alarm_g");//表名
            tableNames.add("stat_trck_alarm_s");//表名
            tableNames.add("stat_trck_alarm_w");//表名
            tableNames.add("stat_trck_dev_status");//表名
            tableNames.add("sys_dic");//表名*/
        }


        //=================================step5:执行===============================================
        tableNames.forEach(tableName -> {
            codeConfig.codeGenerator(tableName, "com.guotie.mdc.sys.bubase");
        });
    }
}
