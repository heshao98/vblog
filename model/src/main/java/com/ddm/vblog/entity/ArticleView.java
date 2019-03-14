package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author DindDangMao
 * @since 2019-03-13
 */
@Data
@ToString
@TableName("vblog_article_view")
public class ArticleView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户id 或者是 ip地址
     */
    private String userId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
