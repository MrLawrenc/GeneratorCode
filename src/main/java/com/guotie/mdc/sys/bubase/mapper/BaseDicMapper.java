package com.guotie.mdc.sys.bubase.mapper;

import java.util.List;
import java.util.Map;
import com.guotie.mdc.sys.bubase.entity.BaseDic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 *  Mapper接口
 *
 * @author lmy
 * @Description Created on 2019-09-11
 */
@Mapper
public interface BaseDicMapper extends BaseMapper<BaseDic> {

 List<BaseDic> selectByConditions(Map<String,Object> params);
}
