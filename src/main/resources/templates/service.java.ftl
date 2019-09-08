package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import org.springframework.stereotype.Service;
/**
 * ${table.comment!} Service接口
 *
 * @author ${author}
 * @Description Created on ${date}
 */

<#if cfg.serviceNeedExtends??>
    <#if cfg.serviceParentIsPlus??>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    <#else>
public interface ${table.serviceName} extends ${superServiceClass}{
    </#if>
<#else>
public interface ${table.serviceName} {
</#if>

}
