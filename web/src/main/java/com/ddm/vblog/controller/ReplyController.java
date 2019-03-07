package com.ddm.vblog.controller;


import com.ddm.vblog.annotation.SysLog;
import com.ddm.vblog.base.BaseController;
import com.ddm.vblog.entity.Reply;
import com.ddm.vblog.exception.BaseException;
import com.ddm.vblog.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DindDangMao
 * @since 2019-03-06
 */
@Slf4j
@RestController
@RequestMapping("/replys")
public class ReplyController extends BaseController {

    /**
     * 注入逻辑层Bean
     */
    @Resource
    private ReplyService replyService;

    @SysLog("回复评论")
    @PostMapping
    public Object addReply(@RequestBody Reply reply){
        try {
            reply.setUserId(getCurrUserId());
            replyService.save(reply);
            return success(reply);
        } catch (Exception e){
            throw new BaseException("系统异常,回复失败!",e);
        }
    }
}

