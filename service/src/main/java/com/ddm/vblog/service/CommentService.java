package com.ddm.vblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddm.vblog.entity.Comment;

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
     * @return
     */
    List<Comment> getCommentByArticle(String id);
}
