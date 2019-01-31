package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@TableName("vblog_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 父评论Id
     */
    private Long parentId;

    /**
     * 评论的评论用户ID
     */
    private Long toUid;

    /**
     * 评论级别
     */
    private String levelFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
