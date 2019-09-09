package com.guotie.mdc.sys.bubase.mapper;

import java.util.List;
import java.util.Map;
import com.guotie.mdc.sys.bubase.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 *  Mapper接口
 *
 * @author lmy
 * @Description Created on 2019-09-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

 List<User> selectByConditions(Map<String,Object> params);
}
