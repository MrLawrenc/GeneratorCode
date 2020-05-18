package github.mrLawrenc.generator.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : MrLawrenc
 * @description :   全局异常处理
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
        return new ResponseResult(StatusEnums.SERVER_ERROR);
    }

}
