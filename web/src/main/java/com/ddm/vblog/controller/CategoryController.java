package com.ddm.vblog.controller;


import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * <p>
 * 文章类别表 前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Validated
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    /**
     * 注入文章分类逻辑层Bean
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 获取所有文章的分类详情
     * @return 所有文章分类的详情
     */
    @GetMapping("/detail")
    public Object getAllDetail(){
        return success(categoryService.getAllDetail());
    }

    @GetMapping("/detail/{id}")
    public Object getCategoryById(@PathVariable @Min(value = 1,
            message = "参数类型不正确,或者参数范围不正确!") Integer id){
        return success(categoryService.getById(id));
    }

}

