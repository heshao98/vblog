package com.ddm.vblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddm.vblog.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface UserService extends IService<User> {

    /**
     * 用户是否存在
     * @param account 用户名
     * @return
     */
    boolean userExist(String account);

    /**
     * 用户注册
     * @param user 注册的用户信息
     * @return
     */
    int register(User user);

    User getByAccount(String username);
}
