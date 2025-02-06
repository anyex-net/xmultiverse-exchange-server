package com.anyex.apps.task.business.luckybox.order;

import com.anyex.apps.business.luckybox.order.service.Order4ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 活动订单定时轮训领取中奖 进行对应账户资产变更
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class Order4ActivityTask
{
    @Autowired(required = false)
    Order4ActivityService order4ActivityService;

    /**
     * 一元夺宝活动订单定时轮训领取中奖 进行对应账户资产变更
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void treasureHuntOrder4ActivityClaimLotteryTask() throws RuntimeException
    {
        log.info("一元夺宝活动订单定时轮训领取中奖 开始任务");
        try
        {
            order4ActivityService.treasureHuntOrder4ActivityClaimLottery();
        }
        catch (Exception e)
        {
            log.error("一元夺宝活动订单定时轮训领取中奖 任务失败：{}", e.getLocalizedMessage());
        }
        log.info("一元夺宝活动订单定时轮训领取中奖 结束任务");
    }
}
