/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.system.entity.SysUserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * RWA机构SPV产品 服务实现类
 * <p>File：RwaInstSpvProductServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductServiceImpl </p>
 * <p>Description:RwaInstSpvProductServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class RwaInstSpvProductServiceImpl extends GenericServiceImpl<RwaInstSpvProduct> implements RwaInstSpvProductService
{
    protected RwaInstSpvProductMapper rwaInstSpvProductMapper;

    @Autowired(required = false)
    public RwaInstSpvProductServiceImpl(RwaInstSpvProductMapper rwaInstSpvProductMapper)
    {
        super(rwaInstSpvProductMapper);
        this.rwaInstSpvProductMapper = rwaInstSpvProductMapper;
    }

    @Override
    public PaginateResult<RwaInstSpvProduct> findListByState(Pagination pagin,RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException{
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<RwaInstSpvProduct> pageInfo = PageInfo.of(rwaInstSpvProductMapper.findListByState(rwaInstSpvProduct));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        List<RwaInstSpvProduct> result = pageInfo.getList();
        return new PaginateResult<>(pagin, result);
    }

    @Scheduled(cron = "15 0 0 * * ?")
    public void productPerformTask() throws BusinessException, ParseException {
        log.info("===============开始执行产品定时任务================");
        List<RwaInstSpvProduct> rwaInstSpvProducts = rwaInstSpvProductMapper.selectAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区避免时间差异问题
        Date today = sdf.parse(sdf.format(new Date())); // 获取今天的日期，不包含时间部分
        for(RwaInstSpvProduct rwaInstSpvProduct : rwaInstSpvProducts){
            if ("4".equals(rwaInstSpvProduct.getState())) {
                Date purchaseStartDate = rwaInstSpvProduct.getPurchaseStartDate();
                if (purchaseStartDate != null && isSameDay(purchaseStartDate, today)) {
                    log.info("产品状态为待开放且申购开始日期已到, 准备更新: {}", rwaInstSpvProduct.getId());
                    rwaInstSpvProduct.setState("5");
                    rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct); // 更新数据库中的记录
                }
            }
            if ("5".equals(rwaInstSpvProduct.getState())) {
                Date purchaseStartDate = rwaInstSpvProduct.getOperationStarDate();
                if (purchaseStartDate != null && isSameDay(purchaseStartDate, today)) {
                    log.info("产品状态为申购结束且运营开始日期已到, 准备更新: {}", rwaInstSpvProduct.getId());
                    rwaInstSpvProduct.setState("7");
                    rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct); // 更新数据库中的记录
                }
            }
            if ("7".equals(rwaInstSpvProduct.getState())) {
                Date purchaseStartDate = rwaInstSpvProduct.getOperationEndDate();
                if (purchaseStartDate != null && isSameDay(purchaseStartDate, today)) {
                    log.info("产品状态为运营结束且运营结束日期已到, 准备更新: {}", rwaInstSpvProduct.getId());
                    rwaInstSpvProduct.setState("8");
                    rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct); // 更新数据库中的记录
                }
            }
            log.info("产品状态更新成功: {}", rwaInstSpvProduct.getId());
        }
        log.info("===============开始执行产品定时任务================");
    }

    private boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date1).equals(sdf.format(date2));
    }

    @Override
    public RwaInstSpvProduct selectOneForUpdate(RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException {
        return rwaInstSpvProductMapper.selectOneForUpdate(rwaInstSpvProduct);
    }
}
