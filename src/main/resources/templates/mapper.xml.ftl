<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">
<!--当前表名：${table.name}-->
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
    <result column="${field.name}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>

</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
<#list table.commonFields as field>
        ${field.name},
</#list>
        ${table.fieldNames}
    </sql>

</#if>
    <!--<#noparse>#{</#noparse>模板引擎不会再这个标签包裹的字符中寻找FTL标签-->
<select id="selectByConditions" parameterType="java.util.Map" resultType="${package.Entity}.${entity}">
select * from ${table.name}
 <where>
    <#list table.fields as field>
        <if test="${field.propertyName} != null and ${field.propertyName} != ''"> and ${field.name}=<#noparse>#{</#noparse>${field.propertyName}<#noparse>}</if></#noparse>
    </#list>
</where>
    <choose>
        <when test="sidx != null and sidx.trim() != ''">
            order by  <#noparse>${</#noparse>sidx<#noparse>}</#noparse>  <#noparse>${</#noparse>order<#noparse>}</#noparse>
        </when>
        <otherwise>
            <!--order by id desc-->
        </otherwise>
    </choose>
<bind name="key_offset" value="(currentPage-1)*pageSize"></bind>
      <if test="currentPage != '' and  pageSize != ''">
       limit <#noparse>#{</#noparse>key_offset<#noparse>}</#noparse>,<#noparse>#{</#noparse>pageSize<#noparse>}</#noparse>
      </if>

</select>
</mapper>