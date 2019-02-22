package com.ddm.vblog.exception;

import com.ddm.vblog.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常处理
 * @Date:2019/1/30 15:57
 * @Author ddm
 **/
@Slf4j
@RestControllerAdvice
public class GlobalHandlerExceptionResolver extends BaseController {

    @ExceptionHandler(BaseException.class)
    public Object operateBaseException(BaseException e){
        log.error(e.getMessage(),e);
        return error(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Object unauthorizedException(UnauthorizedException e){
        log.error("访问无权限资源!");
        return error("您不具备相应权限,请联系管理员。");
    }

    @ExceptionHandler(Exception.class)
    public Object operateException(Exception e) {
        log.error(e.getMessage(),e);
        return error("系统异常");
    }
}
