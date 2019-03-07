package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Data
@TableName("vblog_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 父评论Id
     */
    private String parentId;

    /**
     * 评论的评论用户ID
     */
    private String toUid;

    /**
     * 评论级别
     */
    private String levelFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 自己评论数量
     */
    @TableField(exist = false)
    private Integer subLevelCount;

    /**
     * 该评论的回复集合
     */
    @TableField(exist = false)
    private List<Reply> reply;

    /**
     * 此评论的回复数
     */
    @TableField(exist = false)
    private Integer replyCount;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;


}
