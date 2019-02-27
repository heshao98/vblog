package com.ddm.vblog.filter;

import com.ddm.vblog.common.Common;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.util.jwt.JwtToken;
import com.ddm.vblog.util.jwt.JwtUtil;
import com.ddm.vblog.utils.RedisUtil;
import com.ddm.vblog.utils.SpringContextUtil;
import com.ddm.vblog.utils.Stringer;
import com.ddm.vblog.utils.UUIDUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @Description jwt过滤器
 * @Date:2019/1/30 18:02
 * @Author ddm
 **/
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        redisUtil =  (RedisUtil) SpringContextUtil.getBean("redisUtil");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String accessToken = httpRequest.getHeader("Authorization");
        if(Stringer.isNullOrEmpty(accessToken) && Stringer.isNullOrEmpty(httpRequest.getHeader("refreshToken"))){
            return true;
        }
        String username = JwtUtil.getUsername(accessToken);
        if(username == null){
            responseError(response,"accessToken无效!");
        }
        try {
            if(redisUtil.exists(Common.ACCESS_TOKEN_NAME + username)){
                try {
                    this.newAccessToken = accessToken;
                    executeLogin(request, response);
                } catch (Exception e){
                    throw new BaseException(e);
                }
            } else{
                String refreshToken = redisUtil.get(Common.REFRE_TOKEN_NAME + username);
                if(refreshToken == null || !Objects.equals(refreshToken,httpRequest.getHeader("refreshToken"))){
                    try {
                        String message = URLEncoder.encode("refreshToken过期或失效,请重新登录!", "UTF-8");
                        responseError(response,message);
                    } catch (IOException e){
                        throw new BaseException(e);
                    }
                    return false;
                } else{
                    String newRefreshToken = UUIDUtils.generateUuid();
                    this.newAccessToken = JwtUtil.sign(username,newRefreshToken);
                    redisUtil.set(Common.REFRE_TOKEN_NAME + username,newRefreshToken,Common.REFRESH_TOKEN_EXPIRE_TIME);
                    redisUtil.set(Common.ACCESS_TOKEN_NAME + username,newAccessToken,Common.ACCESS_TOKEN_EXPIRE_TIME);
                    try {
                        executeLogin(request, response);
                    } catch (Exception e){
                        throw new BaseException(e);
                    }
                    httpResponse.setHeader("accessToken",newAccessToken);
                    httpResponse.setHeader("refreshToken",newRefreshToken);
                }
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            responseError(httpResponse,"系统异常!");
            return false;
        }
    }


    @Resource
    private RedisUtil redisUtil;

    private String newAccessToken;

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        JwtToken jwtToken = new JwtToken(newAccessToken);
        try {
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            getSubject(request, response).login(jwtToken);
        } catch (Exception e){
            responseError(response,e.getMessage());
            return false;
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    protected String getHeader(ServletRequest request,String value){
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        if(value == null){
            return httpServletRequest.getHeader("Authorization");
        } else{
            return value;
        }
    }

    private void responseError(ServletResponse servletResponse, String message){
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            message = URLEncoder.encode(message, "UTF-8");
            response.sendRedirect("http://localhost:8081/unauthorized"+"/"+message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
