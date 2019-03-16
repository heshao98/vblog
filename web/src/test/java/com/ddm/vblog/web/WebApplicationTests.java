package com.ddm.vblog.web;

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


    @Test
    public void testRedisMap(){
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("1","少华");
//        map.put("2","张三");
//        redisUtil.hmset("a",map);
//        List<String> list=  new LinkedList<>();
//        list.add("a");
//        list.add("a");
//        list.add("a");
//        redisUtil.lLeftPush("aa","1");
//        redisUtil.hput("a","4",list);
//        redisUtil.
        System.out.println(redisUtil.hmHasKey("a","3"));
    }



}

