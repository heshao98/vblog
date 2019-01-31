package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统验证码
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
@TableName("sys_captcha")
public class Captcha implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
}
