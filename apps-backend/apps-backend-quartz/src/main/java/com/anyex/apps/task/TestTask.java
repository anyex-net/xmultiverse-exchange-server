package com.anyex.apps.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TestTask
 * <p>File：TestTask.java</p>
 * <p>Title: TestTask</p>
 * <p>Description: TestTask</p>
 * <p>Copyright: Copyright (c) 2019/10/25</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class TestTask
{
    /**
     * Test 定时任务
     * @throws RuntimeException
     */
    //@Scheduled(cron = "59 * * * * ?")
    public void test() throws RuntimeException
    {
        log.info("Test 开始任务");
        try
        {
            log.info("TestTestTest!!!");
        }
        catch (Exception e)
        {
            log.error("Test 任务失败：{}", e.getLocalizedMessage());
        }
        log.info("Test 结束任务");
    }
}
