package com.ddm.vblog.dto.article;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author ddm
 * @Date: 2019/7/28 20:17
 * @Description: 发布文章 接受参数类
 */
@Data
public class AddArticleParamDTO {

    /**
     * 文章内容txt
     */
    @NotBlank(message = "文章内容不能为空！")
    private String content;

    /**
     * 文章摘要
     */
    @NotBlank(message = "文章摘要不能为空！")
    private String summary;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空！")
    private String title;

    /**
     * 文章分类
     */
    @Min(value = 0,message = "数据类型错误！")
    private Integer category;

    /**
     * 标签id
     */
    @NotBlank(message = "标签类型不能为空！")
    private String tagIds;

    /**
     * 文章发布者id
     */
    @NotBlank(message = "文章发布者id不能为空")
    private String userId;
}
