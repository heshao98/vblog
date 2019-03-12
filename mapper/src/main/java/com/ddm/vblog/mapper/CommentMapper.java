package com.ddm.vblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddm.vblog.entity.Comment;
import com.ddm.vblog.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> getCommentByArticle(@Param("articleId") String articleId, @Param("page") Page<Comment> page);

}
