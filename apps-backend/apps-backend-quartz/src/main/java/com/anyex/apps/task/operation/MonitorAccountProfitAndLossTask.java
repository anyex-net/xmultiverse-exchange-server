package com.anyex.apps.task.operation;

import com.anyex.apps.operation.service.MonitorAccountProfitLossService;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  账户浮动盈亏监控
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class MonitorAccountProfitAndLossTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    MonitorAccountProfitLossService monitorAccountProfitLossService;

    /**
     * 账户浮动盈亏监控
     * @throws RuntimeException
     */
//    @Scheduled(cron = "0 2/3 * * * ?")
    public void monitorWalletAssetFlowsTask() throws RuntimeException
    {
        log.info("账户浮动盈亏监控 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:monitor:accountProfitAndLoss");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                monitorAccountProfitLossService.updateMonitorAllAccountProfit();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("账户浮动盈亏监控调度异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("账户浮动盈亏监控调度异常： error={}",  "分布式锁获取失败");
        }
        log.info("账户浮动盈亏监控 结束任务");
    }
}
