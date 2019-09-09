package com.guotie.mdc.sys.bubase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guotie.mdc.sys.bubase.entity.BaseDic;
import com.guotie.mdc.sys.bubase.mapper.BaseDicMapper;
import com.guotie.mdc.sys.bubase.service.BaseDicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service接口实现类
 *
 * @author lmy
 * @Description Created on 2019-09-09
 */
@Service
public class BaseDicServiceImpl extends ServiceImpl<BaseDicMapper, BaseDic> implements BaseDicService {


    @Override
    public List<BaseDic> selectByConditions(Map<String, Object> params) {
        return baseMapper.selectByConditions(params);
    }
}