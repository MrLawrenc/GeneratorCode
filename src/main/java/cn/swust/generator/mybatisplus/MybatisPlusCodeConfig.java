package cn.swust.generator.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : LiuMing
 * @date : 2019/9/6 23:21
 * @description :    Mybatis plus 代码生成器配置
 * Mybatis plus 官网：https://mp.baomidou.com/guide/generator.html
 */
public class MybatisPlusCodeConfig {
    /**
     * 项目路径
     */
    private static final String projectPath = System.getProperty("user.dir");
    /**
     * 模板存放位置
     */
    private static final String templatePathEntity = "/templates/entity.java.ftl";
    private static final String templatePathController = "/templates/controller.java.ftl";
    private static final String templatePathService = "/templates/service.java.ftl";
    private static final String templatePathServiceImpl = "/templates/serviceImpl.java.ftl";
    private static final String templatePathMapper = "/templates/mapper.java.ftl";
    private static final String templatePathMapperXML = "/templates/mapper.xml.ftl";
    private static final String templatePathJsp = "/templates/view.jsp.ftl";


    /**
     * 代码生成
     *
     * @param tableName   表名
     * @param packageName 基础包名
     */
    public static void codeGenerator(String tableName, String packageName) {
        /**
         * 生成文件位置
         */
        String end = packageName.replace(".", "/");
        System.out.println(end);
        String javaLocation = projectPath + "/src/main/java/" + end + "/";//生成的java文件所在目录
        String xmlLocation = projectPath + "/src/main/resources/mapper/";
        String pageLocation = projectPath + "/src/main/resources/views/";


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("lmy定义的模板引擎生成的代码");
        gc.setFileOverride(true);
//        gc.setEnableCache(true);
        gc.setServiceName("%sService");
        /*gc.setServiceName("%sService");*/
        /*gc.setEntityName("%s");
        gc.setMapperName("%sDaoImpl");
        gc.setXmlName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");*/
        gc.setFileOverride(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin");
        mpg.setDataSource(dsc);

        // 基础包配置
        final PackageConfig pc = new PackageConfig();
/*        pc.setModuleName("test");
        pc.setParent("com.guotie");*/
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        //重点: 自定义配置,向模板注入自定义的属性。还有问题，模板引擎会报错
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
               /* Map<String, Object> map = new HashMap<>();
                //设置生成的service需要继承苞米豆的ServiceImpl(这个属性会在模板里面进行判断，如为true，则会继承苞米豆的service)
                Map map1 = setServiceParent(map);

                this.setMap(map1);*/
            }
        };

        // 自定义输出配置，配置会被优先输出
        cfg.setFileOutConfigList(getFileOutConfig(javaLocation, xmlLocation, pageLocation));
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        /*自己可选父类,如果需要继承父类可以在下面的几个方法中设置。参数为父类的完整包名+类名
         *
         *注意:如果不设置父类，那么mybatis-plus会自动继承苞米豆的父类，如：接口service，sevice实现以及mapper都会继承苞米豆自带的父类，
         * 此时如果不需要继承父类是无法避免的，那么就需要在模板引擎加一个判断，判断是否需要继承。
         *
         * <p>本来是通过设置自定义属性来判断是否需要继承的，但是模板引擎去取自定义属性会报错，因此判断的条件只能**借助是否设置了实体类的父类。**
         * 在模板引擎判断是否有实体的父类设置:
         *  <#if superEntityClass??>
         *  public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
         *  <#else>
         *  public interface ${table.serviceName} {
         *  </#if>
         *  }
         *  因此打开下面代码会有继承，不打开是没有继承的(里面的全类名可以随便写)。
         *  具体需不需要继承关系，也可以直接更改模板引擎的条件判断，删除也行，
         * */
        //打开生成service会继承苞米豆的service
        strategy.setSuperEntityClass("java.lang.Object");


/*        strategy.setSuperEntityClass(basePackage + ".entity.BaseEntity");
        strategy.setSuperControllerClass(basePackage + ".controller.BaseController");
        strategy.setSuperControllerClass("cn.tellsea.skeleton.lmy.base.controller.BaseController");
        strategy.setSuperServiceClass(basePackage + ".service.BaseService");
        strategy.setSuperServiceImplClass(basePackage + ".service.impl.ServiceImpl");*/
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableName);

        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 自定义输出配置
     *
     * @return
     */
    private static List<FileOutConfig> getFileOutConfig(String javaLocation, String xmlLocation, String pageLocation) {
        List<FileOutConfig> focList = new ArrayList<>();

        // Entity
        focList.add(new FileOutConfig(templatePathEntity) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return javaLocation + "/entity/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        // Controller
        focList.add(new FileOutConfig(templatePathController) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                tableInfo.setXmlName(convertToLowercase(tableInfo.getEntityName()));
                return javaLocation + "/controller/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });

        // Service
        focList.add(new FileOutConfig(templatePathService) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return javaLocation + "/service/" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });

        // ServiceImpl
        focList.add(new FileOutConfig(templatePathServiceImpl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return javaLocation + "/service/impl/" + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
            }
        });

        // Mapper.java
        focList.add(new FileOutConfig(templatePathMapper) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return javaLocation + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
            }
        });

        // Mapper.xml
        focList.add(new FileOutConfig(templatePathMapperXML) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return xmlLocation + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

       /* // 生成页面jsp  基本不需要
        focList.add(new FileOutConfig(templatePathJsp) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String fileName = humpTurnUnderscore(new StringBuffer(convertToLowercase(tableInfo.getEntityName()))).toString();
                return pageLocation + fileName + ".jsp";
            }
        });*/
        return focList;
    }

    /**
     * 全部转为小写
     *
     * @param oldStr
     * @return
     */
    public static String convertToLowercase(String oldStr) {
        char[] chars = oldStr.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private static Pattern pattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static StringBuffer humpTurnUnderscore(StringBuffer str) {
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return humpTurnUnderscore(sb);
    }


    /*    *//**有问题自定义属性
     * @author : LiuMing
     * @date : 2019/9/6 22:56
     * @description :  自定义属性注入
     * <p>
     * 通过如下方式获取:
     * entity2.java.ftl
     * 自定义属性注入abc=${cfg.abc}
     * <p>
     * entity2.java.vm
     * 自定义属性注入abc=$!{cfg.abc}
     * <p>
     * <p>
     * need代表需要注入的键值对
     *//*
    public static Map<String, Object> setValue(Map<String, Object> map, Map<String, Object> need) {
        map.putAll(need);
        return map;
    }

    *//**
     * @author : LiuMing
     * @date : 2019/9/6 23:33
     * @description :   设置是否需要继承苞米豆定义的父类service
     *//*
    public static Map setServiceParent(Map<String,Object> map) {
        map.put("needParentService", "hello");
        return map;

    }*/
}
