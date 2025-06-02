package com.anyex.apps.task.user;

import com.anyex.apps.user.entity.UserRebate;
import com.anyex.apps.user.service.UserInviteRewardConfigService;
import com.anyex.apps.user.service.UserRebateService;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户返佣记录每个周期返现处理调度
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class UserRebateDealTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    UserRebateService userRebateService;

    @Autowired(required = false)
    UserInviteRewardConfigService userInviteRewardConfigService;

    /**
     * 用户返佣记录每个周期返现处理调度
     * @throws RuntimeException
     */
//    @Scheduled(cron = "0 */19 * * * ?")
    public void userRebateDealTask() throws RuntimeException
    {
        log.info("用户返佣记录每个周期返现处理调度 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:task:userRebateDealTask");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock())
        {
            try
            {
                UserRebate userRebateSearch = new UserRebate();
                userRebateSearch.setStatus("pending");
                List<UserRebate> listUserRebate = userRebateService.findList(userRebateSearch);
                if(null!=listUserRebate && listUserRebate.size()>0)
                {
                    for(int i=0; i<listUserRebate.size(); i++)
                    {
                        // 根据 一定周期内的累积手续费 根据 等级配置 进行返佣处理
                        UserRebate userRebateDB = listUserRebate.get(i);
                        userRebateDB.setRebateRate(BigDecimal.valueOf(0.1));
                        userRebateDB.setRebateAmount(userRebateDB.getFeeAmount().multiply(userRebateDB.getRebateRate()));
                        userRebateDB.setStatus("settled");
                        userRebateDB.setSettleDate(new Date());
                        userRebateDB.setUpdateTime(System.currentTimeMillis());
                        log.info("更新userRebateDB:{}", userRebateDB);
                        userRebateService.updateByPrimaryKeySelective(userRebateDB);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("用户返佣记录每个周期返现处理调度 异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("用户返佣记录每个周期返现处理调度 异常: error={}", "分布式锁获取失败");
        }
        log.info("用户返佣记录每个周期返现处理调度 结束任务");
    }
}
