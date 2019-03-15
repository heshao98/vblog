package com.ddm.vblog.shiro;

import com.ddm.vblog.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Description
 * @Date:2019/3/14 16:06
 * @Author ddm
 **/
public class ShiroSubjectManager {

    /**
     * 获取到当前请求用户的用户名
     * @return
             */
    public static String getCurrUserName() {
        Subject subject = SecurityUtils.getSubject();
        return ((User) subject.getPrincipal()).getAccount();
    }

    public static boolean isLogin() {
        Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated();
    }

    public static String getCurrUserId(){
        Subject subject = SecurityUtils.getSubject();
        return ((User)subject.getPrincipal()).getId();
    }


    public static User currUser(){
        Subject subject = SecurityUtils.getSubject();
        return (User)subject.getPrincipal();
    }
}
