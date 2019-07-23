package com.ddm.vblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.entity.Reply;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.exception.ReplyException;
import com.ddm.vblog.service.CommentService;
import com.ddm.vblog.service.ReplyService;
import com.ddm.vblog.utils.ValidatorUtils;
import com.ddm.vblog.validation.group.reply.ReplyCommentSave;
import com.ddm.vblog.validation.group.reply.ReplySave;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-03-06
 */
@Valid
@Slf4j
@RestController
@RequestMapping("/replys")
public class ReplyController extends BaseController {

    /**
     * 注入逻辑层Bean
     */
    @Resource
    private ReplyService replyService;

    @Resource
    private CommentService commentService;

    @SysLog("回复评论")
    @PostMapping
    public Object addReply(@RequestBody Reply reply){
        reply.setUserId(getCurrUserId());
        //检测是否是回复的自己
        int isOwner = checkReplyIsOwner(reply);
        if(isOwner == -1){
            return error("不能回复自己");
        }
        replyService.save(reply);
        return success(reply);
    }

    /**
     * 检测该条回复是否是回复的自己
     * @param reply 回复实体信息
     */
    private int checkReplyIsOwner(Reply reply) {
        String userId = commentService.getCommentById(reply.getCommentId()).getUserId();
        if(reply.getFlag() == 0){
            ValidatorUtils.validateEntity(reply, ReplySave.class);
            if(reply.getUserId().equals(reply.getParentUserId())){
                return -1;
            }

            if(reply.getUserId().equals(userId)){
                return -1;
            }
        } else {
            ValidatorUtils.validateEntity(reply, ReplyCommentSave.class);
            if(reply.getUserId().equals(userId)){
                return -1;
            }
        }
        return 1;
    }
}

