/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.DepositAddress;
import com.anyex.apps.fund.service.DepositAddressService;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.entity.RwaCertInstInvestor;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.mapper.RwaBalancesMapper;
import com.anyex.apps.rwa.model.RwaInstSpvProductPurchaseResultModel;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.exchange.contract.api.ContractMintApi;
import com.anyex.exchange.contract.req.ReqMint;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductPurchaseMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * RWA机构SPV产品申购记录 服务实现类
 * <p>File：RwaInstSpvProductPurchaseServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductPurchaseServiceImpl </p>
 * <p>Description:RwaInstSpvProductPurchaseServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class RwaInstSpvProductPurchaseServiceImpl extends GenericServiceImpl<RwaInstSpvProductPurchase> implements RwaInstSpvProductPurchaseService
{
    protected RwaInstSpvProductPurchaseMapper rwaInstSpvProductPurchaseMapper;

    private final RwaBalancesMapper rwaBalancesMapper;

    @Autowired(required = false)
    private RwaInstSpvProductService rwaInstSpvProductService;

    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private RwaCertInstInvestorService rwaCertInstInvestorService;

    @Autowired(required = false)
    private DepositAddressService depositAddressService;



    @Autowired(required = false)
    public RwaInstSpvProductPurchaseServiceImpl(RwaInstSpvProductPurchaseMapper rwaInstSpvProductPurchaseMapper, RwaBalancesMapper rwaBalancesMapper)
    {
        super(rwaInstSpvProductPurchaseMapper);
        this.rwaInstSpvProductPurchaseMapper = rwaInstSpvProductPurchaseMapper;
        this.rwaBalancesMapper = rwaBalancesMapper;
    }

    @Override
    public PaginateResult<RwaInstSpvProductPurchase> findListByRaiseUserId(Pagination pagin,RwaInstSpvProductPurchase rwaInstSpvProductPurchase,Long raiseUserId) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<RwaInstSpvProductPurchase> pageInfo = PageInfo.of(rwaInstSpvProductPurchaseMapper.findListByRaiseUserId(rwaInstSpvProductPurchase, raiseUserId));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        List<RwaInstSpvProductPurchase> result = pageInfo.getList();
        return new PaginateResult<>(pagin, result);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void submitRwaInstSpvProductPurchase(RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException {
        //只有投资机构或者个人认证认证才能提交
        User user = userService.selectByPrimaryKey(rwaInstSpvProductPurchase.getUserId());
        if (user.getCertState() == 0) {
            log.error("用户未认证");
            throw new BusinessException(CommonEnums.ERROR_USER_NOT_CERT);
        };
        if (user.getCertState() == 3) {
            log.error("用户认证状态不能进行申购！");
            throw new BusinessException(CommonEnums.ERROR_USER_CERT_STATE_NOT_PURCHASE);
        };
        //先查看提交的数量是否满足
        RwaInstSpvProduct rwaInstSpvProductNew = new RwaInstSpvProduct();
        rwaInstSpvProductNew.setId(rwaInstSpvProductPurchase.getInstSpvProductId());
        RwaInstSpvProduct rwaInstSpvProductDB = rwaInstSpvProductService.selectOneForUpdate(rwaInstSpvProductNew);
        BigDecimal purchaseAmount = rwaInstSpvProductPurchase.getPurchaseAmount();
        BigDecimal purchaseSumAmount = rwaInstSpvProductDB.getPurchasedSumAmount();
        if (purchaseAmount.add(purchaseSumAmount).compareTo(rwaInstSpvProductDB.getTokenIssueNumber()) > 0) {
            log.error("申购数量超出剩余数量");
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_PURCHASE_AMOUNT_OVER_LIMIT);
        }
        //先查询可用余额是否足够(usdt)
//        rwaBalancesService.purchaseFrozenBalCheckBefore(rwaInstSpvProductPurchase);

        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductPurchase.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalancesDB == null) {
            log.error("用户资产不存在，请充值");
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
        }
        BigDecimal purchaseBalance = rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice());
        if (purchaseBalance.compareTo(rwaBalancesDB.getAvailBal()) > 0) {
            log.error("用户可用余额不足");
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_INSUFFICIENT_AVAILABLE_BALANCE);
        }

        // 添加给用户铸币
        //先找用户eth钱包地址有无
        DepositAddress depositAddress = new DepositAddress();
        depositAddress.setUserId(user.getId());
        depositAddress.setCurrency("ETH");
        DepositAddress depositAddressDB = depositAddressService.selectOne(depositAddress);
        if (null == depositAddressDB) {
            log.error("用户ETH钱包地址不存在");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }

        //钱包存在 进行铸币
        ReqMint reqMint = new ReqMint();
        reqMint.setRecipient_address(depositAddressDB.getDepositAddress());

        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductPurchase.getInstSpvProductId());
        if (null == rwaInstSpvProduct){
            log.error("Spv产品不存在");
            throw new BusinessException(CommonEnums.ERROR_RWA_INST_SPV_PRODUCT_NOT_FOUND);
        }
        reqMint.setContract_address(rwaInstSpvProduct.getTokenContractAddress());
        reqMint.setAmount(rwaInstSpvProductPurchase.getPurchaseAmount());
        JSONObject jsonObject = ContractMintApi.mint(reqMint);
        if (jsonObject.getInteger("code") != 200) {
            log.error("代币铸币失败");
//            throw new BusinessException(CommonEnums.ERROR_RWA_TOKEN_MINT_FAIL);
            rwaInstSpvProductPurchase.setState("failed");
            log.info("rwaInstSpvProductPurchase failed:{}", rwaInstSpvProductPurchase);
            rwaInstSpvProductPurchase.setId(SerialnoUtils.buildPrimaryKey());
            rwaInstSpvProductPurchaseMapper.insert(rwaInstSpvProductPurchase);
        }else {
            System.out.println(jsonObject);
            //中心化业务处理
            //
            if (user.getCertState() == 2){
                RwaCertInstInvestor rwaCertInstInvestor = new RwaCertInstInvestor();
                rwaCertInstInvestor.setUserId(rwaInstSpvProductPurchase.getUserId());
                RwaCertInstInvestor rwaCertInstInvestor1 = rwaCertInstInvestorService.selectOne(rwaCertInstInvestor);
                rwaInstSpvProductPurchase.setInstInvestorId(rwaCertInstInvestor1.getId());
            }
            if (null == rwaInstSpvProductPurchase.getId())
            {
                rwaInstSpvProductPurchase.setCreateTime(System.currentTimeMillis());
            }
//        rwaInstSpvProductPurchase.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProductPurchase.setState("success");
            //
            log.info("rwaInstSpvProductPurchase:{}", rwaInstSpvProductPurchase);
            if(null == rwaInstSpvProductPurchase.getId()){
                rwaInstSpvProductPurchase.setId(SerialnoUtils.buildPrimaryKey());
                rwaInstSpvProductPurchaseMapper.insert(rwaInstSpvProductPurchase);
            }
            //申购者资产更新 总余额减少，冻结不变，可用减少
            rwaBalancesDB.setBalance(rwaBalancesDB.getBalance().subtract(purchaseBalance));
//        rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().subtract(purchaseBalance));
            rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().subtract(purchaseBalance));
            rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);
            //募集者 总余额增加，冻结增加，可用余额不变
