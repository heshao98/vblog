package com.ddm.vblog.controller;


import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Slf4j
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
     * @return
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

}

