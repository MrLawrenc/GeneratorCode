package github.mrLawrenc.generator.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : LiuMing
 * @date : 2019/9/6 23:21
 * @description :    Mybatis plus 代码生成器配置
 * Mybatis plus 官网：https://mp.baomidou.com/guide/generator.html
 */
@Slf4j
@SuppressWarnings("all")
public class MybatisPlusCodeConfig {

    private String url;
    private String driverName;
    private String username;
    private String password;
    /**
     * 项目路径
     */
    private String projectPath = System.getProperty("user.dir");
    /**
     * 生成文件的位置，如C://test/
     */
    private String mapperLocation;
    private String serviceLocation;
    private String serviceImplLocation;
    private String controllerLocation;
    private String entryLocation;
    /**
     * 模板存放位置
     */
    private String templatePathEntity = "/templates/entity.java.ftl";
    private String templatePathController = "/templates/controller.java.ftl";
    private String templatePathService = "/templates/service.java.ftl";
    private String templatePathServiceImpl = "/templates/serviceImpl.java.ftl";
    private String templatePathMapper = "/templates/mapper.java.ftl";
    private String templatePathMapperXML = "/templates/mapper.xml.ftl";
    private String templatePathJsp = "/templates/view.jsp.ftl";

    /**
     * 表的第一列是id，且自增，那么会加上注解 @TableId(type = IdType.AUTO)，以此来消除第一列名字不为"id"，但是也是主键id的情况。
     * 如果不是需要更改为false
     */
    private boolean firstFieldIsId = true;


    /**
     * service是否需要继承关系
     */
    private boolean serviceNeedExtend = false;
    /**
     * service接口的父类为plus的service
     */
    private boolean serviceParentIsPlus = false;
    /**
     * service实现类的父类为plus的service
     */
    private boolean serviceImplParentIsPlus = false;


    /**
     * 不需要生成controller包含基本方法
     */
    private boolean needControllerMethod = true;
    /**
     * controller入参是否需要校验
     */
    private boolean needValid = false;
    /**
     * 设置controller生成的方法为rest风格的请求
     */
    private boolean methodRestful = false;


    /**
     * service接口的父类为plus的service
     */
    private String serviceParentClz = "";
    /**
     * service实现类的父类为plus的service
     */
    private String serviceImplParentClz = "";


    private String entryParentClz = "";
    private String controllerParentClz = "";

    private String author = "lmy";
    private boolean needGlobalExceptionHandle = false;

    /**
     * 设置生成service的父类为plus的service
     */
    public MybatisPlusCodeConfig setServiceParentPlus() {
        this.serviceNeedExtend = true;
        this.serviceParentIsPlus = true;
        this.serviceImplParentIsPlus = true;
        return this;
    }

    public MybatisPlusCodeConfig setAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * 需要全局异常处理
     */
    public MybatisPlusCodeConfig needGlobalExceptionHandle() {
        this.needGlobalExceptionHandle = true;
        return this;
    }

    /**
     * 自定义生成的service接口的父类<code>serviceParentClz</code>为需要继承的类的全限定名
     */
    public MybatisPlusCodeConfig setServiceParentOther(String serviceParentClz) {
        this.serviceNeedExtend = true;
        this.serviceParentIsPlus = false;
        this.serviceImplParentIsPlus = false;
        this.serviceParentClz = serviceParentClz;
        return this;
    }

    /**
     * 自定义生成的service实现类的父类
     */
    public MybatisPlusCodeConfig setServiceImplParentOther(String serviceImplParentClz) {
        this.serviceNeedExtend = true;
        this.serviceImplParentIsPlus = false;
        this.serviceImplParentClz = serviceImplParentClz;
        return this;
    }

    /**
     * entity父类设置
     */
    public MybatisPlusCodeConfig setEntryParent(String entryParentClz) {
        this.entryParentClz = entryParentClz;
        return this;
    }

    /**
     * controller父类设置
     */
    public MybatisPlusCodeConfig setControllerParent(String controllerParentClz) {
        this.controllerParentClz = controllerParentClz;
        return this;
    }

    /**
     * controller不需要基本方法
     */
    public void setExcludeControllerMethod() {
        this.needControllerMethod = false;
    }

    /**
     * 设置controller入参需要校验
     */
    public void setControllerMethodNeedValid() {
        this.needValid = true;
    }

    /**
     * 设置方法为restful风格请求
     */
    public void setMethodRestful() {
        methodRestful = true;
    }

