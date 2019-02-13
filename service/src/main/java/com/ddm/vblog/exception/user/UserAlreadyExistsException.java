package com.ddm.vblog.exception.user;

import com.ddm.vblog.exception.BaseException;

/**
 * @Auther: shaohua
 * @Date: 2019/2/12 16:03
 * @Description: 用户已存在异常
 */
public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
