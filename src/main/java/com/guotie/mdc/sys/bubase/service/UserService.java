package com.guotie.mdc.sys.bubase.service;

import java.util.Map;
import java.util.List;
import com.guotie.mdc.sys.bubase.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
/**
 *  Service接口
 *
 * @author lmy
 * @Description Created on 2019-09-10
 */

public interface UserService extends IService<User> {
List<User> selectByConditions(Map<String,Object> params);
}
