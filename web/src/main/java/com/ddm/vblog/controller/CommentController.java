package com.ddm.vblog.controller;


import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author heshaohua
 * @since 2019-01-29
 */
@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController {

    /**
     * 注入评论逻辑层bean
     */
    @Resource
    private CommentService commentService;

    @SysLog("获取文章评论")
    @GetMapping("/article/{id}")
    public Object getArticleComment(@PathVariable String id) {
        try {
            return success(commentService.getCommentByArticle(id));
        } catch (Exception e) {
            throw new BaseException("系统异常,获取文章评论失败!", e);
        }
    }

    /**
     * 给一个文章添加评论
     * @return
     */
    @SysLog("评论文章")
    @PostMapping
    public Object addArticleComment(@RequestBody Comment comment){
        try {
            User user = currUser();
            comment.setAvatar(user.getAvatar());
            comment.setUserId(user.getId());
            comment.setNickname(user.getNickname());
            commentService.save(comment);
            user = null;
            return success(comment);
        } catch (Exception e){
            throw new BaseException("系统异常,评论失败!");
        }
    }
}

