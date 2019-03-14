package com.ddm.vblog.timer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddm.vblog.entity.ArticleView;
import com.ddm.vblog.service.ArticleViewService;
import com.ddm.vblog.utils.RedisUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 定时器
 * @Date:2019/3/14 17:27
 * @Author ddm
 **/
@Component
public class Timer {

    public static final String REDIS_VIEW_COUNT = "ArticleViewCount::*";

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ArticleViewService articleViewService;

    @Scheduled(fixedRate = 10000)
    public void saveViewCount(){
        System.out.println("开始执行......");
        Set<String> keys = redisUtil.keys(REDIS_VIEW_COUNT);
        keys.forEach(item -> {
            Map<String, String> hmget = redisUtil.hmget(item);
            Set<String> strings = hmget.keySet();
            List<ArticleView> views = articleViewService.list(new QueryWrapper<ArticleView>().eq("article_id", item.substring(item.lastIndexOf(":")+1,item.length())));
            List<String> accounts = views.stream().map(ArticleView::getUserId).collect(Collectors.toList());
            List<String> difference = strings.stream().filter(s -> !accounts.contains(s)).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(difference)){
                difference.forEach(diff -> {
                    ArticleView articleView = new ArticleView();
                    articleView.setArticleId(item.substring(item.lastIndexOf(":")+1));
                    articleView.setCreateTime(LocalDateTime.now());
                    articleView.setUserId(diff);
                });
            }
        });

    }
}