//            RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductPurchase.getInstSpvProductId());

            RwaBalances rwaBalancesRaise = new RwaBalances();
            rwaBalancesRaise.setUserId(rwaInstSpvProduct.getUserId());
            rwaBalancesRaise.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
            RwaBalances rwaBalancesRaiseDB = rwaBalancesMapper.selectOneForUpdate(rwaBalancesRaise);
            if (null == rwaBalancesRaiseDB) {
                log.error("募集者的资产不存在");
                throw new BusinessException(CommonEnums.ERROR_RWA_RAISE_USER_BALANCE_NOT_FOUND);
            }
            rwaBalancesRaiseDB.setBalance(rwaBalancesRaiseDB.getBalance().add(purchaseBalance));
            rwaBalancesRaiseDB.setFrozenBal(rwaBalancesRaiseDB.getFrozenBal().add(purchaseBalance));
//        rwaBalancesRaiseDB.setAvailBal(rwaBalancesRaiseDB.getAvailBal().add(purchaseBalance));
            rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesRaiseDB);
            //更新Rwa产品总申购数量
//        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductPurchase.getInstSpvProductId());
            rwaInstSpvProduct.setPurchasedSumAmount(rwaInstSpvProduct.getPurchasedSumAmount().add(rwaInstSpvProductPurchase.getPurchaseAmount()));
            rwaInstSpvProductService.updateByPrimaryKeySelective(rwaInstSpvProduct);
            //

            //记录申购记录代币到Rwa账户
            RwaBalances rwaBalancesPurchase  = new RwaBalances();
            rwaBalancesPurchase.setUserId(rwaInstSpvProductPurchase.getUserId());
            rwaBalancesPurchase.setCurrency(rwaInstSpvProduct.getTokenName());
            RwaBalances rwaBalancesPurchaseDB = rwaBalancesService.selectOne(rwaBalancesPurchase);
            if (null != rwaBalancesPurchaseDB){
                BigDecimal totalPurchaseAmount = rwaInstSpvProductPurchaseMapper.findTotalPurchaseAmountByUserIdAndProductId(rwaInstSpvProductPurchase);
                //累计申购数量+现购数量
                BigDecimal balance = totalPurchaseAmount.multiply(rwaInstSpvProductPurchase.getPurchasePrice());
                rwaBalancesPurchaseDB.setBalance(balance);
//            rwaBalancesDB.setFrozenBal(BigDecimal.valueOf(0));
                rwaBalancesPurchaseDB.setAvailBal(balance);
                rwaBalancesService.updateByPrimaryKeySelective(rwaBalancesPurchaseDB);
            }else {
                rwaBalancesPurchase.setBalance(rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice()));
                rwaBalancesPurchase.setFrozenBal(BigDecimal.valueOf(0));
                rwaBalancesPurchase.setAvailBal(rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice()));
                rwaBalancesService.insert(rwaBalancesPurchase);
            }
        }
    }

    @Override
    public PaginateResult<RwaInstSpvProductPurchaseResultModel> findListRwaOrder(Pagination pagin, RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<RwaInstSpvProductPurchaseResultModel> pageInfo = PageInfo.of(rwaInstSpvProductPurchaseMapper.findListRwaOrder(rwaInstSpvProductPurchase));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        List<RwaInstSpvProductPurchaseResultModel> result = pageInfo.getList();
        return new PaginateResult<>(pagin, result);
    }
}
