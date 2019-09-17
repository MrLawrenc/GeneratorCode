package com.guotie.mdc.sys.bubase.service.impl;

import java.util.Map;
import java.util.List;
import com.guotie.mdc.sys.bubase.entity.DataPermission;
import java.lang.Override;
import com.guotie.mdc.sys.bubase.mapper.DataPermissionMapper;
import com.guotie.mdc.sys.bubase.service.DataPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guotie.mdc.sys.bubase.mapper.DataPermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *  Service接口实现类
 * @author lmy
 * @Description Created on 2019-09-11
 */
@Service
public class DataPermissionServiceImpl extends ServiceImpl<DataPermissionMapper,DataPermission> implements DataPermissionService {


    @Override
    public List<DataPermission> selectByConditions(Map<String,Object> params){
    return baseMapper.selectByConditions(params);
    }
}