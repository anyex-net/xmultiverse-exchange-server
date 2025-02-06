/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.statistics.service;

import com.anyex.apps.statistics.mapper.StatisticsMapper;
import com.anyex.apps.statistics.model.StatisticsAccountForLinesModel;
import com.anyex.apps.statistics.model.StatisticsFlowsForLinesModel;
import com.anyex.apps.statistics.model.StatisticsForLineModel;
import com.anyex.apps.statistics.model.ChartModel;
import com.anyex.apps.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 统计服务
 */
@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService
{
    protected StatisticsMapper statisticsMapper;

    @Autowired(required = false)
    public StatisticsServiceImpl(StatisticsMapper statisticsMapper)
    {
        this.statisticsMapper = statisticsMapper;
    }


    @Override
    public StatisticsAccountForLinesModel getRegisterGroupByHoursOfDay(String dtStr) {
        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByHoursOfDay(dtStr,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByHoursOfDay(dtStr,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByHoursOfDay(dtStr,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);

        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getRegisterGroupByDaysOfDays(String dtStart,String dtEnd) {
        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByDaysOfDays(dtStart,dtEnd,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByDaysOfDays(dtStart,dtEnd,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByDaysOfDays(dtStart,dtEnd,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);

        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getRegisterGroupByWeeksOfPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");

        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);

        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getRegisterGroupByWeeksOfPreThreeMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        calendar.add(Calendar.MONTH,-2);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");

        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);
        
        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getRegisterGroupByMonthOfPreMonths(int preMonthCnt) {
        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByMonthOfPreMonths(preMonthCnt,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByMonthOfPreMonths(preMonthCnt,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByMonthOfPreMonths(preMonthCnt,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);
        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getAppActivationGroupByHoursOfDay(String dtStr) {
        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getAppActivationGroupByHoursOfDay(dtStr,"google");
        List<ChartModel> transsionList = statisticsMapper.getAppActivationGroupByHoursOfDay(dtStr,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getAppActivationGroupByHoursOfDay(dtStr,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);
        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getAppActivationGroupByDaysOfDays(String dtStart,String dtEnd) {
        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getAppActivationGroupByDaysOfDays(dtStart,dtEnd,"google");
        List<ChartModel> transsionList = statisticsMapper.getAppActivationGroupByDaysOfDays(dtStart,dtEnd,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getAppActivationGroupByDaysOfDays(dtStart,dtEnd,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);
        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getAppActivationGroupByWeeksOfPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");

        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"google");
        List<ChartModel> transsionList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);

        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getAppActivationGroupByWeeksOfPreThreeMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        calendar.add(Calendar.MONTH,-2);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");

        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"google");
        List<ChartModel> transsionList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);

        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getAppActivationGroupByMonthOfPreMonths(int preMonthCnt) {
        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();
        List<ChartModel> googleList = statisticsMapper.getAppActivationGroupByMonthOfPreMonths(preMonthCnt,"google");
        List<ChartModel> transsionList = statisticsMapper.getAppActivationGroupByMonthOfPreMonths(preMonthCnt,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getAppActivationGroupByMonthOfPreMonths(preMonthCnt,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGoogleYy(yy1);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setTranssionYy(yy2);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setLuckyboxYy(yy3);

        return model;
    }

    @Override
    public StatisticsForLineModel getSwitchRateGroupByHoursOfDay(String dtStr) {
        StatisticsForLineModel model = new StatisticsForLineModel();
        String[] xx = new String[3];
        xx[0] = "google";
        xx[1] = "transsion";
        xx[2] = "luckybox";
        model.setXx(xx);

        // 加工分子
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByHoursOfDay(dtStr,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByHoursOfDay(dtStr,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByHoursOfDay(dtStr,"luckybox");
        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        Double a1 = y1.stream().mapToDouble(BigDecimal::doubleValue).sum();
        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        Double a2 = y2.stream().mapToDouble(BigDecimal::doubleValue).sum();
        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        Double a3 = y3.stream().mapToDouble(BigDecimal::doubleValue).sum();

        // 加工分母
        googleList = statisticsMapper.getAppActivationGroupByHoursOfDay(dtStr,"google");
        transsionList = statisticsMapper.getAppActivationGroupByHoursOfDay(dtStr,"transsion");
        luckyboxList = statisticsMapper.getAppActivationGroupByHoursOfDay(dtStr,"luckybox");
        List<BigDecimal> y11 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        Double b1 = y11.stream().mapToDouble(BigDecimal::doubleValue).sum();
        List<BigDecimal> y22 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        Double b2 = y22.stream().mapToDouble(BigDecimal::doubleValue).sum();
        List<BigDecimal> y33 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        Double b3 = y33.stream().mapToDouble(BigDecimal::doubleValue).sum();
        BigDecimal[] yy = new BigDecimal[3];

        if(BigDecimal.valueOf(b1).setScale(0,BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ZERO) == 0)
        {
            yy[0] = BigDecimal.ZERO;

        }else
        {
            yy[0] = BigDecimal.valueOf(a1).divide(BigDecimal.valueOf(b1),2,BigDecimal.ROUND_HALF_UP);
        }
        if(BigDecimal.valueOf(b2).setScale(0,BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ZERO) == 0) {
            yy[1] = BigDecimal.ZERO;
        }
        else {
            yy[1] = BigDecimal.valueOf(a2).divide(BigDecimal.valueOf(b2), 2, BigDecimal.ROUND_HALF_UP);
        }
        if(BigDecimal.valueOf(b3).setScale(0,BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ZERO) == 0) {
            yy[2] = BigDecimal.ZERO;
        }
        else {
            yy[2] = BigDecimal.valueOf(a3).divide(BigDecimal.valueOf(b3), 2, BigDecimal.ROUND_HALF_UP);
        }
        model.setYy(yy);
        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getSwitchRateGroupByDaysOfDays(String dtStart,String dtEnd) {
        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();

        // 加工分子
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByDaysOfDays(dtStart,dtEnd,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByDaysOfDays(dtStart,dtEnd,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByDaysOfDays(dtStart,dtEnd,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa1[] = y1.toArray(new BigDecimal[y1.size()]);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa2[] = y2.toArray(new BigDecimal[y2.size()]);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa3[] = y3.toArray(new BigDecimal[y3.size()]);

        // 加工分母
        googleList = statisticsMapper.getAppActivationGroupByDaysOfDays(dtStart,dtEnd,"google");
        transsionList = statisticsMapper.getAppActivationGroupByDaysOfDays(dtStart,dtEnd,"transsion");
        luckyboxList = statisticsMapper.getAppActivationGroupByDaysOfDays(dtStart,dtEnd,"luckybox");

        List<BigDecimal> y11 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb1[] = y11.toArray(new BigDecimal[y1.size()]);

        List<BigDecimal> y22 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb2[] = y22.toArray(new BigDecimal[y2.size()]);

        List<BigDecimal> y33 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb3[] = y33.toArray(new BigDecimal[y3.size()]);
        for (int i = 0; i < xx.size(); i++) {
            if(bb1[i].compareTo(BigDecimal.ZERO) == 0)
            {
                aa1[i] = BigDecimal.ZERO;

            }else
            {
                aa1[i] = aa1[i].divide(bb1[i],2,BigDecimal.ROUND_HALF_UP);
            }
            if(bb2[i].compareTo(BigDecimal.ZERO) == 0) {
                aa2[i] = BigDecimal.ZERO;
            }
            else {
                aa2[i] = aa2[i].divide(bb2[i], 2, BigDecimal.ROUND_HALF_UP);
            }

            if(bb3[i].compareTo(BigDecimal.ZERO) == 0) {
                aa3[i] = BigDecimal.ZERO;
            }
            else {
                aa3[i] = aa3[i].divide(bb3[i], 2, BigDecimal.ROUND_HALF_UP);
            }
        }
        model.setGoogleYy(aa1);
        model.setTranssionYy(aa2);
        model.setLuckyboxYy(aa3);
        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getSwitchRateGroupByWeeksOfPreMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        
        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();

        // 加工分子
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa1[] = y1.toArray(new BigDecimal[y1.size()]);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa2[] = y2.toArray(new BigDecimal[y2.size()]);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa3[] = y3.toArray(new BigDecimal[y3.size()]);

        // 加工分母
        googleList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"google");
        transsionList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"transsion");
        luckyboxList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"luckybox");

        List<BigDecimal> y11 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb1[] = y11.toArray(new BigDecimal[y1.size()]);

        List<BigDecimal> y22 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb2[] = y22.toArray(new BigDecimal[y2.size()]);

        List<BigDecimal> y33 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb3[] = y33.toArray(new BigDecimal[y3.size()]);
        for (int i = 0; i < xx.size(); i++) {
            if(bb1[i].compareTo(BigDecimal.ZERO) == 0)
            {
                aa1[i] = BigDecimal.ZERO;

            }else
            {
                aa1[i] = aa1[i].divide(bb1[i],2,BigDecimal.ROUND_HALF_UP);
            }
            if(bb2[i].compareTo(BigDecimal.ZERO) == 0) {
                aa2[i] = BigDecimal.ZERO;
            }
            else {
                aa2[i] = aa2[i].divide(bb2[i], 2, BigDecimal.ROUND_HALF_UP);
            }

            if(bb3[i].compareTo(BigDecimal.ZERO) == 0) {
                aa3[i] = BigDecimal.ZERO;
            }
            else {
                aa3[i] = aa3[i].divide(bb3[i], 2, BigDecimal.ROUND_HALF_UP);
            }
        }
        model.setGoogleYy(aa1);
        model.setTranssionYy(aa2);
        model.setLuckyboxYy(aa3);
        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getSwitchRateGroupByWeeksOfPreThreeMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        calendar.add(Calendar.MONTH,-2);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");

        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();

        // 加工分子
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa1[] = y1.toArray(new BigDecimal[y1.size()]);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa2[] = y2.toArray(new BigDecimal[y2.size()]);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa3[] = y3.toArray(new BigDecimal[y3.size()]);

        // 加工分母
        googleList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"google");
        transsionList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"transsion");
        luckyboxList = statisticsMapper.getAppActivationGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"luckybox");

        List<BigDecimal> y11 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb1[] = y11.toArray(new BigDecimal[y1.size()]);

        List<BigDecimal> y22 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb2[] = y22.toArray(new BigDecimal[y2.size()]);

        List<BigDecimal> y33 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb3[] = y33.toArray(new BigDecimal[y3.size()]);
        for (int i = 0; i < xx.size(); i++) {
            if(bb1[i].compareTo(BigDecimal.ZERO) == 0)
            {
                aa1[i] = BigDecimal.ZERO;

            }else
            {
                aa1[i] = aa1[i].divide(bb1[i],2,BigDecimal.ROUND_HALF_UP);
            }
            if(bb2[i].compareTo(BigDecimal.ZERO) == 0) {
                aa2[i] = BigDecimal.ZERO;
            }
            else {
                aa2[i] = aa2[i].divide(bb2[i], 2, BigDecimal.ROUND_HALF_UP);
            }

            if(bb3[i].compareTo(BigDecimal.ZERO) == 0) {
                aa3[i] = BigDecimal.ZERO;
            }
            else {
                aa3[i] = aa3[i].divide(bb3[i], 2, BigDecimal.ROUND_HALF_UP);
            }
        }
        model.setGoogleYy(aa1);
        model.setTranssionYy(aa2);
        model.setLuckyboxYy(aa3);
        return model;
    }

    @Override
    public StatisticsAccountForLinesModel getSwitchRateGroupByMonthOfPreMonths(int preMonthCnt) {

        StatisticsAccountForLinesModel model = new StatisticsAccountForLinesModel();

        // 加工分子
        List<ChartModel> googleList = statisticsMapper.getRegisterGroupByMonthOfPreMonths(preMonthCnt,"google");
        List<ChartModel> transsionList = statisticsMapper.getRegisterGroupByMonthOfPreMonths(preMonthCnt,"transsion");
        List<ChartModel> luckyboxList = statisticsMapper.getRegisterGroupByMonthOfPreMonths(preMonthCnt,"luckybox");

        List<String> xx = googleList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);

        List<BigDecimal> y1 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa1[] = y1.toArray(new BigDecimal[y1.size()]);

        List<BigDecimal> y2 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa2[] = y2.toArray(new BigDecimal[y2.size()]);

        List<BigDecimal> y3 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal aa3[] = y3.toArray(new BigDecimal[y3.size()]);

        // 加工分母
        googleList = statisticsMapper.getAppActivationGroupByMonthOfPreMonths(preMonthCnt,"google");
        transsionList = statisticsMapper.getAppActivationGroupByMonthOfPreMonths(preMonthCnt,"transsion");
        luckyboxList = statisticsMapper.getAppActivationGroupByMonthOfPreMonths(preMonthCnt,"luckybox");

        List<BigDecimal> y11 = googleList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb1[] = y11.toArray(new BigDecimal[y1.size()]);

        List<BigDecimal> y22 = transsionList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb2[] = y22.toArray(new BigDecimal[y2.size()]);

        List<BigDecimal> y33 = luckyboxList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal bb3[] = y33.toArray(new BigDecimal[y3.size()]);
        for (int i = 0; i < xx.size(); i++) {
            if(bb1[i].compareTo(BigDecimal.ZERO) == 0)
            {
                aa1[i] = BigDecimal.ZERO;

            }else
            {
                aa1[i] = aa1[i].divide(bb1[i],2,BigDecimal.ROUND_HALF_UP);
            }
            if(bb2[i].compareTo(BigDecimal.ZERO) == 0) {
                aa2[i] = BigDecimal.ZERO;
            }
            else {
                aa2[i] = aa2[i].divide(bb2[i], 2, BigDecimal.ROUND_HALF_UP);
            }

            if(bb3[i].compareTo(BigDecimal.ZERO) == 0) {
                aa3[i] = BigDecimal.ZERO;
            }
            else {
                aa3[i] = aa3[i].divide(bb3[i], 2, BigDecimal.ROUND_HALF_UP);
            }
        }
        model.setGoogleYy(aa1);
        model.setTranssionYy(aa2);
        model.setLuckyboxYy(aa3);
        return model;
    }

    @Override
    public StatisticsForLineModel getDepositGroupByHoursOfDay(String dtStr) {
        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getDepositGroupByHoursOfDay(dtStr);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getDepositGroupByDaysOfDays(String dtStart,String dtEnd) {
        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getDepositGroupByDaysOfDays(dtStart, dtEnd);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getDepositGroupByWeeksOfPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");

        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getDepositGroupByWeeksOfPreMonths(firstMonday,lastSunday,31);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getDepositGroupByWeeksOfPreThreeMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        calendar.add(Calendar.MONTH,-2);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");

        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getDepositGroupByWeeksOfPreMonths(firstMonday,lastSunday,93);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getDepositGroupByMonthOfPreMonths(int preMonthCnt) {
        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getDepositGroupByMonthOfPreMonths(preMonthCnt);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getWithDrawGroupByHoursOfDay(String dtStr) {
        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getWithDrawGroupByHoursOfDay(dtStr);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getWithDrawGroupByDaysOfDays(String dtStart,String dtEnd) {
        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getWithDrawGroupByDaysOfDays(dtStart, dtEnd);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getWithDrawGroupByWeeksOfPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getWithDrawGroupByWeeksOfPreMonths(firstMonday,lastSunday,31);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getWithDrawGroupByWeeksOfPreThreeMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        calendar.add(Calendar.MONTH,-2);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");
        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getWithDrawGroupByWeeksOfPreMonths(firstMonday,lastSunday,93);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsForLineModel getWithDrawGroupByMonthOfPreMonths(int preMonthCnt) {
        StatisticsForLineModel model = new StatisticsForLineModel();
        List<ChartModel> list = statisticsMapper.getWithDrawGroupByMonthOfPreMonths(preMonthCnt);
        List<String> xx = list.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = list.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setYy(yy1);
        return model;
    }

    @Override
    public StatisticsFlowsForLinesModel getFlowsOutcomeGroupByHoursOfDay(String dtStr) {
        StatisticsFlowsForLinesModel model = new StatisticsFlowsForLinesModel();
        // gameSpinReward 转盘   activityTreasureHunt 一元 activityHotDeals 半价
        List<ChartModel> gameSpinRewardList = statisticsMapper.getFlowsOutcomeGroupByHoursOfDay(dtStr,"gameSpinReward");
        List<ChartModel> activityTreasureHuntList = statisticsMapper.getFlowsOutcomeGroupByHoursOfDay(dtStr,"activityTreasureHunt");
        List<ChartModel> activityHotDealsList = statisticsMapper.getFlowsOutcomeGroupByHoursOfDay(dtStr,"activityHotDeals");
        List<String> xx = gameSpinRewardList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = gameSpinRewardList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGameSpinRewardYy(yy1);
        List<BigDecimal> y2 = activityTreasureHuntList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setActivityTreasureHuntYy(yy2);
        List<BigDecimal> y3 = activityHotDealsList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setActivityHotDealsYy(yy3);
        return model;
    }

    @Override
    public StatisticsFlowsForLinesModel getFlowsOutcomeGroupByDaysOfDays(String dtStart,String dtEnd) {
        StatisticsFlowsForLinesModel model = new StatisticsFlowsForLinesModel();
        // gameSpinReward 转盘   activityTreasureHunt 一元 activityHotDeals 半价
        List<ChartModel> gameSpinRewardList = statisticsMapper.getFlowsOutcomeGroupByDaysOfDays(dtStart,dtEnd,"gameSpinReward");
        List<ChartModel> activityTreasureHuntList = statisticsMapper.getFlowsOutcomeGroupByDaysOfDays(dtStart,dtEnd,"activityTreasureHunt");
        List<ChartModel> activityHotDealsList = statisticsMapper.getFlowsOutcomeGroupByDaysOfDays(dtStart,dtEnd,"activityHotDeals");
        List<String> xx = gameSpinRewardList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = gameSpinRewardList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGameSpinRewardYy(yy1);
        List<BigDecimal> y2 = activityTreasureHuntList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setActivityTreasureHuntYy(yy2);
        List<BigDecimal> y3 = activityHotDealsList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setActivityHotDealsYy(yy3);
        return model;
    }

    @Override
    public StatisticsFlowsForLinesModel getFlowsOutcomeGroupByWeeksOfPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        StatisticsFlowsForLinesModel model = new StatisticsFlowsForLinesModel();
        // gameSpinReward 转盘   activityTreasureHunt 一元 activityHotDeals 半价
        List<ChartModel> gameSpinRewardList = statisticsMapper.getFlowsOutcomeGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"gameSpinReward");
        List<ChartModel> activityTreasureHuntList = statisticsMapper.getFlowsOutcomeGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"activityTreasureHunt");
        List<ChartModel> activityHotDealsList = statisticsMapper.getFlowsOutcomeGroupByWeeksOfPreMonths(firstMonday,lastSunday,31,"activityHotDeals");
        List<String> xx = gameSpinRewardList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = gameSpinRewardList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGameSpinRewardYy(yy1);
        List<BigDecimal> y2 = activityTreasureHuntList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setActivityTreasureHuntYy(yy2);
        List<BigDecimal> y3 = activityHotDealsList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setActivityHotDealsYy(yy3);
        return model;
    }

    @Override
    public StatisticsFlowsForLinesModel getFlowsOutcomeGroupByWeeksOfPreThreeMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,-1);
        String lastSunday = DateUtils.formatDate(DateUtils.getLastSunday(calendar.getTime()),"yyyy-MM-dd");
        calendar.add(Calendar.MONTH,-2);
        String firstMonday = DateUtils.formatDate(DateUtils.getFirstMonday(calendar.getTime()),"yyyy-MM-dd");
        StatisticsFlowsForLinesModel model = new StatisticsFlowsForLinesModel();
        // gameSpinReward 转盘   activityTreasureHunt 一元 activityHotDeals 半价
        List<ChartModel> gameSpinRewardList = statisticsMapper.getFlowsOutcomeGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"gameSpinReward");
        List<ChartModel> activityTreasureHuntList = statisticsMapper.getFlowsOutcomeGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"activityTreasureHunt");
        List<ChartModel> activityHotDealsList = statisticsMapper.getFlowsOutcomeGroupByWeeksOfPreMonths(firstMonday,lastSunday,93,"activityHotDeals");
        List<String> xx = gameSpinRewardList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = gameSpinRewardList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGameSpinRewardYy(yy1);
        List<BigDecimal> y2 = activityTreasureHuntList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setActivityTreasureHuntYy(yy2);
        List<BigDecimal> y3 = activityHotDealsList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setActivityHotDealsYy(yy3);
        return model;
    }

    @Override
    public StatisticsFlowsForLinesModel getFlowsOutcomeGroupByMonthOfPreMonths(int preMonthCnt) {
        StatisticsFlowsForLinesModel model = new StatisticsFlowsForLinesModel();
        // gameSpinReward 转盘   activityTreasureHunt 一元 activityHotDeals 半价
        List<ChartModel> gameSpinRewardList = statisticsMapper.getFlowsOutcomeGroupByMonthOfPreMonths(preMonthCnt,"gameSpinReward");
        List<ChartModel> activityTreasureHuntList = statisticsMapper.getFlowsOutcomeGroupByMonthOfPreMonths(preMonthCnt,"activityTreasureHunt");
        List<ChartModel> activityHotDealsList = statisticsMapper.getFlowsOutcomeGroupByMonthOfPreMonths(preMonthCnt,"activityHotDeals");
        List<String> xx = gameSpinRewardList.stream().map(ChartModel::getDt).collect(Collectors.toList());
        String x[] = xx.toArray(new String[xx.size()]);
        model.setXx(x);
        List<BigDecimal> y1 = gameSpinRewardList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy1[] = y1.toArray(new BigDecimal[y1.size()]);
        model.setGameSpinRewardYy(yy1);
        List<BigDecimal> y2 = activityTreasureHuntList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy2[] = y2.toArray(new BigDecimal[y2.size()]);
        model.setActivityTreasureHuntYy(yy2);
        List<BigDecimal> y3 = activityHotDealsList.stream().map(ChartModel::getNum).collect(Collectors.toList());
        BigDecimal yy3[] = y3.toArray(new BigDecimal[y3.size()]);
        model.setActivityHotDealsYy(yy3);
        return model;
    }

}
