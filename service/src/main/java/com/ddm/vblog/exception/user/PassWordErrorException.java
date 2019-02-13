package com.ddm.vblog.exception.user;

import com.ddm.vblog.exception.BaseException;

/**
 * @Auther: shaohua
 * @Date: 2019/2/12 18:17
 * @Description: 密码错误异常
 */
public class PassWordErrorException extends BaseException {

    public PassWordErrorException(String message){
        super(message);
    }
}
