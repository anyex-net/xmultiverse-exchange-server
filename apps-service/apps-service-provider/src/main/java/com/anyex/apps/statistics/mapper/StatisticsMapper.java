/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.statistics.mapper;

import com.anyex.apps.statistics.model.ChartModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 统计
 */
@Mapper
public interface StatisticsMapper
{
    List<ChartModel> getRegisterGroupByHoursOfDay(@Param("dtStr") String dtStr, @Param("source") String source);

    List<ChartModel> getAppActivationGroupByHoursOfDay(@Param("dtStr") String dtStr, @Param("source") String source);

    List<ChartModel> getDepositGroupByHoursOfDay(@Param("dtStr") String dtStr);

    List<ChartModel> getWithDrawGroupByHoursOfDay(@Param("dtStr") String dtStr);

    List<ChartModel> getFlowsOutcomeGroupByHoursOfDay(@Param("dtStr") String dtStr, @Param("businessType") String businessType);

    //

    List<ChartModel> getRegisterGroupByDaysOfDays(@Param("dtStart") String dtStart,
                                                  @Param("dtEnd") String dtEnd,
                                                  @Param("source") String source);

    List<ChartModel> getAppActivationGroupByDaysOfDays(@Param("dtStart") String dtStart,
                                                       @Param("dtEnd") String dtEnd,
                                                       @Param("source") String source);

    List<ChartModel> getDepositGroupByDaysOfDays(@Param("dtStart") String dtStart,
                                                 @Param("dtEnd") String dtEnd
                                                 );

    List<ChartModel> getWithDrawGroupByDaysOfDays(@Param("dtStart") String dtStart,
                                                  @Param("dtEnd") String dtEnd
                                                  );

    List<ChartModel> getFlowsOutcomeGroupByDaysOfDays(@Param("dtStart") String dtStart,
                                                      @Param("dtEnd") String dtEnd,
                                                      @Param("businessType") String businessType);

    //
    List<ChartModel> getRegisterGroupByWeeksOfPreMonths(@Param("firstMondayDateStr")String firstMondayDateStr,
                                                        @Param("lastSundayDateStr")String lastSundayDateStr,
                                                        @Param("limit")Integer limit,
                                                        @Param("source") String source);

    List<ChartModel> getAppActivationGroupByWeeksOfPreMonths(@Param("firstMondayDateStr")String firstMondayDateStr,
                                                             @Param("lastSundayDateStr")String lastSundayDateStr,
                                                             @Param("limit")Integer limit,
                                                             @Param("source") String source);

    List<ChartModel> getDepositGroupByWeeksOfPreMonths(@Param("firstMondayDateStr")String firstMondayDateStr,
                                                       @Param("lastSundayDateStr")String lastSundayDateStr,
                                                       @Param("limit")Integer limit
                                                       );

    List<ChartModel> getWithDrawGroupByWeeksOfPreMonths(@Param("firstMondayDateStr")String firstMondayDateStr,
                                                        @Param("lastSundayDateStr")String lastSundayDateStr,
                                                        @Param("limit")Integer limit
    );

    List<ChartModel> getFlowsOutcomeGroupByWeeksOfPreMonths(@Param("firstMondayDateStr")String firstMondayDateStr,
                                                            @Param("lastSundayDateStr")String lastSundayDateStr,
                                                            @Param("limit")Integer limit,
                                                            @Param("businessType") String businessType);

    //
    List<ChartModel> getRegisterGroupByMonthOfPreMonths(@Param("monthCnt")Integer monthCnt,
                                                        @Param("source") String source);

    List<ChartModel> getAppActivationGroupByMonthOfPreMonths(@Param("monthCnt")Integer monthCnt,
                                                             @Param("source") String source);

    List<ChartModel> getDepositGroupByMonthOfPreMonths(@Param("monthCnt")Integer monthCnt
    );

    List<ChartModel> getWithDrawGroupByMonthOfPreMonths(@Param("monthCnt")Integer monthCnt
    );

    List<ChartModel> getFlowsOutcomeGroupByMonthOfPreMonths(@Param("monthCnt")Integer monthCnt,
                                                            @Param("businessType") String businessType);

}
