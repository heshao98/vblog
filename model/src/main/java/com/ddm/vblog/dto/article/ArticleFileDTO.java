package com.ddm.vblog.dto.article;

import lombok.Data;

/**
 * @Description
 * @Date:2019/7/26 15:16
 * @Author ddm
 **/
@Data
public class ArticleFileDTO {

    /**
     * 归档后的文章日期
     */
    private String date;

    /**
     * 文章数量
     */
    private Integer articleCount;
}
