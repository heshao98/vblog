package com.ddm.vblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddm.vblog.entity.Tag;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取到目前最热标签的id集合
     * @return
     */
    List<Integer> getHotTagId();
}
