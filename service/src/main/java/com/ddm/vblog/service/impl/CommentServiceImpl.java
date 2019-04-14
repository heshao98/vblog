package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Article;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.entity.Reply;
import com.ddm.vblog.exception.comment.CommentException;
import com.ddm.vblog.mapper.CommentMapper;
import com.ddm.vblog.page.Page;
import com.ddm.vblog.service.ArticleService;
import com.ddm.vblog.service.CommentService;
import com.ddm.vblog.utils.RedisUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private ArticleService articleService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取文章的评论信息
     * @param id 文章id
     * @param page 分页信息
     * @return 评论信息集合
     */
    @Override
    public List<Comment> getCommentByArticle(String id, Page<Comment> page) {
        List<Comment> list = redisUtil.getList(Comment.class, "Comment::" + id);
        if(!CollectionUtils.isEmpty(list)){
            return list;
        }
        List<Comment> commentByArticle = commentMapper.getCommentByArticle(id,page);
        commentByArticle.forEach(item -> {
            if(!item.getReply().isEmpty()){
                item.setReply(item.getReply().stream().sorted(Comparator.comparing(Reply::getCreateTime).reversed()).collect(Collectors.toList()));
            }
        });
        redisUtil.lset("Comment::" + id,commentByArticle);
        return commentByArticle;
    }

    /**
     * 评论文章
     * @param comment 评论信息
     * @return 评论信息是否添加成功
     */
    @Override
    public boolean save(Comment comment) throws CommentException {
        int commentInsert = commentMapper.insert(comment);
        if(commentInsert <= 0){
            throw new CommentException("评论失败,请稍后重试!");
        }
        redisUtil.lLeftPush(comment.getArticleId(),comment);
        return true;
    }
}
