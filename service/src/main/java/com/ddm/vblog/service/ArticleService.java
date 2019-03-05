package com.ddm.vblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ddm.vblog.entity.Article;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取到最热文章
     * @param iPage 分页对象
     * @return 当前页, 总页数, 当前页文章信息
     */
    IPage<Article> getHotArticle(IPage<Article> iPage);

    /**
     * 获取到最新的文章
     * @return 当前页, 总页数, 当前页最新文章信息
     */
    IPage<Article> getNewArticle(IPage<Article> iPage);

    /**
     * 获取文章归档视图
     * @return
     */
    List<String> fileArticle();
}
