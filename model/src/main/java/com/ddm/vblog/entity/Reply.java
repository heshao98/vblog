package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author DindDangMao
 * @since 2019-03-06
 */
@Data
@TableName("vblog_reply")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 回复id
     */
    @TableId(type = IdType.UUID)
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
     * 用户昵称
     */
    private String nickname;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 父评论的用户昵称
     */
    private String parentNickname;

    /**
     * 父评论的用户id
     */
    private String parentUserId;

    /**
     * 父评论id
     */
    private String parentId;
}
