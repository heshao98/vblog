package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.ArticleTag;
import com.ddm.vblog.mapper.ArticleTagMapper;
import com.ddm.vblog.service.ArticleTagService;
import com.ddm.vblog.utils.Stringer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public int saveArticleTagBatch(String tagStr, String articleId) {
        if(Stringer.isBatchNullOrEmpty(tagStr,articleId)){
            return -1;
        }

        String[] tagIds = tagStr.split(",");
        if(tagIds.length == 0){
            return 0;
        }

        List<ArticleTag> articleTagList = new ArrayList<>();
        for (String tagId : tagIds) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleId);
            articleTag.setTagId(Integer.valueOf(tagId));
            articleTagList.add(articleTag);
        }
        this.saveBatch(articleTagList);
        return 1;
    }
}
