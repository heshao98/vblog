package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ddm.vblog.validation.group.article.ArticleSave;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@TableName("vblog_article")
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 作者昵称
     */
    @NotNull(message = "作者昵称不能为空",groups = {ArticleSave.class})
    private String nickname;

    /**
     * 文章标题
     */
    @NotNull(message = "标题不能为空",groups = {ArticleSave.class})
    private String title;

    /**
     * 文章摘要
     */
    @NotNull(message = "文章摘要不能为空",groups = {ArticleSave.class})
    private String summary;

    /**
     * 文章内容txt
     */
    @NotNull(message = "文章内容不能为空",groups = {ArticleSave.class})
    private String content;

    /**
     * 文章内容html
     */
    @NotNull(message = "内容不能为空",groups = {ArticleSave.class})
    private String contentHtml;

    /**
     * 浏览数
     */
    private Integer viewNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 文章标签
     */
    @NotNull(message = "标签不能为空",groups = {ArticleSave.class})
    private String tagIds;

    /**
     * 文章分类ID
     */
    @NotNull(message = "文章分类不能为空",groups = {ArticleSave.class})
    private Integer categoryId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 文章的作者信息
     */
    @TableField(exist = false)
    private User user;

    /**
     * 文章分类信息
     */
    @TableField(exist = false)
    private Category category;

    /**
     * 该文章的标签
     */
    @TableField(exist = false)
    private List<Tag> tags;

}
