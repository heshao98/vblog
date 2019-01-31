package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Log;
import com.ddm.vblog.mapper.LogMapper;
import com.ddm.vblog.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户操作日志表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
