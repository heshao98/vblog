package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.RoleMenu;
import com.ddm.vblog.mapper.RoleMenuMapper;
import com.ddm.vblog.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
