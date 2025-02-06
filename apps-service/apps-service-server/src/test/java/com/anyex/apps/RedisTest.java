package com.anyex.apps;

import com.anyex.apps.consts.CacheConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * redis测试类
 * note: 1.记录变更 by WangXiao
 *
 * @author WangXiao
 * @date 2023/02/08 16:03
 **/
@Slf4j
public class RedisTest extends BaseServiceImplTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        System.out.println(redisTemplate);
        redisTemplate.opsForValue().set("test1", "test1-ok");
        Object test1 = redisTemplate.opsForValue().get("test1");
        System.out.println("test1: " + test1);
    }

    @Test
    public void test2() {
        Set<String> keys = redisTemplate.keys(CacheConst.ADMIN_SHIRO_CACHE_PREFIX.concat("*"));
        log.info("redisTemplate.keys:{}", keys);
        //session:admin:9e59c8ce-e444-4d03-96df-0407206d87d9
        keys = redisTemplate.keys("session:admin:9e59c8ce-e444-4d03-96df-0407206d87d9");
        log.info("redisTemplate.keys:{}", keys);
    }

//    public static void main(String[] args) {
//        System.out.println(getDmsFun(Double.valueOf("10724335")));
//        System.out.println(getDmsFun(Double.valueOf("43754256")));
//        System.out.println(String.format("%05s",1));
//        Random rdm = new Random(4);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Math.random() * 2 -1);
//        }
//    }

    public static String getDmsFun(double data) {
        int degree = (int) (data / 360000);
        int minute = (int) ((data - degree * 360000) / 6000);
        double second =  ((data - degree * 360000 - minute * 6000) / 100);
        return String.valueOf(degree+Double.valueOf(minute)/60+Double.valueOf(second)/3600);
    }
}
