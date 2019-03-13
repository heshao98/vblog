package com.ddm.vblog.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddm.vblog.entity.ResponseEntity;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.service.UserService;
import com.ddm.vblog.utils.Stringer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @Author: ddm
 * @Description: 基类Controller 封装基本操作
 */
@Controller
public class BaseController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Resource
    protected UserService userService;

    /**
     * @param errorCode    错误代码
     * @param errorMessage 错误信息
     * @return
     * @throws Exception
     * @author by chippy
     * @desc 构造错误的返回信息（带errorCode）.
     */
    protected Object error(Integer errorCode, String errorMessage) {
        return new ResponseEntity(errorCode, errorMessage).toJson();
    }

    /**
     * 根据用户名返回用户信息
     */
    protected User getUser(String account){
        return userService.getOne(new QueryWrapper<User>().eq("account",account));
    }

    /**
     * 获取到当前请求的用户实体信息
     * @return
     */
    protected User currUser(){
        Subject subject = SecurityUtils.getSubject();
        return (User)subject.getPrincipal();
    }

    /**
     * 获取到当前请求用户的用户名
     * @return
     */
    protected String getCurrUserName(){
        Subject subject = SecurityUtils.getSubject();
        return ((User)subject.getPrincipal()).getAccount();
    }

    protected String getCurrUserId(){
        Subject subject = SecurityUtils.getSubject();
        return ((User)subject.getPrincipal()).getId();
    }


    /**
     * @param errorMessage 错误信息
     * @return
     * @throws Exception
     * @author by chippy
     * @desc 构造错误的返回信息（不带errorCode）.
     */
    protected Object error(String errorMessage) {
        return new ResponseEntity(null, errorMessage).toJson();
    }

    /**
     * @param data - 业务数据json
     * @return
     * @throws Exception
     * @author by chippy
     * @desc 构造成功的返回信息.
     */
    protected Object success(Object data) {
        return new ResponseEntity(data).toJson();
    }

    /**
     * @param obj
     * @return
     * @author chippy
     * @desc 判断某对象是否为空..
     */
    protected boolean isNullOrEmpty(Object obj) {
        return Stringer.isNullOrEmpty(obj);
    }

    /**
     * @param request
     * @return
     * @author chippy
     * @desc 获取webapp完整URL. e.g http://www.abc.com/app/a/b/c?a=b&c=d...
     */
    protected final String getRequestURL(HttpServletRequest request) {

        if (request == null) {
            return "";
        }

        String url = "";
        url = "http://" + request.getServerName() // 服务器地址
                // + ":"
                // + request.getServerPort() //端口号
                + request.getContextPath() // 项目名称
                + request.getServletPath(); // 请求页面或其他地址
        try {
            // 参数
            Enumeration<?> names = request.getParameterNames();

            int i = 0;
            String queryString = request.getQueryString();
            if (null != queryString && !"".equals(queryString) && (!queryString.equals("null"))) {
                url = url + "?" + request.getQueryString();
                i++;
            }

            if (names != null) {
                while (names.hasMoreElements()) {
                    if (i == 0) {
                        url = url + "?";
                    }

                    String name = (String) names.nextElement();
                    if (url.indexOf(name) < 0) {
                        url = url + "&";

                        String value = request.getParameter(name);
                        if (value == null) {
                            value = "";
                        }
                        url = url + name + "=" + value;
                        i++;
                    }
                    // java.net.URLEncoder.encode(url, "ISO-8859");
                }
            }

            // String enUrl = java.net.URLEncoder.encode(url, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return url;
    }

}
