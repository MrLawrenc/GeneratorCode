package ${package.Mapper};

import java.util.List;
import java.util.Map;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;
/**
 * ${table.comment!} Mapper接口
 *
 * @author ${author}
 * @Description Created on ${date}
 */
@Mapper
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

 List<${entity}> selectByConditions(Map<String,Object> params);
}
</#if>
