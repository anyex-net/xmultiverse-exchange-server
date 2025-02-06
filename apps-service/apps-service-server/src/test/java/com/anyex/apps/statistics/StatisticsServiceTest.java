package com.anyex.apps.statistics;



import com.alibaba.fastjson.JSON;
import com.anyex.apps.BaseServiceImplTest;

import com.anyex.apps.statistics.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class StatisticsServiceTest extends BaseServiceImplTest {


    @Autowired
    StatisticsService statisticsService;

    @Before
    public void pre()
    {

    }

    /**
     * 查询当天24小时
     */

    @Test
    public void getStatisticsGroupByHoursOfDay()
    {
        System.out.println(JSON.toJSON(statisticsService.getRegisterGroupByHoursOfDay("2024-04-07")));
        System.out.println(JSON.toJSON(statisticsService.getAppActivationGroupByHoursOfDay("2024-04-07")));
        System.out.println(JSON.toJSON(statisticsService.getSwitchRateGroupByHoursOfDay("2024-04-08")));
        System.out.println(JSON.toJSON(statisticsService.getWithDrawGroupByHoursOfDay("2024-04-08")));
        System.out.println(JSON.toJSON(statisticsService.getDepositGroupByHoursOfDay("2024-04-08")));
        System.out.println(JSON.toJSON(statisticsService.getFlowsOutcomeGroupByHoursOfDay("2024-04-08")));
    }

    @Test
    public void getStatisticsGroupByDaysOfDays()
    {
        System.out.println(JSON.toJSON(statisticsService.getRegisterGroupByDaysOfDays("2024-04-01","2024-04-07")));
        System.out.println(JSON.toJSON(statisticsService.getAppActivationGroupByDaysOfDays("2024-04-01","2024-04-07")));
        System.out.println(JSON.toJSON(statisticsService.getSwitchRateGroupByDaysOfDays("2024-04-01","2024-04-07")));
        System.out.println(JSON.toJSON(statisticsService.getWithDrawGroupByDaysOfDays("2024-04-01","2024-04-07")));
        System.out.println(JSON.toJSON(statisticsService.getDepositGroupByDaysOfDays("2024-04-01","2024-04-07")));
        System.out.println(JSON.toJSON(statisticsService.getFlowsOutcomeGroupByDaysOfDays("2024-04-01","2024-04-07")));
    }

    @Test
    public void getStatisticsGroupByWeeksOfPreMonth()
    {
        System.out.println(JSON.toJSON(statisticsService.getRegisterGroupByWeeksOfPreMonth()));
        System.out.println(JSON.toJSON(statisticsService.getAppActivationGroupByWeeksOfPreMonth()));
        System.out.println(JSON.toJSON(statisticsService.getSwitchRateGroupByWeeksOfPreMonth()));
        System.out.println(JSON.toJSON(statisticsService.getWithDrawGroupByWeeksOfPreMonth()));
        System.out.println(JSON.toJSON(statisticsService.getDepositGroupByWeeksOfPreMonth()));
        System.out.println(JSON.toJSON(statisticsService.getFlowsOutcomeGroupByWeeksOfPreMonth()));
    }



    @Test
    public void getStatisticsGroupByWeeksOfPreThreeMonth()
    {
        System.out.println(JSON.toJSON(statisticsService.getRegisterGroupByWeeksOfPreThreeMonth()));
        System.out.println(JSON.toJSON(statisticsService.getAppActivationGroupByWeeksOfPreThreeMonth()));
        System.out.println(JSON.toJSON(statisticsService.getSwitchRateGroupByWeeksOfPreThreeMonth()));
        System.out.println(JSON.toJSON(statisticsService.getWithDrawGroupByWeeksOfPreThreeMonth()));
        System.out.println(JSON.toJSON(statisticsService.getDepositGroupByWeeksOfPreThreeMonth()));
        System.out.println(JSON.toJSON(statisticsService.getFlowsOutcomeGroupByWeeksOfPreThreeMonth()));
    }


    @Test
    public void getStatisticsGroupByMonthOfPreMonths()
    {
        int preMonthCnt = 12;
        System.out.println(JSON.toJSON(statisticsService.getRegisterGroupByMonthOfPreMonths(preMonthCnt)));
        System.out.println(JSON.toJSON(statisticsService.getAppActivationGroupByMonthOfPreMonths(preMonthCnt)));
        System.out.println(JSON.toJSON(statisticsService.getSwitchRateGroupByMonthOfPreMonths(preMonthCnt)));
        System.out.println(JSON.toJSON(statisticsService.getWithDrawGroupByMonthOfPreMonths(preMonthCnt)));
        System.out.println(JSON.toJSON(statisticsService.getDepositGroupByMonthOfPreMonths(preMonthCnt)));
        System.out.println(JSON.toJSON(statisticsService.getFlowsOutcomeGroupByMonthOfPreMonths(preMonthCnt)));
    }



}