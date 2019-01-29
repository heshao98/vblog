package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Article;
import mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import service.ArticleService;

import java.util.Collection;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
