package com.anyex.apps.statistics.service;

import com.anyex.apps.statistics.model.StatisticsAccountForLinesModel;
import com.anyex.apps.statistics.model.StatisticsFlowsForLinesModel;
import com.anyex.apps.statistics.model.StatisticsForLineModel;

public interface StatisticsService {
    /**
     * 注册情况统计
     * @param dtStr yyyy-MM-dd
     * @return
     */
    StatisticsAccountForLinesModel getRegisterGroupByHoursOfDay(String dtStr);
    StatisticsAccountForLinesModel getRegisterGroupByDaysOfDays(String dtStart,String dtEnd);
    StatisticsAccountForLinesModel getRegisterGroupByWeeksOfPreMonth();
    StatisticsAccountForLinesModel getRegisterGroupByWeeksOfPreThreeMonth();
    StatisticsAccountForLinesModel getRegisterGroupByMonthOfPreMonths(int preMonthCnt);

    /**
     * 激活情况
     * @param dtStr yyyy-MM-dd
     * @return
     */
    StatisticsAccountForLinesModel getAppActivationGroupByHoursOfDay(String dtStr);
    StatisticsAccountForLinesModel getAppActivationGroupByDaysOfDays(String dtStart,String dtEnd);
    StatisticsAccountForLinesModel getAppActivationGroupByWeeksOfPreMonth();
    StatisticsAccountForLinesModel getAppActivationGroupByWeeksOfPreThreeMonth();
    StatisticsAccountForLinesModel getAppActivationGroupByMonthOfPreMonths(int preMonthCnt);

    /**
     * 用户转化率
     * @param dtStr yyyy-MM-dd
     * @return
     */
    StatisticsForLineModel getSwitchRateGroupByHoursOfDay(String dtStr);
    StatisticsAccountForLinesModel getSwitchRateGroupByDaysOfDays(String dtStart,String dtEnd);
    StatisticsAccountForLinesModel getSwitchRateGroupByWeeksOfPreMonth();
    StatisticsAccountForLinesModel getSwitchRateGroupByWeeksOfPreThreeMonth();
    StatisticsAccountForLinesModel getSwitchRateGroupByMonthOfPreMonths(int preMonthCnt);


    /**
     * 充值情况
     * @param dtStr yyyy-MM-dd
     * @return
     */
    StatisticsForLineModel getDepositGroupByHoursOfDay(String dtStr);
    StatisticsForLineModel getDepositGroupByDaysOfDays(String dtStart,String dtEnd);
    StatisticsForLineModel getDepositGroupByWeeksOfPreMonth();
    StatisticsForLineModel getDepositGroupByWeeksOfPreThreeMonth();
    StatisticsForLineModel getDepositGroupByMonthOfPreMonths(int preMonthCnt);

    /**
     * 提现情况
     * @param dtStr yyyy-MM-dd
     * @return
     */
    StatisticsForLineModel getWithDrawGroupByHoursOfDay(String dtStr);
    StatisticsForLineModel getWithDrawGroupByDaysOfDays(String dtStart,String dtEnd);
    StatisticsForLineModel getWithDrawGroupByWeeksOfPreMonth();
    StatisticsForLineModel getWithDrawGroupByWeeksOfPreThreeMonth();
    StatisticsForLineModel getWithDrawGroupByMonthOfPreMonths(int preMonthCnt);

    /**
     * 按天插24小时用户成交情况
     * @param dtStr yyyy-MM-dd
     * @return
     */
    StatisticsFlowsForLinesModel getFlowsOutcomeGroupByHoursOfDay(String dtStr);
    StatisticsFlowsForLinesModel getFlowsOutcomeGroupByDaysOfDays(String dtStart,String dtEnd);
    StatisticsFlowsForLinesModel getFlowsOutcomeGroupByWeeksOfPreMonth();
    StatisticsFlowsForLinesModel getFlowsOutcomeGroupByWeeksOfPreThreeMonth();
    StatisticsFlowsForLinesModel getFlowsOutcomeGroupByMonthOfPreMonths(int preMonthCnt);
}
