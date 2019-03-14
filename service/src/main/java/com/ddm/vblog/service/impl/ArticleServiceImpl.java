package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Article;
import com.ddm.vblog.mapper.ArticleMapper;
import com.ddm.vblog.service.ArticleService;
import com.ddm.vblog.shiro.ShiroSubjectManager;
import com.ddm.vblog.utils.LocalDateTimeUtils;
import com.ddm.vblog.utils.RedisUtil;
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
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 注入文章数据层bean
     */
    @Resource
    private ArticleMapper articleMapper;

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
    @Cacheable(value = "Article",key = "#id")
    @Override
    public Article getArticleById(String id) {
        return articleMapper.getArticleById(id);
    }

    /**
     * 增加文章的查看数
     * @param id 文章id
     * @return 返回当前文章查看数
     */
    @Override
    public Integer addArticleViewCount(String id) {
        try {
            if(redisUtil.exists(REDIS_VIEW_COUNT + id)){
                if(redisUtil.hmHasKey(REDIS_VIEW_COUNT + id, ShiroSubjectManager.getCurrUserName())){
                    return redisUtil.hmCountKey(REDIS_VIEW_COUNT + id).intValue();
                } else{
                    redisUtil.hmput(REDIS_VIEW_COUNT + id,ShiroSubjectManager.getCurrUserName(), LocalDateTimeUtils.now()+":"+id);
                    return redisUtil.hmCountKey(REDIS_VIEW_COUNT + id).intValue();
                }
            } else{
                Map<String,String> map = new HashMap<String,String>();
                map.put(ShiroSubjectManager.getCurrUserName(),LocalDateTimeUtils.now()+":"+id);
                redisUtil.hmset(REDIS_VIEW_COUNT + id,map);
                return redisUtil.hmCountKey(REDIS_VIEW_COUNT + id).intValue();
            }
        } catch (Exception e){
            System.out.println("redis挂了.......");
            return 0;
        }

    }
}
