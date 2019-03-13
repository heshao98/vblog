package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "ArticleView{" +
        "id=" + id +
        ", userId=" + userId +
        ", articleId=" + articleId +
        "}";
    }
}
