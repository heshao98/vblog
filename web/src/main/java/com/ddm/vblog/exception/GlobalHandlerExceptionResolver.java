package com.ddm.vblog.exception;

import com.alibaba.fastjson.JSONObject;
import com.ddm.vblog.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @Description 全局异常处理
 * @Date:2019/1/30 15:57
 * @Author ddm
 **/
@Slf4j
@RestControllerAdvice
public class GlobalHandlerExceptionResolver extends BaseController {

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

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(ValidationException exception) {
        JSONObject jsonObject = new JSONObject();
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            log.error(exs.getMessage(), exs);
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<?> item : violations) {
                sb.append(item.getMessage()).append("/n");
            }
            jsonObject.put("msg", sb);
            jsonObject.put("result", 0);
        }
        return error(jsonObject.getString("msg"));
    }
}
