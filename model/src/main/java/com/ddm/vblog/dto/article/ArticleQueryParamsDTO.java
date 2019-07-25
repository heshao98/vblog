package com.ddm.vblog.dto.article;

import lombok.Data;

/**
 * @Description 文章查询接收查询参数的DTO
 * @Date:2019/7/25 17:10
 * @Author heshaohua
 **/
@Data
public class ArticleQueryParamsDTO {

    /**
     * 文章标签id
     */
    private Integer tagId;

    /**
     * 文章分类id
     */
    private Integer categoryId;

    /**
     * 文章发布日期
     */
    private String date;

}
