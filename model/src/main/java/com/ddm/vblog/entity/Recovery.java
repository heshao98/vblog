package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author DindDangMao
 * @since 2019-03-06
 */
@Data
@TableName("vblog_recovery")
public class Recovery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 回复id
     */
    private String id;

    /**
     * 回复的评论id
     */
    private String commentId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 填写此条回复的用户id
     */
    private String userId;

    /**
     * 要回复的评论的用户id
     */
    private String commentUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 回复的主人
     */
    @TableField(exist = false)
    private User user;
}
