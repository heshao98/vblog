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
     * @param page
     * @return
     */
    List<Comment> getCommentByArticle(String id, Page<Comment> page);

    /**
     * 评论文章
     * @param comment
     * @return
     */
    @Override
    boolean save(Comment comment);
}
