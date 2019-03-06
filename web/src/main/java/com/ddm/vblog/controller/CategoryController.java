package com.ddm.vblog.controller;


import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 * 文章类别表 前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Controller
//@RequestMapping("/category")
@RequestMapping("/comments21")
public class CategoryController extends BaseController {


    @Resource
    private CategoryService categoryService;

}

