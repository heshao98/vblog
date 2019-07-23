package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.common.Common;
import com.ddm.vblog.entity.Article;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.entity.Reply;
import com.ddm.vblog.exception.comment.CommentException;
import com.ddm.vblog.mapper.CommentMapper;
import com.ddm.vblog.page.Page;
import com.ddm.vblog.service.ArticleService;
import com.ddm.vblog.service.CommentService;
import com.ddm.vblog.utils.RedisUtil;
import com.ddm.vblog.utils.Stringer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    private RedisUtil redisUtil;

    @Resource
    private ArticleService articleService;

    private static final int PAGE_NUM = 5;

    /**
     * 获取文章的评论信息
     * @param id 文章id
     * @param page 分页信息
     * @return 评论信息集合
     */
    @Override
    public List<Comment> getCommentByArticle(String id, Page<Comment> page) {
        if(page.getCurrent() == 1){
            List<Comment> list = redisUtil.getList(Comment.class,"Comment::" + id);
            if(CollectionUtils.isNotEmpty(list)){
                return list;
            }
        }
        List<Comment> commentByArticle = commentMapper.getCommentByArticle(id,page);
        if(CollectionUtils.isNotEmpty(commentByArticle)){
            //将评论下面的子评论进行排序
            commentByArticle.forEach(item -> {
                if(!item.getReply().isEmpty()){
                    item.setReply(item.getReply().stream()
                            .sorted(Comparator.comparing(Reply::getCreateTime).reversed())
                            .collect(Collectors.toList()));
                }
            });
            if(page.getCurrent() == 1){
                redisUtil.lset(Common.COMMENT_REDIS_PREFIX + id,commentByArticle);
            }
        } else{
            commentByArticle = new ArrayList<>();
        }
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
            throw new CommentException("系统异常,评论失败！");
        }
        //获取当前文章的评论数，并且在当前数量基础上 + 1
        int count = articleService.getCommentCount(comment.getArticleId());
        Article article = new Article();
        article.setId(comment.getArticleId());
        article.setCommentNum(count + 1);
        articleService.updateById(article);
        String rPrefix = "Comment::" + comment.getArticleId();
        if(redisUtil.getListSize(rPrefix) < PAGE_NUM){
            redisUtil.lLeftPush(rPrefix,comment);
        } else{
            redisUtil.leftPushRightPop(rPrefix, comment);
        }
        return true;
    }

    /**
     * 获取文章评论数
     * @param id 文章id
     * @return 文章的评论数
     */
    @Override
    public int getArticleCommentCount(String id) {
        if(Stringer.isNullOrEmpty(id)){
            throw new NullPointerException("文章id不能为空!");
        }
        return commentMapper.selectCount(new QueryWrapper<Comment>().eq("article_id",id));
    }

    @Override
    public Comment getCommentById(String id) {
        if(Stringer.isNullOrEmpty(id)){
            return null;
        }

        //构造出sql表达式
        LambdaQueryWrapper<Comment> getCommentByIdQw = new QueryWrapper<Comment>().lambda()
                .eq(Comment::getId, id);

        List<Comment> comments = this.list(getCommentByIdQw);
        if(CollectionUtils.isNotEmpty(comments)){
            return comments.get(0);
        }
        return null;
    }
}
