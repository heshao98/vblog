package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Log;
import mapper.LogMapper;
import org.springframework.stereotype.Service;
import service.LogService;

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
