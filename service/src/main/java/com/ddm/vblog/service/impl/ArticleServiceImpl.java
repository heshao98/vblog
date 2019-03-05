package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Article;
import com.ddm.vblog.mapper.ArticleMapper;
import com.ddm.vblog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 注入文章数据层bean
     */
    @Resource
    private ArticleMapper articleMapper;

    /**
     * 获取到最热文章
     * @param iPage 分页对象
     * @return 当前页, 总页数, 当前页文章信息
     */
    @Override
    public IPage<Article> getHotArticle(IPage<Article> iPage) {
        return articleMapper.getHotArticle(iPage);
    }

    /**
     * 获取到最新的文章
     * @param iPage 分页对象
     * @return
     */
    @Override
    public IPage<Article> getNewArticle(IPage<Article> iPage) {
        return articleMapper.getNewArticle(iPage);
    }

    /**
     * 获取文章归档视图
     * @return
     */
    @Override
    public List<String> fileArticle() {
        return articleMapper.fileArticle();
    }

    /**
     * 获取该文章的详细信息
     * @param id 文章id
     * @return 文章信息
     */
    @Override
    public Article getArticleById(String id) {

        return articleMapper.getArticleById(id);
    }
}
