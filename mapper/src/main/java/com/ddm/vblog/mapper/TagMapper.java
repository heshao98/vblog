package com.ddm.vblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddm.vblog.dto.tag.TagDTO;
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

    /**
     * 获取所有标签信息以及每个标签有多少文章
     * @return 文章信息集合
     */
    List<TagDTO> selectAll();
}
