package com.ddm.vblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddm.vblog.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取某个用户的所有角色以及角色下所对应的权限
     * @param username
     * @return
     */
    List<Role> getAuthorization(String username);
}
