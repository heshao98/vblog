package com.ddm.vblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.entity.Tag;
import com.ddm.vblog.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@RestController
@RequestMapping("/tag")
public class TagController extends BaseController {


    /**
     * 标签的service层
     */
    @Resource
    TagService tagService;

    @GetMapping(value = "/hot_tag")
    public Object getHotTag(){
        return success(tagService.listByIds(tagService.getHotTagId()));
    }

}

