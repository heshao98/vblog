package com.ddm.vblog.exception.user;

import com.ddm.vblog.exception.BaseException;

/**
 * @Auther: shaohua
 * @Date: 2019/2/12 13:34
 * @Description: 用户被锁定异常
 */
public class UserLockingException extends BaseException {

    public UserLockingException(String message){
        super(message);
    }
}
