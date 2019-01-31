package com.ddm.vblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddm.vblog.entity.Captcha;
import com.ddm.vblog.mapper.CaptchaMapper;
import com.ddm.vblog.service.CaptchaService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统验证码 服务实现类
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
@Service
public class CaptchaServiceImpl extends ServiceImpl<CaptchaMapper, Captcha> implements CaptchaService {

}
