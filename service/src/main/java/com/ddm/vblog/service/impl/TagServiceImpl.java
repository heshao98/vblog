package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.dto.tag.TagDTO;
import com.ddm.vblog.entity.Tag;
import com.ddm.vblog.mapper.TagMapper;
import com.ddm.vblog.service.TagService;
import com.ddm.vblog.utils.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * 注入redis工具类Bean
     */
    @Resource
    RedisUtil redisUtil;

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
    public List<Integer> getHotTagId() {
        return tagMapper.getHotTagId();
    }

    @Override
    public List<TagDTO> getAllTag() {
        List<TagDTO> tagList;
        if(CollectionUtils.isEmpty(redisUtil.getList(TagDTO.class,"Tag"))){
           tagList = tagMapper.selectAll();
            if(CollectionUtils.isEmpty(tagList)){
                return new ArrayList<>();
            }
            redisUtil.lset("Tag",tagList);
            return tagList;
        }
        return redisUtil.getList(TagDTO.class,"Tag");
    }

    @Override
    public List<Integer> getAllTagIdList() {
        //编写sql表达式
        LambdaQueryWrapper<Tag> selectAllTagId = new QueryWrapper<Tag>().lambda()
                .select(Tag::getId);

        List<Tag> idList = tagMapper.selectList(selectAllTagId);

        if(CollectionUtils.isEmpty(idList)){
            return new ArrayList<>();
        }
        return idList.stream().map(Tag::getId).collect(Collectors.toList());
    }
}
