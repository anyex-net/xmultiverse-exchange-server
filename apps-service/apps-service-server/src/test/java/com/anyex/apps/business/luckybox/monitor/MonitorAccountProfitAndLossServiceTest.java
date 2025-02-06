package com.anyex.apps.business.luckybox.monitor;

import com.alibaba.fastjson.JSON;
import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.operation.service.MonitorAccountProfitLossService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 盈亏监控
 * @author Playguy
 */
@Slf4j
public class MonitorAccountProfitAndLossServiceTest extends BaseServiceImplTest {


    @Autowired
    MonitorAccountProfitLossService monitorAccountProfitLossService;

    @Test
    public void updateTest()
    {
        for (int i = 0; i < 5; i++) {
            monitorAccountProfitLossService.updateMonitorAllAccountProfit();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Test
    public void allDataTest()
    {
        System.out.println(JSON.toJSON(monitorAccountProfitLossService.allAccountProfit()));
    }

}