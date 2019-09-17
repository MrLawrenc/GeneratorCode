package com.guotie.mdc.sys.bubase.entity;

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
 * @Description Created on 2019-09-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  DataPermission {

     @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String lineId;

    private String deptId;

    private String logId;

    private String baseDicId;

}
