package com.ddm.vblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddm.vblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
