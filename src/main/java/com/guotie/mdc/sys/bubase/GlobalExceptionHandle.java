package com.guotie.mdc.sys.bubase;

import cn.swust.generator.common.dto.ResponseResult;
import cn.swust.generator.common.enums.StatusEnums;
import cn.swust.generator.common.exception.SkeletonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author Tellsea
 * @Description Created on 2019/7/13
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public ResponseResult exception(Exception e) {
        log.error("【错误原因】{}", e.getClass());
        log.error("【错误描述】{}", e.getMessage());
        e.printStackTrace();
        return new ResponseResult(StatusEnums.SERVER_ERROR,e.getMessage());
    }

    @ExceptionHandler(value = SkeletonException.class)
    public ResponseResult globalExceptionHandle(SkeletonException e, HttpServletResponse response) {
        log.error("【错误原因】{}==", e.getClass());
        log.error("【错误描述】{}==", e.getMessage());
        e.printStackTrace();
        return new ResponseResult(response.getStatus(), e.getMessage());
    }
}
