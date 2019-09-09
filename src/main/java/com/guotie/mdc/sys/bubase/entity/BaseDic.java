package com.guotie.mdc.sys.bubase.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 *  实体类
 *
 * @author lmy
 * @Description Created on 2019-09-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  BaseDic {

    /**
     * 字典id
     */
    private Integer dicId;

    /**
     * 字典名
     */
    private String dicName;

    /**
     * 字典类别
     */
    private Integer category;

    /**
     * 父级字典id
     */
    private Integer parentId;

    private String dicDesc;

}
