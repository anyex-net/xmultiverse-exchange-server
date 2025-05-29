/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductMapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private RwaBalancesService rwaBalancesService;

    @Autowired(required = false)
    private RwaInstSpvProductPurchaseService rwaInstSpvProductPurchaseService;



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

//    @Scheduled(fixedRate = 5 * 60 * 1000)
    @Scheduled(cron = "15 0 0 * * ?")
    public void productPerformTask() throws BusinessException, ParseException {
        log.info("===============开始执行产品定时任务================");

        RwaInstSpvProduct rwaInstSpvProduct = new RwaInstSpvProduct();
        List<RwaInstSpvProduct> products = rwaInstSpvProductMapper.findListByState(rwaInstSpvProduct);
        LocalDate today = LocalDate.now(ZoneId.of("GMT"));

        for (RwaInstSpvProduct product : products) {
            try {
                processSingleProduct(product, today);
            } catch (Exception e) {
                log.error("处理产品 {} 出现异常", product.getId(), e);
            }
        }

        log.info("===============结束执行产品定时任务================");
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void processSingleProduct(RwaInstSpvProduct rwaInstSpvProduct, LocalDate today) throws BusinessException {
        String state = rwaInstSpvProduct.getState();

        if ("4".equals(state)) {
            Date purchaseStartDate = rwaInstSpvProduct.getPurchaseStartDate();
            if (purchaseStartDate != null && isSameDay(purchaseStartDate, today)) {
                log.info("产品状态为待开放且申购开始日期已到, 准备更新: {}", rwaInstSpvProduct.getId());
                rwaInstSpvProduct.setState("5");
                rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct);
            }
        }

        if ("5".equals(state)) {
            Date purchaseEndDate = rwaInstSpvProduct.getPurchaseEndDate();
            Date operationStartDate = rwaInstSpvProduct.getOperationStarDate();

            if (purchaseEndDate != null && isSameDay(purchaseEndDate, today)) {
                BigDecimal raiseEstablished = rwaInstSpvProduct.getRaiseAmount()
                        .multiply(rwaInstSpvProduct.getRaiseEstablishedRatio());

                if (raiseEstablished.compareTo(rwaInstSpvProduct.getPurchasedSumAmount()) > 0) {
                    log.info("募集条件不成立, 状态更新为发行失败: {}", rwaInstSpvProduct.getId());
                    rwaInstSpvProduct.setState("6");
                    rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct);

                    // 保证金退还
                    rwaBalancesService.raiseMarginFrozenBalUncheck(rwaInstSpvProduct);

                    // 申购者退还
                    RwaInstSpvProductPurchase purchaseDB = new RwaInstSpvProductPurchase();
                    purchaseDB.setInstSpvProductId(rwaInstSpvProduct.getId());
                    List<RwaInstSpvProductPurchase> purchases = rwaInstSpvProductPurchaseService.findList(purchaseDB);
                    rwaBalancesService.purchaseFrozenBalUncheck(purchases);

                    // 账户余额失效
                    RwaBalances balancesDB = new RwaBalances();
                    balancesDB.setInstSpvProductId(rwaInstSpvProduct.getId());
                    List<RwaBalances> balances = rwaBalancesService.findList(balancesDB);

                    List<RwaBalances> rwaBalancesList = new ArrayList<>();
                    for (RwaBalances balance : balances) {
                        log.info("失效的rwaBalance: {}", balance);
                        balance.setRemark("Failed");
                        rwaBalancesList.add(balance);
                    }
                    rwaBalancesService.updateBatch(rwaBalancesList);
                    return;
                }

                if (operationStartDate != null) {
                    LocalDate opStartDate = operationStartDate.toInstant()
                            .atZone(ZoneId.of("GMT"))
                            .toLocalDate();
                    if (today.isBefore(opStartDate)) {
                        log.info("产品申购结束，运营尚未开始，进入待运营状态: {}", rwaInstSpvProduct.getId());
                        rwaInstSpvProduct.setState("9");
                        rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct);
                        return;
                    }
                }
            }

            if (operationStartDate != null && isSameDay(operationStartDate, today)) {
                log.info("产品状态为申购结束且运营开始日期已到, 准备更新: {}", rwaInstSpvProduct.getId());
                rwaInstSpvProduct.setState("7");
                rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct);
            }
        }

        if ("9".equals(state)) {
            Date operationStartDate = rwaInstSpvProduct.getOperationStarDate();
            if (operationStartDate != null && isSameDay(operationStartDate, today)) {
                log.info("产品状态为待运营且运营开始日期已到, 准备更新: {}", rwaInstSpvProduct.getId());
                rwaInstSpvProduct.setState("7");
                rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct);
            }
        }

        if ("7".equals(state)) {
            Date operationEndDate = rwaInstSpvProduct.getOperationEndDate();
            if (operationEndDate != null && isSameDay(operationEndDate, today)) {
                log.info("产品状态为运营结束且运营结束日期已到, 准备更新: {}", rwaInstSpvProduct.getId());
                rwaInstSpvProduct.setState("8");
                rwaInstSpvProductMapper.updateByPrimaryKeySelective(rwaInstSpvProduct);
            }
        }

        log.info("产品状态更新成功: {}", rwaInstSpvProduct.getId());
    }

    private boolean isSameDay(Date date1, LocalDate date2) {
        if (date1 == null) return false;
        // 使用系统默认时区来转换 Date 到 LocalDate
        LocalDate d1 = date1.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return d1.isEqual(date2);
    }

    @Override
    public RwaInstSpvProduct selectOneForUpdate(RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException {
        return rwaInstSpvProductMapper.selectOneForUpdate(rwaInstSpvProduct);
    }
}
