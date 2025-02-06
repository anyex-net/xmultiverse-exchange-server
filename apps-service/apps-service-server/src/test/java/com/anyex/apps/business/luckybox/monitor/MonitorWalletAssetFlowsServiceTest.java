package com.anyex.apps.business.luckybox.monitor;

import com.anyex.apps.BaseServiceImplTest;

import com.anyex.apps.operation.service.MonitorWalletAssetFlowsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 资金流水监控
 * @author Playguy
 */
@Slf4j
public class MonitorWalletAssetFlowsServiceTest extends BaseServiceImplTest {


    @Autowired
    MonitorWalletAssetFlowsService monitorWalletAssetFlowsService;

    @Test
    public void flowsTest()
    {
        while(true) {
            monitorWalletAssetFlowsService.monitorWalletAssetFlowsTask();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}