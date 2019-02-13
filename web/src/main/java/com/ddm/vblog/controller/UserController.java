package com.ddm.vblog.controller;


import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 获取所有用户信息
     * @return
     */
    @GetMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public Object getAllUser(){
        List<User> list = userService.list();
        for (User user: list) {
            System.out.println(user.getAccount());
        }
        return success(list);
    }

}

