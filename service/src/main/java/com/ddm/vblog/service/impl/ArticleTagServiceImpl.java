package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.ArticleTag;
import com.ddm.vblog.mapper.ArticleTagMapper;
import com.ddm.vblog.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
