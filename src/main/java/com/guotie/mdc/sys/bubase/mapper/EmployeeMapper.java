package com.guotie.mdc.sys.bubase.mapper;

import java.util.List;
import java.util.Map;
import com.guotie.mdc.sys.bubase.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 *  Mapper接口
 *
 * @author lmy
 * @Description Created on 2019-09-09
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

 List<Employee> selectByConditions(Map<String,Object> params);
}
