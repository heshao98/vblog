package com.ddm.vblog.controller;

import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.common.Common;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.exception.user.PassWordErrorException;
import com.ddm.vblog.exception.user.UserNotExistException;
import com.ddm.vblog.service.UserService;
import com.ddm.vblog.util.jwt.JwtUtil;
import com.ddm.vblog.utils.RedisUtil;
import com.ddm.vblog.utils.UUIDUtils;
import com.ddm.vblog.utils.ValidatorUtils;
import com.ddm.vblog.validation.group.user.UserLogin;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description
 * @Date:2019/1/30 14:53
 * @Author ddm
 **/
@RestController
@Valid
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;



    /**
     * 用户登录
     * @param user 用户信息
     * @return
     */
    @SysLog("用户登录")
    @PostMapping("login")
    public Object login(User user, HttpServletResponse response){
        try {
            ValidatorUtils.validateEntity(user, UserLogin.class);
            User getUser = getUser(user.getAccount());
            if(getUser != null){
                user.setSalt(getUser.getSalt());
                if(passwordAnalysis(user).equals(getUser.getPassword())){
                    String refreshToken = UUIDUtils.generateUuid();
                    redisUtil.exists("!");
                    String accessToken = JwtUtil.sign(getUser.getAccount(),refreshToken);
                    //将accessToken和refreshToken存入redis
                    redisUtil.set(Common.REFRE_TOKEN_NAME + getUser.getAccount(),refreshToken,Common.REFRESH_TOKEN_EXPIRE_TIME);
                    redisUtil.set(Common.ACCESS_TOKEN_NAME + getUser.getAccount(),accessToken,Common.ACCESS_TOKEN_EXPIRE_TIME);
                    return success("成功");
                } else{
                    throw new PassWordErrorException("密码错误！");
                }
            } else{
                throw new UserNotExistException("用户不存在！");
            }
        } catch (BaseException e){
            throw new BaseException("系统异常,登录失败!");
        } catch (Exception e){
            return error("系统异常!");
        }
    }

    /**
     * 密码通过和注册相同的方式加密后返回
     * @param user 用户名和密码信息
     * @return
     */
    private String passwordAnalysis(User user){
        return new SimpleHash("md5",user .getPassword(),ByteSource.Util.bytes(user.getSalt()),3).toBase64();
    }

    /**
     * 用户注册
     * @param user 注册的用户信息
     * @return
     */
    @SysLog("用户注册")
    @PostMapping("/register")
    public Object register(User user){
        try {
            ValidatorUtils.validateEntity(user, UserLogin.class);
            return userService.register(user);
        } catch (BaseException e){
            throw e;
        }
    }

    @RequestMapping("/unauthorized/{message}")
    public Object illegal(@PathVariable String message){
        return error(message);
    }
}
