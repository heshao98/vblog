package com.ddm.vblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddm.vblog.dto.category.CategoryDTO;
import com.ddm.vblog.entity.Category;

import java.util.List;

/**
 * <p>
 * 文章类别表 服务类
 * </p>
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取到所有文章分类的详情信息
     * @author ddm
     * @Description 无
     * @update （无）
     * @version 1.0
     * @date 2019/7/17 22:14
     * @return 所有文章分类的详情信息集合
     */
    List<CategoryDTO> getAllDetail();
}
