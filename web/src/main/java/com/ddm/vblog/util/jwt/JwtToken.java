package com.ddm.vblog.util.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description
 * @Date:2019/1/30 18:00
 * @Author ddm
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
