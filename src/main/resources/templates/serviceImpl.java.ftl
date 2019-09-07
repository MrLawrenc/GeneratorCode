package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * ${table.comment!} Service接口实现类
 *
 * @author ${author}
 * @Description Created on ${date}
 */
@Service
<#if superEntityClass??>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName},${entity}> implements ${table.serviceName} {
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {
</#if>
@Autowired
private ${table.mapperName} mapper;
}