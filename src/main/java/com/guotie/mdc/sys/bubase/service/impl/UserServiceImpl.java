package com.guotie.mdc.sys.bubase.service.impl;

import java.util.Map;
import java.util.List;
import com.guotie.mdc.sys.bubase.entity.User;
import java.lang.Override;
import com.guotie.mdc.sys.bubase.mapper.UserMapper;
import com.guotie.mdc.sys.bubase.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guotie.mdc.sys.bubase.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *  Service接口实现类
 * @author lmy
 * @Description Created on 2019-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    @Override
    public List<User> selectByConditions(Map<String,Object> params){
    return baseMapper.selectByConditions(params);
    }
}