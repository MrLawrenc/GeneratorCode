package github.mrLawrenc.generator;

import github.mrLawrenc.generator.mybatisplus.MybatisPlusCodeConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器启动类
 */
public class CodeGeneratorMain {
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {
        // 数据库时区问题解决方案
        // SHOW VARIABLES LIKE '%time_zone%'
        // SET GLOBAL time_zone='+8:00'

        //=================================step1:传入数据库连接信息构造对象===============================================
        //构造器参数分别为url driver username password
        String url = "jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
        MybatisPlusCodeConfig codeConfig = new MybatisPlusCodeConfig(url, DRIVER, "root", "admin");


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


        //=================================step4:controller相关设置,可以跳过整个step4===============================================

        //设置生成的controller里面不带基本方法
        // codeConfig.setExcludeControllerMethod();
        //设置需要全局异常捕获
        codeConfig.setControllerMethodNeedValid();
        /*//设置方法参数需要校验不为null
        codeConfig.setControllerMethodNeedValid();*/
        /*//设置生成的方法为restful请求
        codeConfig.setMethodRestful();*/


        //=================================step5:设置需要生成的表===============================================
        List<String> tableNames = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            tableNames.add("alarm");
            tableNames.add("user");
        }


        //=================================step6:执行===============================================
        codeConfig.doGenerator(tableNames,"com.test");
    }
}
