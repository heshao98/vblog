package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.UserRole;
import com.ddm.vblog.mapper.UserRoleMapper;
import com.ddm.vblog.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
