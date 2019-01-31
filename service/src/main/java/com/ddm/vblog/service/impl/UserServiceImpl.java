package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.mapper.UserMapper;
import com.ddm.vblog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询此用户是否存在
     * @param account 用户名
     * @return 结果值
     */
    @Override
    public boolean userExist(String account) {
        return userMapper.selectCount(new QueryWrapper<User>().eq("account", account)) > 0;
    }
}
