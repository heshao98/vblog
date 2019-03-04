package com.ddm.vblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddm.vblog.entity.Tag;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 获取最热标签id
     * @return
     */
    List<Integer> getHotTagId();
}
