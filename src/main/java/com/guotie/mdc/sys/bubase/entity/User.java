package com.guotie.mdc.sys.bubase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 *  实体类
 *
 * @author lmy
 * @Description Created on 2019-09-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  User {

     @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

}
