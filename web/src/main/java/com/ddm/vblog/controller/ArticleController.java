package com.ddm.vblog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.dto.article.AddArticleParamDTO;
import com.ddm.vblog.dto.article.ArticleQueryParamsDTO;
import com.ddm.vblog.entity.Article;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.ArticleService;
import com.ddm.vblog.service.TagService;
import com.ddm.vblog.utils.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 * @author DindDangMao
 * @since 2019-01-29
 */
@Slf4j
@RestController
@RequestMapping("/articles")
public class ArticleController extends BaseController {

    /**
     * 注入文章逻辑层
     */
    @Resource
    private ArticleService articleService;

    /**
     * 注入标签逻辑层Bean
     */
    @Resource
    private TagService tagService;

    @SysLog("获取最热文章")
    @GetMapping("/hot")
    public Object getHotArticle() {
        IPage<Article> iPage = new Page<>();
        iPage.setCurrent(0);
        iPage.setSize(4);
        return success(articleService.getHotArticle(iPage).getRecords());
    }

    @SysLog("获取最新文章")
    @GetMapping("/new")
    public Object getNewArticle() {
        try {
            IPage<Article> iPage = new Page<>();
            iPage.setCurrent(0);
            iPage.setSize(4);
            return success(articleService.getNewArticle(iPage).getRecords());
        } catch (Exception e) {
            throw new BaseException("系统异常,获取最热文章失败!");
        }
    }

    @SysLog("文章归档视图")
    @GetMapping("/file")
    public Object fileArticle() {
        try {
            return success(articleService.fileArticle());
        } catch (Exception e) {
            throw new BaseException("系统异常,文章归档失败!");
        }
    }

    /**
     * 首页加载文章数据
     * @return 首页的文章数据信息
     */
    @SysLog("文章首页视图加载")
    @GetMapping
    public Object loadArticle(com.ddm.vblog.page.Page<Article> page,
                              ArticleQueryParamsDTO queryParams) {
        try {
            return success(articleService.loadHomeArticle(page,queryParams));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("系统异常,文章加载失败!",e);
        }
    }

    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<>();
    }

    /**
     * 根据id获取某个文章
     * @return 根据id获取的文章信息
     */
    @SysLog("根据id获取文章")
    @GetMapping("/view/{id}")
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

    @SysLog("发布文章")
    @PostMapping
    public Object publishArticle(AddArticleParamDTO addArticle){
        ValidatorUtils.validateEntity(addArticle);

        String tagIdStr = addArticle.getTagIds();
        List<String> tagIds = Arrays.asList(tagIdStr.split(","));

        boolean tagIdListIsNull = CollectionUtils.isEmpty(tagIds);
        if(tagIdListIsNull){
            return error("标签id不能为空！");
        }

        List<Integer> allTagIdList = tagService.getAllTagIdList();
        List<Integer> testTagId = allTagIdList.stream().filter(id -> !tagIds.contains(String.valueOf(String.valueOf(id)))).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(testTagId)){
            return error("标签参数范围不正确");
        }
        int result = articleService.addArticle(addArticle);
        return null;
    }
}

