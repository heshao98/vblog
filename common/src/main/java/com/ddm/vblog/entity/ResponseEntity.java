package com.ddm.vblog.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * @program: xx
 * @Author: chippy
 * @Description:
 */
public class ResponseEntity {
    protected boolean success; // 是否成功
    protected String errmsg; // 错误内容
    protected Integer errcode; // 错误代码
    protected Object data; // 数据对象

    /**
     * @param success
     * @param data
     * @param errcode
     * @param errmsg
     * @author by chippy
     * @desc 构造函数.
     */
    public ResponseEntity(boolean success, Object data, Integer errcode, String errmsg) {
        this.success = success;
        this.data = data;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    /**
     * @param errcode
     * @param errmsg
     * @author by chippy
     * @desc 构造函数.
     */
    public ResponseEntity(Integer errcode, String errmsg) {
        this.success = false;
        this.data = null;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    /**
     * @param data
     * @author by chippy
     * @desc 构造函数.
     */
    public ResponseEntity(Object data) {
        this.success = true;
        this.data = data;
    }

    /**
     * @return
     * @author by chippy
     * @desc 将ResponseEntity转换成json格式对象.
     */
    public Object toJson() {
        return JSONObject.toJSONString(this);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
