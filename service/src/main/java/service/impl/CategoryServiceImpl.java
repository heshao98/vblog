package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Category;
import mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import service.CategoryService;

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
