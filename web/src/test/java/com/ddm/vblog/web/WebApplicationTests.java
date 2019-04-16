package com.ddm.vblog.web;

import com.ddm.vblog.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void contextLoads() {
    }



}

