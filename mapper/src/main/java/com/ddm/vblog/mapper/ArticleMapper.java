package com.ddm.vblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ddm.vblog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 获取到最热文章
     * @param iPage 分页对象
     * @return 当前页, 总页数, 当前页文章信息
     */
    IPage<Article> getHotArticle(@Param("iPage") IPage<Article> iPage);

    /**
     * 获取到最新的文章信息
     * @param iPage 分页对象
     * @return
     */
    IPage<Article> getNewArticle(@Param("iPage") IPage<Article> iPage);

    /**
     * 获取文章归档视图
     * @return
     */
    List<String> fileArticle();
}
