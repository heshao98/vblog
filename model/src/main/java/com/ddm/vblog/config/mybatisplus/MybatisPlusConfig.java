package com.ddm.vblog.config.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Date:2019/2/19 13:48
 * @Author ddm
 **/
@Configuration
@MapperScan("com.ddm.vblog.mapper")
public class MybatisPlusConfig {
}
