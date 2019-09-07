package com.guotie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.guotie.entity.User;
import com.guotie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *  前端控制器
 *
 * @author lmy定义的模板引擎生成的代码
 * @Description Created on 2019-09-07
 */
@Controller
@RequestMapping("user")
public class UserController {
@Autowired
private UserService service;
}
