package com.guotie.mdc.sys.bubase.service;

import java.util.Map;
import java.util.List;
import com.guotie.mdc.sys.bubase.entity.UserDataPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
/**
 *  Service接口
 *
 * @author lmy
 * @Description Created on 2019-09-11
 */

public interface UserDataPermissionService extends IService<UserDataPermission> {
List<UserDataPermission> selectByConditions(Map<String,Object> params);
}
