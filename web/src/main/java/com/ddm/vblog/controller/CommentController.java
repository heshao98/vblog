package com.ddm.vblog.controller;

import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.entity.User;
import com.ddm.vblog.page.Page;
import com.ddm.vblog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
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
    @GetMapping("/article/{id}/{comment_curr}")
    public Object getArticleComment(@PathVariable String id,@PathVariable("comment_curr") Integer commentCurr) {
        Page<Comment> page = new Page<>();
        page.setSize(5);
        page.setCurrent(commentCurr);
        page.setList(commentService.getCommentByArticle(id,page));
        return success(page);
    }

    /**
     * 给一个文章添加评论
     * @return 文章的评论信息
     */
    @SysLog("评论文章")
    @PostMapping
    public Object addArticleComment(@RequestBody Comment comment){
        User user = currUser();
        comment.setAvatar(user.getAvatar());
        comment.setUserId(user.getId());
        comment.setNickname(user.getNickname());
        commentService.save(comment);
        return success(comment);
    }
}

