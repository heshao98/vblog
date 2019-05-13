package com.ddm.vblog.article;

import com.ddm.vblog.utils.RedisUtil;
import com.ddm.vblog.web.WebApplicationTests;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Description
 * @Date:2019/5/10 16:56
 * @Author heshaohua
 **/
public class RedisTest extends WebApplicationTests {

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void testMap(){
//        Map<String,String> map = new HashMap<>();
//        map.put("1", "一");
//        map.put("2", "二");
//        map.put("3", "三");
//        map.put("4", "四");
//        map.put("5", "五");
//        redisUtil.hmset("test",map);

        System.out.println(redisUtil.hmCountKey("test"));
        System.out.println(redisUtil.hmHasKey("test", "1"));
        redisUtil.hmput("test", "6", "六");
        redisUtil.hmGetValue("test","1");
        redisUtil.hmDelValue("test", "2");
    }
}
