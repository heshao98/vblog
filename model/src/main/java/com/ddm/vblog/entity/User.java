package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ddm.vblog.validation.group.user.Register;
import com.ddm.vblog.validation.group.user.UserLogin;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Data
@TableName("vblog_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 登录账号
     */
    @NotNull(message = "用户名不能为空",groups = {UserLogin.class})
    @NotNull(message = "用户名不能为空",groups = {Register.class})
    private String account;

    /**
     * 用户图片注册验证码
     */
    @NotNull(message = "图片验证码不能为空",groups = {Register.class})
    @TableField(exist = false)
    private String verificationCode;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空",groups = {UserLogin.class})
    @NotNull(message = "密码不能为空",groups = {Register.class})
    private String password;

    /**
     * 盐
     */
    @TableField(fill = FieldFill.INSERT)
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 用户昵称
     */
    @NotNull(message = "昵称不能为空",groups = {Register.class})
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 是否admin，0不是，1是
     */
    private Integer admin;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
