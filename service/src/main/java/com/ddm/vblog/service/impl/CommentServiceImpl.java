package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Article;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.entity.Reply;
import com.ddm.vblog.exception.comment.CommentException;
import com.ddm.vblog.mapper.CommentMapper;
import com.ddm.vblog.page.Page;
import com.ddm.vblog.service.ArticleService;
import com.ddm.vblog.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    /**
     * 获取文章的评论信息
     * @param id 文章id
     * @param page
     * @return
     */
    @Override
    public List<Comment> getCommentByArticle(String id, Page<Comment> page) {
        List<Comment> commentByArticle = commentMapper.getCommentByArticle(id,page);
        commentByArticle.forEach(item -> {
            if(!item.getReply().isEmpty()){
                item.setReply(item.getReply().stream().sorted(Comparator.comparing(Reply::getCreateTime).reversed()).collect(Collectors.toList()));
            }
        });
        return commentByArticle;
    }

    /**
     * 评论文章
     * @param comment 评论信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(Comment comment) throws CommentException {
        int commentInsert = commentMapper.insert(comment);
        Article article = articleService.getOne(new QueryWrapper<Article>().select("id","comment_num","version").eq("id",comment.getArticleId()));
        Integer commentNum = article.getCommentNum();
        article.setCommentNum(++commentNum);
        boolean articleInsert = articleService.updateById(article);
        if(commentInsert <= 0 || !articleInsert){
            throw new CommentException("评论失败,请稍后重试!");
        }
        return true;
    }
}
