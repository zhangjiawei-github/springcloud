package cn.syp.sypuser.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Author syp
 * @Date 2020/3/26 1:12
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtil.set("redis_key", "redis_vale");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = redisUtil.get("redis_key");
        System.out.println(value);
    }


}
