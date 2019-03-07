package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.entity.Reply;
import com.ddm.vblog.mapper.CommentMapper;
import com.ddm.vblog.service.CommentService;
import org.springframework.stereotype.Service;

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

    /**
     * 获取文章的评论信息
     * @param id 文章id
     * @return
     */
    @Override
    public List<Comment> getCommentByArticle(String id) {
        List<Comment> commentByArticle = commentMapper.getCommentByArticle(id);
        commentByArticle.forEach(item -> {
            if(item.getReply().isEmpty() || item.getReply() == null){
                item.setReplyCount(0);
            } else{
                item.setReply(item.getReply().stream().sorted(Comparator.comparing(Reply::getCreateTime).reversed()).collect(Collectors.toList()));
                item.setReplyCount(item.getReply().size());
            }
        });
        return commentByArticle;
    }
}
