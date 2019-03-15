package com.ddm.vblog.timer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddm.vblog.entity.ArticleView;
import com.ddm.vblog.service.ArticleViewService;
import com.ddm.vblog.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 定时器
 * @Date:2019/3/14 17:27
 * @Author ddm
 **/
@Slf4j
@Component
public class Timer {

    public static final String REDIS_VIEW_COUNT = "ArticleViewCount::*";

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ArticleViewService articleViewService;

    @Scheduled(cron = "0 0 */1 * * ?")
    public void saveViewCount(){
        log.info("文章查看量持久化任务开始执行.....");
        //根据表达式获取redis中所有key
        Set<String> keys = redisUtil.keys(REDIS_VIEW_COUNT);
        keys.forEach(item -> {
            String articleId = item.substring(item.lastIndexOf(":") + 1, item.length());
            //取到每个key对应的Hash值
            Map<String, String> hmget = redisUtil.hmget(item);
            Set<String> strings = hmget.keySet();
            //获取当前所有文章的所有查看人信息
            List<ArticleView> views = articleViewService.list(new QueryWrapper<ArticleView>().eq("article_id", articleId));
            if(!CollectionUtils.isEmpty(views)){
                List<String> accounts = views.stream().map(ArticleView::getUserId).collect(Collectors.toList());
                //取出redis中当前文章的查看人信息和数据库中的当前文章查看人信息的差集
                List<String> difference = strings.stream().filter(s -> !accounts.contains(s)).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(difference)){
                    articleViewService.saveBatch(this.packageArticleViews(difference,articleId));
                }
            } else{
                articleViewService.saveBatch(this.packageArticleViews(new ArrayList<>(strings),articleId));
            }
        });
    }

    private List<ArticleView> packageArticleViews(List<String> users,String articleId){
        List<ArticleView> saveArticleViews = new ArrayList<>();
        users.forEach(item -> {
            ArticleView articleView = new ArticleView();
            articleView.setArticleId(articleId);
            articleView.setCreateTime(LocalDateTime.now().minusHours(1L));
            articleView.setUserId(item);
            saveArticleViews.add(articleView);
        });
        return saveArticleViews;
    }
}
