package com.anyex.apps.task.business.luckybox.goods;

import com.anyex.apps.business.luckybox.goods.service.GoodsSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时调整SKU库存
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class GoodsSkuTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    GoodsSkuService goodsSkuService;

    /**
     * 定时调整SKU库存
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 0/35 * * * ?")
    public void monitorWalletAssetFlowsTask() throws RuntimeException
    {
        log.info("定时调整SKU库存 开始任务");
        try {
            goodsSkuService.adjustGoodsSkuStock();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("定时调整SKU库存调度异常：error={}",e.getMessage());
        } 
        log.info("定时调整SKU库存 结束任务");
    }
}
