package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.User;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import service.UserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
