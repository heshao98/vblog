package com.ddm.vblog.exception.user;

import com.ddm.vblog.exception.BaseException;

/**
 * @Description 用户不存在异常
 * @Date:2019/1/30 17:23
 * @Author ddm
 **/
public class UserNotExistException extends BaseException {

    public UserNotExistException(String message){
        super(message);
    }
}
