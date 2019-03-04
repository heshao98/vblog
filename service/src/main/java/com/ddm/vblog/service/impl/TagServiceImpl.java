package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Tag;
import com.ddm.vblog.mapper.TagMapper;
import com.ddm.vblog.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    /**
     * 标签数据层
     */
    @Resource
    TagMapper tagMapper;

    /**
     * 实现获取到目前最热标签的id集合
     * @return
     */
    @Override
    public List<Integer> getHotTagId() { ;
        return tagMapper.getHotTagId();
    }
}
