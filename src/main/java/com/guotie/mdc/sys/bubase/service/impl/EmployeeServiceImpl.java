package com.guotie.mdc.sys.bubase.service.impl;

import java.util.Map;
import java.util.List;
import com.guotie.mdc.sys.bubase.entity.Employee;
import java.lang.Override;
import com.guotie.mdc.sys.bubase.mapper.EmployeeMapper;
import com.guotie.mdc.sys.bubase.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guotie.mdc.sys.bubase.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *  Service接口实现类
 * @author lmy
 * @Description Created on 2019-09-09
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {


    @Override
    public List<Employee> selectByConditions(Map<String,Object> params){
    return baseMapper.selectByConditions(params);
    }
}