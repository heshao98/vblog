package com.ddm.vblog.article;

import com.ddm.vblog.entity.Article;
import com.ddm.vblog.service.ArticleService;
import com.ddm.vblog.web.WebApplicationTests;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Date:2019/4/10 11:43
 * @Author heshaohua
 **/
public class ArticleTest extends WebApplicationTests {

    @Resource
    private ArticleService articleService;

    /**
     * 根据文章id获取文章的详情信息
     */
    @Test
    public void testGetArticleById(){
        Article articleById = articleService.getArticleById("1");
        System.out.println(articleById.toString());
    }


    public static void main(String[] args) {
        String a = "a";
        String a2 = "a";
        System.out.println(a.equals(a2));
        System.out.println(a == a2);
    }
}
