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
     * @return 结果
     */
    boolean userExist(String account);
}
