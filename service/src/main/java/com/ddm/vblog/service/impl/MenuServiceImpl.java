package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Menu;
import com.ddm.vblog.mapper.MenuMapper;
import com.ddm.vblog.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
