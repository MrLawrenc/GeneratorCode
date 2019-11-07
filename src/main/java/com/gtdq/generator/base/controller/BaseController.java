package com.gtdq.generator.base.controller;

import com.gtdq.generator.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基类控制器
 *
 */
public class BaseController<T> {

    @Autowired
    protected BaseService<T> baseService;

}
