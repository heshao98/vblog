package com.ddm.vblog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.entity.Article;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Slf4j
@RestController
@RequestMapping("/articles")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

    @SysLog("获取最热文章")
    @GetMapping("hot")
    public Object getHotArticle() {
        try {
            IPage<Article> iPage = new Page<Article>();
            iPage.setCurrent(0);
            iPage.setSize(4);
            return success(articleService.getHotArticle(iPage).getRecords());
        } catch (Exception e) {
            throw new BaseException("系统异常,获取最热文章失败!");
        }
    }

    @SysLog("获取最新文章")
    @GetMapping("new")
    public Object getNewArticle() {
        try {
            IPage<Article> iPage = new Page<Article>();
            iPage.setCurrent(0);
            iPage.setSize(4);
            return success(articleService.getNewArticle(iPage).getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("系统异常,获取最热文章失败!");
        }
    }

    @SysLog("文章归档视图")
    @GetMapping("file")
    public Object fileArticle() {
        try {
            return success(articleService.fileArticle());
        } catch (Exception e) {
            throw new BaseException("系统异常,文章归档失败!");
        }
    }

    /**
     * 首页加载文章数据
     *
     * @return
     */
    @GetMapping("/")
    public Object loadArticle(Page page) {
        try {
            return success(articleService.page(page));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("系统异常,文章加载失败!");
        }
    }

    /**
     * 根据id获取某个文章
     *
     * @return
     */
    @SysLog("根据id获取文章")
    @GetMapping("view/{id}")
    public Object getArticleById(@PathVariable String id, HttpServletRequest httpServletRequest) {
        try {
            Article articleById = articleService.getArticleById(id);
            articleById.setViewNum(articleService.addArticleViewCount(id,httpServletRequest.getRemoteAddr()));
            return success(articleById);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("系统异常,获取文章失败!");
        }
    }


}

