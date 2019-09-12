package ${package.ServiceImpl};

import java.util.Map;
import java.util.List;
import ${package.Entity}.${entity};
import java.lang.Override;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * ${table.comment!} Service接口实现类
 * @author ${author}
 * @Description Created on ${date}
 */
@Service
<#if cfg.serviceNeedExtends??>
    <#if cfg.serviceParentIsPlus??>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName},${entity}> implements ${table.serviceName} {
    <#else>
public class ${table.serviceImplName} extends ${superServiceImplClass} implements ${table.serviceName} {
    </#if>
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {
</#if>

<#if cfg.needAutowiredMapper??>
@Autowired
private ${table.mapperName} mapper;
<#else>
</#if>

<#if cfg.needAutowiredMapper??>
    @Override
    public List<${entity}> selectByConditions(Map<String,Object> params){
    return mapper.selectByConditions(params);
    }
<#else>
    @Override
    public List<${entity}> selectByConditions(Map<String,Object> params){
    if (params.get("currentPage") == null || params.get("pageSize") == null) {
    params.put("currentPage", "");
    params.put("pageSize", "");
    }
    return baseMapper.selectByConditions(params);
    }
@Override
public List<${entity}> list(Wrapper<${entity}> queryWrapper) {
        if (queryWrapper == null) {
        return super.list();
        } else {
        return super.list(queryWrapper);
        }
        }
</#if>
}