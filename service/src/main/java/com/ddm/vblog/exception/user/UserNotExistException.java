package com.ddm.vblog.exception.user;

import com.ddm.vblog.exception.BaseException;

/**
 * @Auther: shaohua
 * @Date: 2019/2/12 13:25
 * @Description:
 */
public class UserNotExistException extends BaseException {

    public UserNotExistException(String message){
        super(message);
    }
}
