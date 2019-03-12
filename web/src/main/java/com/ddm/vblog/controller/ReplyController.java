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

    @SysLog("回复")
    @PostMapping
    public Object addReply(@RequestBody Reply reply){
        try {
            reply.setUserId(getCurrUserId());
            if(reply.getFlag() == 0){
                ValidatorUtils.validateEntity(reply, ReplySave.class);
                if(reply.getUserId().equals(reply.getParentUserId())){
                    throw new ReplyException("不能回复自己");
                }
            } else{
                ValidatorUtils.validateEntity(reply, ReplyCommentSave.class);
                if(reply.getUserId().equals(commentService.getOne(new QueryWrapper<Comment>().select("user_id").eq("id",reply.getCommentId())).getUserId())){
                    throw new ReplyException("不能回复自己");
                }
            }
            replyService.save(reply);
            return success(reply);
        } catch (ReplyException e){
            throw e;
        }
        catch (Exception e){
            throw new BaseException("系统异常,回复失败!",e);
        }
    }
}

