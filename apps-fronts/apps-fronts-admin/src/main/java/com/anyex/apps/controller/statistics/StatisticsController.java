/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.statistics;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.statistics.req.ReqStatistics;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.statistics.service.StatisticsService;
import com.anyex.apps.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 图表统计
 */
@Slf4j
@RestController
@RequestMapping("/statistics")
@Api(description = "图表统计")
public class StatisticsController extends GenericController
{
    @Autowired(required = false)
    private StatisticsService statisticsService;

    @PostMapping(value = "/register")
    @RequiresPermissions("statistics:account:data")
    @ApiOperation(value = "用户注册统计", httpMethod = "POST")
    public JsonMessage register(@Validated @ModelAttribute ReqStatistics model) throws BusinessException {
        if (model.getType() == 1) {
            String[] dates = model.getDate().split("~");
            String start = dates[0];
            String end = dates[1];
            if(StringUtils.equalsIgnoreCase(dates[0],dates[1]))
            {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getRegisterGroupByHoursOfDay(start));
            }
            else
            {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getRegisterGroupByDaysOfDays(start,end));
            }
        } else if (model.getType() == 2) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String start = df.format(cal.getTime());
            cal.add(Calendar.DATE, 6);
            String end = df.format(cal.getTime());
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getRegisterGroupByDaysOfDays(start,end));
        } else if (model.getType() == 3) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getRegisterGroupByWeeksOfPreMonth());
        } else if (model.getType() == 4) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getRegisterGroupByWeeksOfPreThreeMonth());
        } else if (model.getType() == 5) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getRegisterGroupByMonthOfPreMonths(6));
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
    }

    @PostMapping(value = "/appActivation")
    @RequiresPermissions("statistics:account:data")
    @ApiOperation(value = "用户安装激活统计", httpMethod = "POST")
    public JsonMessage appActivation(@Validated @ModelAttribute ReqStatistics model) throws BusinessException {
        if (model.getType() == 1) {
            String[] dates = model.getDate().split("~");
            String start = dates[0];
            String end = dates[1];
            if(StringUtils.equalsIgnoreCase(dates[0],dates[1])) {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getAppActivationGroupByHoursOfDay(start));
            }
            else {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getAppActivationGroupByDaysOfDays(start,end));
            }
        } else if (model.getType() == 2) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String start = df.format(cal.getTime());
            cal.add(Calendar.DATE, 6);
            String end = df.format(cal.getTime());
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getAppActivationGroupByDaysOfDays(start,end));
        } else if (model.getType() == 3) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getAppActivationGroupByWeeksOfPreMonth());
        } else if (model.getType() == 4) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getAppActivationGroupByWeeksOfPreThreeMonth());
        } else if (model.getType() == 5) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getAppActivationGroupByMonthOfPreMonths(6));
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
    }

    @PostMapping(value = "/accountSwitchRate")
    @RequiresPermissions("statistics:account:data")
    @ApiOperation(value = "用户转化率统计", httpMethod = "POST")
    public JsonMessage accountSwitchRate(@Validated @ModelAttribute ReqStatistics model) throws BusinessException {
        if (model.getType() == 1) {
            String[] dates = model.getDate().split("~");
            String start = dates[0];
            String end = dates[1];
            if(StringUtils.equalsIgnoreCase(dates[0],dates[1])) {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getSwitchRateGroupByHoursOfDay(start));
            }
            else {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getSwitchRateGroupByDaysOfDays(start,end));
            }
        } else if (model.getType() == 2) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String start = df.format(cal.getTime());
            cal.add(Calendar.DATE, 6);
            String end = df.format(cal.getTime());
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getSwitchRateGroupByDaysOfDays(start,end));
        } else if (model.getType() == 3) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getSwitchRateGroupByWeeksOfPreMonth());
        } else if (model.getType() == 4) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getSwitchRateGroupByWeeksOfPreThreeMonth());
        } else if (model.getType() == 5) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getSwitchRateGroupByMonthOfPreMonths(6));
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
    }

    @PostMapping(value = "/deposit")
    @RequiresPermissions("statistics:asset:data")
    @ApiOperation(value = "用户充值统计", httpMethod = "POST")
    public JsonMessage deposit(@Validated @ModelAttribute ReqStatistics model) throws BusinessException {
        if (model.getType() == 1) {
            String[] dates = model.getDate().split("~");
            String start = dates[0];
            String end = dates[1];
            if(StringUtils.equalsIgnoreCase(dates[0],dates[1])) {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getDepositGroupByHoursOfDay(start));
            }
            else
            {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getDepositGroupByDaysOfDays(start,end));
            }
        } else if (model.getType() == 2) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String start = df.format(cal.getTime());
            cal.add(Calendar.DATE, 6);
            String end = df.format(cal.getTime());
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getDepositGroupByDaysOfDays(start,end));
        } else if (model.getType() == 3) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getDepositGroupByWeeksOfPreMonth());
        } else if (model.getType() == 4) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getDepositGroupByWeeksOfPreThreeMonth());
        } else if (model.getType() == 5) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getDepositGroupByMonthOfPreMonths(6));
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
    }

    @PostMapping(value = "/withdraw")
    @RequiresPermissions("statistics:asset:data")
    @ApiOperation(value = "用户提现统计", httpMethod = "POST")
    public JsonMessage withdraw(@Validated @ModelAttribute ReqStatistics model) throws BusinessException {
        if (model.getType() == 1) {
            String[] dates = model.getDate().split("~");
            String start = dates[0];
            String end = dates[1];
            if(StringUtils.equalsIgnoreCase(dates[0],dates[1])) {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getWithDrawGroupByHoursOfDay(start));
            }else
            {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getWithDrawGroupByDaysOfDays(start,end));
            }
        } else if (model.getType() == 2) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String start = df.format(cal.getTime());
            cal.add(Calendar.DATE, 6);
            String end = df.format(cal.getTime());
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getWithDrawGroupByDaysOfDays(start,end));
        } else if (model.getType() == 3) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getWithDrawGroupByWeeksOfPreMonth());
        } else if (model.getType() == 4) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getWithDrawGroupByWeeksOfPreThreeMonth());
        } else if (model.getType() == 5) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getWithDrawGroupByMonthOfPreMonths(6));
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
    }

    @PostMapping(value = "/flow")
    @RequiresPermissions("statistics:asset:data")
    @ApiOperation(value = "用户支出统计", httpMethod = "POST")
    public JsonMessage flowOutCome(@Validated @ModelAttribute ReqStatistics model) throws BusinessException {
        if (model.getType() == 1) {
            String[] dates = model.getDate().split("~");
            String start = dates[0];
            String end = dates[1];
            if(StringUtils.equalsIgnoreCase(dates[0],dates[1])) {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getFlowsOutcomeGroupByHoursOfDay(start));
            }else {
                return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getFlowsOutcomeGroupByDaysOfDays(start,end));
            }
        } else if (model.getType() == 2) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String start = df.format(cal.getTime());
            cal.add(Calendar.DATE, 6);
            String end = df.format(cal.getTime());
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getFlowsOutcomeGroupByDaysOfDays(start,end));
        } else if (model.getType() == 3) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getFlowsOutcomeGroupByWeeksOfPreMonth());
        } else if (model.getType() == 4) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getFlowsOutcomeGroupByWeeksOfPreThreeMonth());
        } else if (model.getType() == 5) {
            return this.getJsonMessage(CommonEnums.SUCCESS, statisticsService.getFlowsOutcomeGroupByMonthOfPreMonths(6));
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
    }

}
