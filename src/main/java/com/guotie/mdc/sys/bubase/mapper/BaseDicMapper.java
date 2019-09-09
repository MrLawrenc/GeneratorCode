package com.guotie.mdc.sys.bubase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guotie.mdc.sys.bubase.entity.BaseDic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Mapper接口
 *
 * @author lmy
 * @Description Created on 2019-09-09
 */
@Mapper
public interface BaseDicMapper extends BaseMapper<BaseDic> {

    List<BaseDic> selectByConditions(Map<String, Object> params);
}
