package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Config;
import com.ddm.vblog.mapper.ConfigMapper;
import com.ddm.vblog.service.ConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置信息表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}
