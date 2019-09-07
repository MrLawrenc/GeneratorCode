package com.guotie.service.impl;

import com.guotie.entity.User;
import com.guotie.mapper.UserMapper;
import com.guotie.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guotie.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *  Service接口实现类
 *
 * @author lmy定义的模板引擎生成的代码
 * @Description Created on 2019-09-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
@Autowired
private UserMapper mapper;
}