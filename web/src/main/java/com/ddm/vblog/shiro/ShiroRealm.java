package com.ddm.vblog.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddm.vblog.common.Common;
import com.ddm.vblog.entity.Menu;
import com.ddm.vblog.entity.Role;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.mapper.RoleMapper;
import com.ddm.vblog.mapper.UserMapper;
import com.ddm.vblog.util.jwt.JwtToken;
import com.ddm.vblog.util.jwt.JwtUtil;
import com.ddm.vblog.utils.RedisUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 授权认证
 * @Date:2019/1/30 11:27
 * @Author ddm
 **/
@Service
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 注入用户mapper
     */
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    RedisUtil redisUtil;


    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 给用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.toString();
        SimpleAuthorizationInfo simpleAuthorizationInfo = null;
        if(username != null){
            simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            List<Role> roles = roleMapper.getAuthorization(username);
            if(!roles.isEmpty()){
                List<String> roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
                simpleAuthorizationInfo.addRoles(roleNames);
                for (Role role : roles) {
                    for (Menu menu : role.getMenus()) {
                        if(menu.getPerms() != null){
                            if(menu.getPerms().contains(",")){
                                simpleAuthorizationInfo.addStringPermissions(Arrays.asList(menu.getPerms().split(",")));
                            } else{
                                simpleAuthorizationInfo.addStringPermission(menu.getPerms());
                            }
                        }
                    }
                }
            }
        }
        return simpleAuthorizationInfo;
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
        String token = (String)authenticationToken.getCredentials();
        if(token == null){
            throw new AuthenticationException("token不能为空!");
        }

        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token无效!");
        }

        User userBean = userMapper.selectOne(new QueryWrapper<User>().eq("account",username));
        if (userBean == null) {
            throw new AuthenticationException("用户不存在,请联系管理员!");
        }

        String refreshToken = redisUtil.get(Common.REFRE_TOKEN_NAME + username);
        if (refreshToken != null && JwtUtil.verify(token, username, refreshToken)) {
            return new SimpleAuthenticationInfo(JwtUtil.getUsername(token), token, this.getName());
        } else{
            throw new AuthenticationException("token失效!");
        }
    }
}
