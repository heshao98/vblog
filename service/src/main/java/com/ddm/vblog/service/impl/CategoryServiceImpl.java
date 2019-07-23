package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.dto.category.CategoryDTO;
import com.ddm.vblog.entity.Category;
import com.ddm.vblog.mapper.CategoryMapper;
import com.ddm.vblog.service.CategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章类别表 服务实现类
 * </p>
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 注入文章分类的dao层Bean
     */
    @Resource
    private CategoryMapper categoryMapper;

    @Cacheable("category")
    @SuppressWarnings("unchecked")
    @Override
    public List<CategoryDTO> getAllDetail() {
        List<CategoryDTO> allDetailList = categoryMapper.getAllDetail();

        //如果查询出来的分类数据为空，则返回空集合
        if(CollectionUtils.isEmpty(allDetailList)){
            return new ArrayList<>();
        }
        return allDetailList;
    }
}
