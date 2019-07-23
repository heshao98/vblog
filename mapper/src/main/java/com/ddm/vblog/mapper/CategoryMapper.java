package com.ddm.vblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddm.vblog.dto.category.CategoryDTO;
import com.ddm.vblog.entity.Category;

import java.util.List;

/**
 * <p>
 * 文章类别表 Mapper 接口
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获取文章分类详情
     * @return 文章分类dto集合
     */
    List<CategoryDTO> getAllDetail();

}
