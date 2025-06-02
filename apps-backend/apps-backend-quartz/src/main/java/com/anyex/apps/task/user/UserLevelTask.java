package com.anyex.apps.task.user;

import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户等级调度
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class UserLevelTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    UserService userService;

    /**
     * 用户等级调度
     * @throws RuntimeException
     */
//    @Scheduled(cron = "0 */30 * * * ?")
    public void userLevelTask() throws RuntimeException
    {
        log.info("用户等级调度 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:task:userLevelTask");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                List<User> listAllUser = userService.selectAll();
                if(null!=listAllUser && listAllUser.size() > 0){

                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("用户等级调度 异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("用户等级调度 异常: error={}", "分布式锁获取失败");
        }
        log.info("用户等级调度 结束任务");
    }
}
