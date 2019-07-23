package com.ddm.vblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.page.Page;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface CommentService extends IService<Comment> {

    /**
     * 获取文章的评论信息
     * @param id 文章id
     * @param page 分页信息
     * @return 评论信息
     */
    List<Comment> getCommentByArticle(String id, Page<Comment> page);

    /**
     * 评论文章
     * @param comment 要保存的评论信息
     * @return 是否保存成功
     */
    @Override
    boolean save(Comment comment);

    /**
     * 获取文章评论数
     * @param id 文章id
     * @return 文章的评论数
     */
    int getArticleCommentCount(String id);

    /**
     * 根据评论id获取一条评论信息
     * 如果传入的id为空 则返回null
     * @param id 评论id
     * @return 评论信息
     */
    Comment getCommentById(String id);
}
