package com.ddm.vblog.exception;

import com.ddm.vblog.base.BaseController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常处理
 * @Date:2019/1/30 15:57
 * @Author ddm
 **/
@RestControllerAdvice
public class GlobalHandlerExceptionResolver extends BaseController {

    @ExceptionHandler(BaseException.class)
    public Object operateException(BaseException e){
        return error(e.getMessage());
    }
}
