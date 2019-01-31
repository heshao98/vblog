package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Category;
import com.ddm.vblog.mapper.CategoryMapper;
import com.ddm.vblog.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章类别表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
