package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户操作日志表
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Data
@TableName("vblog_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 访问Ip
     */
    private String ip;

    /**
     * 访问方法
     */
    private String method;

    /**
     * 方法参数
     */
    private String params;

    /**
     * 操作人昵称
     */
    private String nickname;

    /**
     * 操作事项
     */
    private String operation;

    /**
     * 访问的地址
     */
    private String url;

    /**
     * 操作耗时
     */
    private Double time;

    /**
     * 操作用户userId
     */
    private String userId;

}
