package com.ddm.vblog.controller;

import com.ddm.vblog.entity.User;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.UserService;
import com.ddm.vblog.utils.RedisUtil;
import com.ddm.vblog.utils.ValidatorUtils;
import com.ddm.vblog.validation.group.user.UserLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description
 * @Date:2019/1/30 14:53
 * @Author ddm
 **/
@RestController
@Valid
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;



    /**
     * 用户登录
     * @param user 用户信息
     * @return
     */
    @PostMapping("login")
    public Object login(User user){
        try {
            redisUtil.set("a",111);
            ValidatorUtils.validateEntity(user, UserLogin.class);
            if(userService.userExist(user.getAccount())){
                 Subject subject = SecurityUtils.getSubject();
            }
        } catch (BaseException e){
            throw e;
        }
        return null;
    }
}