    /**
     * 第一个字段(表的第一列不是主键id或者自增的)
     */
    public void setFirstFieldNoId() {
        firstFieldIsId = false;
    }

    /**
     * 设置entry的生成文件夹，如;location="C://test/"
     *
     * @param location
     */
    public void setEntryLocation(String location) {
        entryLocation = location;
    }

    /**
     * 构造方法
     */
    public MybatisPlusCodeConfig(String url, String driverName, String username, String password) {
        this.url = url;
        this.driverName = driverName;
        this.username = username;
        this.password = password;
    }


    public void replacePackage(String firstLine, String sourcePath, String newPath) throws Exception {

        try (RandomAccessFile rw = new RandomAccessFile(new File(sourcePath), "rw");) {
            int firstLineLength = rw.readLine().length();

            byte[] second = new byte[1024];
            rw.read(second, firstLineLength, (int) rw.length() - firstLineLength);

            String result = firstLine + new String(second, StandardCharsets.UTF_8);
            new FileInputStream(result).transferTo(new FileOutputStream(new File(newPath)));
        }
    }

    /**
     * @param tableNames  所有表名
     * @param packageName 基础包名
     */
    public void doGenerator(List<String> tableNames, String packageName) {

        CompletableFuture<Void> publicFuture = null;
        if (needControllerMethod || needControllerMethod) {
            publicFuture = CompletableFuture.runAsync(() -> {
                //生成文件位置
                String end = packageName.replace(".", "/");
                //生成的java文件所在目录
                String javaLocation = projectPath + "/src/main/java/" + end + "/";
                String path = javaLocation + "entity";

                String basePkg = packageName + ".entity;";
                try {
                    File temp = new File(path);
                    if (!temp.exists()) {
                        temp.mkdirs();
                    }
                    String firstLine = "package " + basePkg;
                    if (needGlobalExceptionHandle) {
                        String globalException = projectPath + "/src/main/java/github/mrLawrenc/generator/common/GlobalExceptionHandle.java";
                        replacePackage(firstLine,globalException,path + "/GlobalExceptionHandle.java");
                    }

                    if (needControllerMethod) {
                        String r = projectPath + "/src/main/java/github/mrLawrenc/generator/common/ResponseResult.java";
                        String e = projectPath + "/src/main/java/github/mrLawrenc/generator/common/StatusEnums.java";

                        replacePackage(firstLine,r,path + "/ResponseResult.java");
                        replacePackage(firstLine,e,path + "/StatusEnums.java");
                    }
                } catch (Exception exception) {
                    log.error("生成controller返回实体失败,请拷贝{}处的文件到entity包下!", exception);
                }

                log.info("生成的controller返回实体位置:{}", path);
            });
        }


        CompletableFuture[] futures = tableNames.stream().map(tableName -> {
            return CompletableFuture.runAsync(() -> codeGenerator(tableName, packageName));
        }).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();

        if (Objects.nonNull(publicFuture)) {
            publicFuture.join();
        }
    }

