package com.ddm.vblog.controller;


import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 获取所有用户信息
     * @return
     */
    @SysLog("获取所有用户信息")
    @GetMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public Object getAllUser(){
        return success(userService.list());
    }

    @SysLog("获取当前用户登录信息")
    @GetMapping("/curr_user")
    public Object getCurrUser(){
        try {
            return success(currUser());
        } catch (Exception e){
            log.error("获取当前用户信息错误：",e);
            throw new BaseException("获取当前用户信息错!");
        }
    }
}

