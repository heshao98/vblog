package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ddm.vblog.validation.group.reply.ReplyCommentSave;
import com.ddm.vblog.validation.group.reply.ReplySave;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
    @NotNull(message = "评论id不能为空!",groups = {ReplySave.class, ReplyCommentSave.class})
    private String commentId;

    /**
     * 回复内容
     */
    @NotNull(message = "回复内容不能为空!",groups = {ReplySave.class, ReplyCommentSave.class})
    private String content;

    /**
     * 填写此条回复的用户id
     */
    @NotNull(message = "用户id不能为空",groups = {ReplySave.class, ReplyCommentSave.class})
    private String userId;

    /**
     * 用户昵称
     */
    @NotNull(message = "用户昵称不能为空",groups = {ReplySave.class, ReplyCommentSave.class})
    private String nickname;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 父评论的用户昵称
     */
    @NotNull(message = "父评论的用户昵称",groups = ReplySave.class)
    private String parentNickname;

    /**
     * 父评论的用户id
     */
    @NotNull(message = "父评论的用户id不能为空",groups = ReplySave.class)
    private String parentUserId;

    /**
     * 父评论id
     */
    @NotNull(message = "父评论id不能为空",groups = ReplySave.class)
    private String parentId;

    @TableField(exist = false)
    private Integer flag;

    @TableField(exist = false)
    private List<Reply> subReplys;
}
