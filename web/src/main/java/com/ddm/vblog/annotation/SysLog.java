package com.ddm.vblog.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Date:2019/2/18 10:51
 * @Author ddm
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SysLog {
    String value();
}
