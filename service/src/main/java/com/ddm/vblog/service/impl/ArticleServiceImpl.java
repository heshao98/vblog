package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Article;
import com.ddm.vblog.entity.ArticleView;
import com.ddm.vblog.mapper.ArticleMapper;
import com.ddm.vblog.service.ArticleService;
import com.ddm.vblog.service.ArticleViewService;
import com.ddm.vblog.shiro.ShiroSubjectManager;
import com.ddm.vblog.utils.LocalDateTimeUtils;
import com.ddm.vblog.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 注入文章数据层bean
     */
    @Resource
    private ArticleMapper articleMapper;

    /**
     * 注入文章阅读数逻辑层bean
     */
    @Resource
    private ArticleViewService articleViewService;

    @Resource
    private RedisUtil redisUtil;

    private static final String REDIS_VIEW_COUNT = "ArticleViewCount::";

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
     * @return 返回最新文章数据
     */
    @Override
    public IPage<Article> getNewArticle(IPage<Article> iPage) {
        return articleMapper.getNewArticle(iPage);
    }

    /**
     * 获取文章归档视图
     * @return 归档数据
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
    @Cacheable(value = "Article",key = "#id")
    @Override
    public Article getArticleById(String id) {
        return articleMapper.getArticleById(id);
    }

    /**
     * 增加文章的查看数
     * @param id 文章id
     * @param ip 发起请求的主机ip
     * @return 返回当前文章查看数
     */
    @Override
    public Integer addArticleViewCount(String id, String ip) {
        try {
            String token = "";
            if(redisUtil.exists(REDIS_VIEW_COUNT + id)){
                if(ShiroSubjectManager.isLogin()){
                    token = ShiroSubjectManager.getCurrUserName();
                } else{
                    token = ip;
                }
                if(redisUtil.hmHasKey(REDIS_VIEW_COUNT + id, token)){
                    return redisUtil.hmCountKey(REDIS_VIEW_COUNT + id).intValue();
                } else{
                    redisUtil.hmput(REDIS_VIEW_COUNT + id,token, LocalDateTimeUtils.now()+":"+id);
                    return redisUtil.hmCountKey(REDIS_VIEW_COUNT + id).intValue();
                }
            } else{
                Map<String,String> map = new HashMap<>(10);
                map.put(token,LocalDateTimeUtils.now()+":"+id);
                redisUtil.hmset(REDIS_VIEW_COUNT + id,map);
                return redisUtil.hmCountKey(REDIS_VIEW_COUNT + id).intValue();
            }
        } catch (Exception e){
            e.printStackTrace();
            log.info("redis获取数据异常,从数据库获取数据.");
            return articleViewService.count(new QueryWrapper<ArticleView>().eq("article_id", id));
        }
    }

    /**
     * 加载首页文章数据
     * @param page 分页信息
     * @param date 日期
     * @param tag 标签
     * @return 分页信息、文章列表
     */
    @SuppressWarnings("unchecked")
    @Override
    public IPage<Article> loadHomeArticle(Page page, String date, String tag) {
        IPage<Article> iPage = articleMapper.selectPage(page,null);
        iPage.getRecords().forEach(item -> item.setViewNum(redisUtil.hmCountKey(REDIS_VIEW_COUNT + item.getId()).intValue()));
        return iPage;
    }

    /**
     * 获取一个文章的评论数
     * @param articleId 文章id
     * @return 文章评论数
     */
    @Override
    public int getCommentCount(String articleId) {
        return articleMapper.selectOne(new QueryWrapper<Article>().eq("id",articleId)).getCommentNum();
    }
}
