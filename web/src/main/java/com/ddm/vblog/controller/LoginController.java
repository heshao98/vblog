package com.ddm.vblog.controller;

import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.common.Common;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.UserService;
import com.ddm.vblog.util.jwt.JwtUtil;
import com.ddm.vblog.utils.RedisUtil;
import com.ddm.vblog.utils.UUIDUtils;
import com.ddm.vblog.utils.ValidatorUtils;
import com.ddm.vblog.validation.group.user.UserLogin;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Description
 * @Date:2019/1/30 14:53
 * @Author ddm
 **/
@RestController
@Valid
@Slf4j
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    DefaultKaptcha defaultKaptcha;


    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            redisUtil.set("rightCode",)
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 用户登录
     * @param user 用户信息
     * @return
     */
    @SysLog("用户登录")
    @PostMapping(value = "login",produces = "application/json;charset=UTF-8")
    public Object login(@RequestBody User user, HttpServletResponse response){
        try {
            ValidatorUtils.validateEntity(user, UserLogin.class);
            User getUser = getUser(user.getAccount());
            if(getUser != null){
                user.setSalt(getUser.getSalt());
                if(passwordAnalysis(user).equals(getUser.getPassword())){
                    String refreshToken = UUIDUtils.generateUuid();
                    String accessToken = JwtUtil.sign(getUser.getAccount(),refreshToken);
                    //将accessToken和refreshToken存入redis
                    redisUtil.set(Common.REFRE_TOKEN_NAME + getUser.getAccount(),refreshToken,Common.REFRESH_TOKEN_EXPIRE_TIME);
                    redisUtil.set(Common.ACCESS_TOKEN_NAME + getUser.getAccount(),accessToken,Common.ACCESS_TOKEN_EXPIRE_TIME);
                    response.setHeader("refreshToken",refreshToken);
                    response.setHeader("accessToken",accessToken);
                    return success("成功");
                } else{
                    log.error("密码错误!");
                    return error("密码错误!");
                }
            } else{
                log.error("用户不存在!");
                return error("用户不存在!");
            }
        } catch (Exception e){
            log.error("未知异常,登录失败:",e);
            e.printStackTrace();
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
            log.error("用户注册失败:",e);
            throw e;
        }
    }

    @SysLog("用户登出")
    @GetMapping("/logout")
    public Object logout(){
        try {
            redisUtil.remove( "accessToken:" + getCurrUserName());
            redisUtil.remove("refreshToken:" + getCurrUserName());
            SecurityUtils.getSubject().logout();
        } catch (Exception e){
            log.error("用户登出异常:", e);
            throw new BaseException("用户登出异常!");
        }
        return success("已经登出!");
    }

    /**
     * 错误信息返回
     * @param message 错误信息字符串
     * @return 错误信息
     */
    @RequestMapping("/unauthorized/{message}")
    public Object illegal(@PathVariable String message){
        return error(401,message);
    }
}
