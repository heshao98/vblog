package com.ddm.vblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddm.vblog.entity.ArticleTag;

/**
 * <p>
 * 文章标签表 服务类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 保存文章和标签的关联关系
     * @author heShaoHua
     * @describe 暂无
     * @param tagIds 标签id字符串(逗号分隔)
     * @param articleId 文章id
     * @updateInfo 暂无
     * @date 2019/8/2 17:23
     * @return 是否成功
     */
    int saveArticleTagBatch(String tagIds, String articleId);

}
