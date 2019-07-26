package com.ddm.vblog.controller;


import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/tags")
public class TagController extends BaseController {


    /**
     * 标签的service层
     */
    @Resource
    TagService tagService;

    /**
     * 获取最热的一组标签(4 个)
     */
    @SysLog("获取最热标签信息")
    @GetMapping(value = "/hot_tag")
    public Object getHotTag(){
        try {
            return success(tagService.listByIds(tagService.getHotTagId()));
        } catch (Exception e){
            throw new BaseException("系统异常,最热标签获取失败!");
        }
    }

    @SysLog("获取所有标签信息")
    @GetMapping
    public Object getAllTag(){
        return success(tagService.getAllTag());
    }

    @SysLog("根据标签id获取标签详情")
    @GetMapping("/detail/{id}")
    public Object getTagById(@PathVariable @Min(value = 1,
            message = "参数类型不正确,或者参数范围不正确!") Integer id){
        return success(tagService.getById(id));
    }

}

