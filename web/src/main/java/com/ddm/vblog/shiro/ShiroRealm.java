package com.ddm.vblog.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.exception.user.UserNotExistException;
import com.ddm.vblog.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @Description 授权认证
 * @Date:2019/1/30 11:27
 * @Author ddm
 **/
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 注入用户mapper
     */
    @Resource
    private UserMapper userMapper;

    /**
     * 给用户授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证当前登陆用户
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
//        User account = userMapper.selectOne(new QueryWrapper<>(User).eq("account", token.getUsername()));
//        if(account == null) {
//            throw new UserNotExistException("该用户名不存在！");
//        }
//        if(account.getStatus() == 0){
//            throw new LockedAccountException("用户被锁定！");
//        }
//        if(account.getPassword().equals())

        return null;
    }
}
