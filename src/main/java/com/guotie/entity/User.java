package com.guotie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.lang.Object;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 *  实体类
 *
 * @author lmy定义的模板引擎生成的代码
 * @Description Created on 2019-09-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class  User {


    private Integer id;

    private String name;

}