    /**
     * 代码生成
     *
     * @param tableName   表名
     * @param packageName 基础包名
     */
    private void codeGenerator(String tableName, String packageName) {
        //生成文件位置
        String end = packageName.replace(".", "/");
        //生成的java文件所在目录
        String javaLocation = projectPath + "/src/main/java/" + end + "/";
        String xmlLocation = projectPath + "/src/main/resources/mapper/";
        String pageLocation = projectPath + "/src/main/resources/views/";
        log.info("生成的文件位置 java:{}  xml:{}   view:{}", javaLocation, xmlLocation, pageLocation);


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
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
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 基础包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        //重点: 自定义配置,向模板注入自定义的属性。
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                if (serviceNeedExtend) {
                    //设置service需要继承，这个属性会在freemarker里面取出来作为是否继承的依据,value值随便写，只要不为null即可
                    map.put("serviceNeedExtends", "aa");
                    if (!serviceImplParentIsPlus) {//父类不为plus的service实现类需要生成自动注入mapper的代码
                        map.put("needAutowiredMapper", "bb");
                    }

                    if (serviceParentIsPlus) {
                        map.put("serviceParentIsPlus", "cc");
                    }
                    if (serviceImplParentIsPlus) {
                        map.put("serviceImplParentIsPlus", "dd");
                    }
                } else {
                    map.put("serviceNeedExtends", null);
                }

                //前提是继承了mybatis-plus的service才能设定生成controller的基本方法
                map.put("needControllerMethod", serviceParentIsPlus && needControllerMethod ? "ss" : null);

                map.put("firstFieldIsId", firstFieldIsId ? "ss" : null);

                map.put("needValid", serviceParentIsPlus && needControllerMethod && needValid ? "ss" : null);
                map.put("methodRestful", serviceParentIsPlus && needControllerMethod && methodRestful ? "ss" : null);
                this.setMap(map);
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

        try {
            if (StringUtils.isNotEmpty(controllerParentClz)) {
                strategy.setSuperControllerClass(controllerParentClz);
            }
            if (StringUtils.isNotEmpty(entryParentClz)) {
                strategy.setSuperControllerClass(entryParentClz);
            }
        } catch (Exception e) {
            System.err.println("entry or controller采用自定义的父类失败,将不会设置继承关系!");
        }
        /* strategy.setSuperEntityClass(basePackage + ".entity.BaseEntity");
        strategy.setSuperControllerClass(basePackage + ".controller.BaseController");
        strategy.setSuperControllerClass("cn.tellsea.skeleton.lmy.base.controller.BaseController");
        strategy.setSuperServiceClass(basePackage + ".service.BaseService");
        strategy.setSuperServiceImplClass(basePackage + ".service.impl.ServiceImpl");*/
        //根据配置确定是否需要service的继承关系，如果还需要entry和controller的继承关系，可以依照上面注释的代码自行配置
        if (serviceNeedExtend && !serviceParentIsPlus && StringUtils.isNotEmpty(serviceParentClz)) {
            try {
                strategy.setSuperServiceClass(serviceParentClz);
            } catch (Exception e) {
                System.err.println("接口service采用自定义的父类失败,将转换为继承mybatis-plus的service接口!\t父类全限定名为:" + serviceParentClz);
            }
        }

        if (serviceNeedExtend && !serviceImplParentIsPlus && StringUtils.isNotEmpty(serviceImplParentClz)) {
            try {
                strategy.setSuperServiceImplClass(serviceImplParentClz);
            } catch (Exception e) {
                System.err.println("实现类service采用自定义的父类失败,将转换为继承mybatis-plus的service实现类!\t父类全限定名为:" + serviceImplParentClz);
            }
        }

        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableName);

        //设置为rest风格的controller
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        FreemarkerTemplateEngine freemarker = new FreemarkerTemplateEngine();
        mpg.setTemplateEngine(freemarker);
        try {
            mpg.execute();
        } catch (Exception exception) {
            if (!(exception instanceof SSLException)) {
                throw exception;
            }
        }
    }

    /**
     * 自定义输出配置
     */
    private List<FileOutConfig> getFileOutConfig(String javaLocation, String xmlLocation, String pageLocation) {
        List<FileOutConfig> focList = new ArrayList<>();

        // Entity
        focList.add(new FileOutConfig(templatePathEntity) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entryPrefix = (StringUtils.isEmpty(entryLocation) ? javaLocation + "/entity/" : entryLocation);
                return entryPrefix + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        // Controller
        focList.add(new FileOutConfig(templatePathController) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                tableInfo.setXmlName(convertToLowercase(tableInfo.getEntityName()));
                String controllerPrefix = (StringUtils.isEmpty(controllerLocation) ? javaLocation + "/controller/" : controllerLocation);
                return controllerPrefix + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });

        // Service
        focList.add(new FileOutConfig(templatePathService) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String servicePrefix = (StringUtils.isEmpty(serviceLocation) ? javaLocation + "/service/" : serviceLocation);
                return servicePrefix + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });

        // ServiceImpl
        focList.add(new FileOutConfig(templatePathServiceImpl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String serviceImplPrefix = (StringUtils.isEmpty(serviceImplLocation) ? javaLocation + "/service/impl/" : serviceImplLocation);
                return serviceImplPrefix + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
            }
        });

        // Mapper.java
        focList.add(new FileOutConfig(templatePathMapper) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String mapperPrefix = (StringUtils.isEmpty(mapperLocation) ? javaLocation + "/mapper/" : mapperLocation);
                return mapperPrefix + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
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
     */
    public String convertToLowercase(String oldStr) {
        char[] chars = oldStr.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private Pattern pattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     */
    public StringBuffer humpTurnUnderscore(StringBuffer str) {
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

}
