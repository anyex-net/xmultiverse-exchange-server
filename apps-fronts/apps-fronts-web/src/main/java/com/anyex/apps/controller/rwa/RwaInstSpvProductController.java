/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.*;
import com.anyex.apps.controller.rwa.resp.RespRwaInstSpvProduct;
import com.anyex.apps.controller.rwa.resp.RespRwaInstSpvProductAsset;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;;
import com.anyex.apps.rwa.entity.*;
import com.anyex.apps.rwa.model.RwaDividendSnapshotInfoResultModel;
import com.anyex.apps.rwa.service.*;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.exchange.contract.api.ContractDepositApi;
import com.anyex.exchange.contract.api.ContractDividendApi;
import com.anyex.exchange.contract.req.ReqDeposit;
import com.anyex.exchange.contract.req.ReqDividend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * RWA机构SPV产品 控制器
 * <p>File：RwaInstSpvProductController.java </p>
 * <p>Title: RwaInstSpvProductController </p>
 * <p>Description:RwaInstSpvProductController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProduct")
@Api(tags = "RWA机构SPV产品")
public class RwaInstSpvProductController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductService rwaInstSpvProductService;

    @Autowired(required = false)
    private RwaCertInstSpvPromoterService rwaCertInstSpvPromoterService;

    @Autowired(required = false)
    private RwaInstSpvCompanyService rwaInstSpvCompanyService;

    @Autowired(required = false)
    private RwaInstSpvProductDividendService rwaInstSpvProductDividendService;

    @Autowired(required = false)
    private RwaCertInstInvestorService rwaCertInstInvestorService;

    @Autowired(required = false)
    private RwaInstSpvProductPurchaseService rwaInstSpvProductPurchaseService;

    @Autowired(required = false)
    private RwaInstSpvProductDividendSnapshotService rwaInstSpvProductDividendSnapshotService;

    @Autowired(required = false)
    private RwaInstSpvProductAssetService rwaInstSpvProductAssetService;

    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @Autowired(required = false)
    private RwaInstSpvProductRealizedIncomeService rwaInstSpvProductRealizedIncomeService;

    @Autowired(required = false)
    private UserService userService;

    @GetMapping(value = "/getRwaInstSpvProduct")
    @ApiOperation(value = "获取RWA机构SPV产品详情", httpMethod = "GET")
    public JsonMessage<RespRwaInstSpvProduct> getRwaInstSpvProduct(Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        RespRwaInstSpvProduct respRwaInstSpvProduct = new RespRwaInstSpvProduct();
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(id);
        BeanUtils.copyProperties(rwaInstSpvProduct,respRwaInstSpvProduct);

        //企业信息
        RwaInstSpvCompany rwaInstSpvCompany = rwaInstSpvCompanyService.selectByPrimaryKey(rwaInstSpvProduct.getInstSpvCompanyId());
        BeanUtils.copyProperties(rwaInstSpvCompany,respRwaInstSpvProduct);
        return this.getJsonMessage(CommonEnums.SUCCESS, respRwaInstSpvProduct);
    }

    @PostMapping(value = "/submitRwaInstSpvProduct")
    @ApiOperation(value = "提交RWA机构SPV产品", httpMethod = "POST")
    public JsonMessage submitRwaInstSpvProduct(@Validated @RequestBody ReqRwaInstSpvProduct reqRwaInstSpvProduct) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        //
        //只有spv发起人认证才能提交
        User user = userService.selectByPrimaryKey(principal.getId());
        if (user.getCertState() == 0) throw new BusinessException(CommonEnums.ERROR_USER_NOT_CERT);
        if (user.getCertState() != 3) throw new BusinessException(CommonEnums.ERROR_USER_CERT_STATE_NOT_CERT_INST_SPV);
        //
         //运营时间要在申购时间之后
        if (reqRwaInstSpvProduct.getOperationStarDate().compareTo(reqRwaInstSpvProduct.getPurchaseEndDate()) < 0){
            throw new BusinessException(CommonEnums.ERROR_RWA_INST_SPV_PRODUCT_OPERATION_DATE_ERROR);
        }
        //不能存在代币名称同名
        List<RwaInstSpvProduct> rwaInstSpvProductList = rwaInstSpvProductService.selectAll();
        rwaInstSpvProductList.forEach(rwaInstSpvProduct -> {
            if (rwaInstSpvProduct.getTokenName().equals(reqRwaInstSpvProduct.getTokenName())){
                log.error("代币名称重复");
                throw new BusinessException(CommonEnums.ERROR_RWA_INST_SPV_PRODUCT_TOKEN_NAME_EXIST);
            }
        });
        String tokenName = reqRwaInstSpvProduct.getTokenName();
        if ("BTC".equals(tokenName) || "ETH".equals(tokenName) || "USDT".equals(tokenName)) {
            log.error("代币名称重复");
            throw new BusinessException(CommonEnums.ERROR_RWA_INST_SPV_PRODUCT_TOKEN_NAME_EXIST);
        }
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqRwaInstSpvProduct))
        {
            RwaInstSpvProduct rwaInstSpvProduct = new RwaInstSpvProduct();
            BeanUtils.copyProperties(reqRwaInstSpvProduct, rwaInstSpvProduct);
            //
            if (null == reqRwaInstSpvProduct.getId())
            {
                rwaInstSpvProduct.setCreateTime(System.currentTimeMillis());
                rwaInstSpvProduct.setUserId(principal.getId());
            }
            rwaInstSpvProduct.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProduct.setState("-1");
            RwaCertInstSpvPromoter rwaCertInstSpvPromoter = new RwaCertInstSpvPromoter();
            rwaCertInstSpvPromoter.setUserId(principal.getId());
            RwaCertInstSpvPromoter rwaCertInstSpvPromoter1 = rwaCertInstSpvPromoterService.selectOne(rwaCertInstSpvPromoter);
            rwaInstSpvProduct.setInstSpvPromoterId(rwaCertInstSpvPromoter1.getId());
            rwaInstSpvProduct.setRaiseMarginState(0);
            rwaInstSpvProduct.setPurchasedSumAmount(BigDecimal.valueOf(0));
            rwaInstSpvProduct.setDividendFreezeDays(0);
            rwaInstSpvProduct.setDividendFrequency("0");
            rwaInstSpvProduct.setIsActive(1);
            rwaInstSpvProduct.setRaiseMargin(BigDecimal.valueOf(0));
            //
            Random random = new Random();
            // 生成 [10000000, 99999999] 之间的整数
            int number = random.nextInt(90000000) + 10000000;
            rwaInstSpvProduct.setProductNo(String.valueOf(number));
            rwaInstSpvProduct.setDividendRatio(BigDecimal.valueOf(0));
            rwaInstSpvProduct.setDividendDate(Date.valueOf("1970-01-01"));
            log.info("entity:{}", rwaInstSpvProduct);
            if(null == rwaInstSpvProduct.getId()){
                rwaInstSpvProductService.insert(rwaInstSpvProduct);
            } else {
                rwaInstSpvProductService.updateByPrimaryKeySelective(rwaInstSpvProduct);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA机构SPV产品列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProduct>> data(@Validated @RequestBody ReqRwaInstSpvProductPagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProduct rwaInstSpvProduct = new RwaInstSpvProduct();
        BeanUtils.copyProperties(pagin, rwaInstSpvProduct);
        rwaInstSpvProduct.setUserId(principal.getId());
        PaginateResult<RwaInstSpvProduct> result = rwaInstSpvProductService.search(pagin,rwaInstSpvProduct);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/submitRaiseMargin")
    @ApiOperation(value = "提交保证金", httpMethod = "POST")
    public JsonMessage submitRaiseMargin(@Validated @RequestBody ReqRwaInstSpvProductRaiseMargin reqRwaInstSpvProductRaiseMargin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        //只有spv发起人认证才能提交
        User user = userService.selectByPrimaryKey(principal.getId());
        if (user.getCertState() == 0) throw new BusinessException(CommonEnums.ERROR_USER_NOT_CERT);
        if (user.getCertState() != 3) throw new BusinessException(CommonEnums.ERROR_USER_CERT_STATE_NOT_CERT_INST_SPV);

        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(reqRwaInstSpvProductRaiseMargin.getId());
        if (null == rwaInstSpvProduct)
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        rwaInstSpvProduct.setUserId(principal.getId());
        rwaInstSpvProduct.setRaiseMargin(reqRwaInstSpvProductRaiseMargin.getRaiseMargin());
        //交保证金冻结可用余额
        rwaBalancesService.raiseMarginFrozenBal(rwaInstSpvProduct);
        //
        rwaInstSpvProduct.setRaiseMarginState(1);
        rwaInstSpvProduct.setState("0");
        rwaInstSpvProductService.updateByPrimaryKeySelective(rwaInstSpvProduct);
        return json;
    }

    @GetMapping(value = "/getRwaInstSpvProductAsset")
    @ApiOperation(value = "查询RWA机构SPV产品资产", httpMethod = "GET")
    public JsonMessage<RespRwaInstSpvProductAsset> getRwaInstSpvProductAsset(Long instSpvProductId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        if (null == instSpvProductId) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(instSpvProductId);

        RespRwaInstSpvProductAsset respRwaInstSpvProductAsset = new RespRwaInstSpvProductAsset();
        respRwaInstSpvProductAsset.setTokenName(rwaInstSpvProduct.getTokenName());
        respRwaInstSpvProductAsset.setTokenIssueNumber(rwaInstSpvProduct.getTokenIssueNumber());
        BigDecimal purchasedSumAmount = rwaInstSpvProduct.getPurchasedSumAmount();
        BigDecimal productAmount = rwaInstSpvProduct.getTokenIssueNumber().subtract(purchasedSumAmount);
        respRwaInstSpvProductAsset.setInvestorAmount(purchasedSumAmount);
        respRwaInstSpvProductAsset.setProductAmount(productAmount);
        //调整总融资为融资金额乘以已申购数量
        BigDecimal productPrice = rwaInstSpvProduct.getRaiseAmount().divide(rwaInstSpvProduct.getTokenIssueNumber(), 8, RoundingMode.HALF_UP);
        respRwaInstSpvProductAsset.setTotalAmount(purchasedSumAmount.multiply(productPrice));
        BigDecimal amount = rwaInstSpvProductAssetService.selectAmountSum(instSpvProductId);
        if (null == amount) {
            amount = BigDecimal.valueOf(0);
        }
        respRwaInstSpvProductAsset.setAmount(amount);
        return getJsonMessage(CommonEnums.SUCCESS, respRwaInstSpvProductAsset);
    }

    @PostMapping(value = "/rwaInstSpvProductAssetData")
    @ApiOperation(value = "查询RWA机构SPV产品资产申请解冻记录", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProductAsset>> rwaInstSpvProductAssetData(@Validated @RequestBody ReqRwaInstSpvProductAssetPagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProductAsset rwaInstSpvProductAsset = new RwaInstSpvProductAsset();
        BeanUtils.copyProperties(pagin, rwaInstSpvProductAsset);
        rwaInstSpvProductAsset.setUserId(principal.getId());
        PaginateResult<RwaInstSpvProductAsset> result = rwaInstSpvProductAssetService.search(pagin,rwaInstSpvProductAsset);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/submitRwaInstSpvProductAsset")
    @ApiOperation(value = "提交RWA机构SPV产品资产申请解冻", httpMethod = "POST")
    public JsonMessage submitRwaInstSpvProductAsset(@Validated @RequestBody ReqRwaInstSpvProductAsset reqRwaInstSpvProductAsset) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        //只有spv发起人认证才能提交
        User user = userService.selectByPrimaryKey(principal.getId());
        if (user.getCertState() == 0) throw new BusinessException(CommonEnums.ERROR_USER_NOT_CERT);
        if (user.getCertState() != 3) throw new BusinessException(CommonEnums.ERROR_USER_CERT_STATE_NOT_CERT_INST_SPV);

        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqRwaInstSpvProductAsset))
        {
            RwaInstSpvProductAsset rwaInstSpvProductAsset = new RwaInstSpvProductAsset();
            BeanUtils.copyProperties(reqRwaInstSpvProductAsset, rwaInstSpvProductAsset);
            //
            if (null == reqRwaInstSpvProductAsset.getId())
            {
                rwaInstSpvProductAsset.setCreateTime(System.currentTimeMillis());
                rwaInstSpvProductAsset.setUserId(principal.getId());
            }
//            rwaInstSpvProductAsset.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProductAsset.setState(0);
            RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductAsset.getInstSpvProductId());
            //查看数量是否满足
            // 查看申请数量
            BigDecimal lastAmountSum = rwaInstSpvProductAssetService.selectLastAmountSum(reqRwaInstSpvProductAsset.getInstSpvProductId());
            if (null == lastAmountSum) {
                lastAmountSum = BigDecimal.valueOf(0);
            }
            if (lastAmountSum.add(rwaInstSpvProductAsset.getLastAmount()).compareTo(rwaInstSpvProduct.getPurchasedSumAmount()) > 0) {
                log.error("申请解冻数量超出剩余数量");
                throw new BusinessException(CommonEnums.ERROR_RWA_USER_ASSET_AMOUNT_OVER_LIMIT);
            }
            rwaInstSpvProductAsset.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
            //
            log.info("entity:{}", rwaInstSpvProductAsset);
            if(null == rwaInstSpvProductAsset.getId()){
                rwaInstSpvProductAssetService.insert(rwaInstSpvProductAsset);
            } else {
                rwaInstSpvProductAssetService.updateByPrimaryKeySelective(rwaInstSpvProductAsset);
            }
        }
        return json;
    }

    @PostMapping(value = "/rwaInstSpvProductDividendData")
    @ApiOperation(value = "查询RWA机构SPV产品分红管理列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProductDividend>> rwaInstSpvProductDividendData(@Validated @RequestBody ReqRwaInstSpvProductDividendPagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProductDividend rwaInstSpvProductDividend = new RwaInstSpvProductDividend();
        BeanUtils.copyProperties(pagin, rwaInstSpvProductDividend);
        rwaInstSpvProductDividend.setUserId(principal.getId());
        PaginateResult<RwaInstSpvProductDividend> result = rwaInstSpvProductDividendService.search(pagin,rwaInstSpvProductDividend);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/submitRwaInstSpvProductDividend")
    @ApiOperation(value = "提交分红单", httpMethod = "POST")
    public JsonMessage submitRwaInstSpvProductDividend(@Validated @RequestBody ReqRwaInstSpvProductDividend reqrwaInstSpvProductDividend) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        //只有spv发起人认证才能提交
        User user = userService.selectByPrimaryKey(principal.getId());
        if (user.getCertState() == 0) throw new BusinessException(CommonEnums.ERROR_USER_NOT_CERT);
        if (user.getCertState() != 3) throw new BusinessException(CommonEnums.ERROR_USER_CERT_STATE_NOT_CERT_INST_SPV);

        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqrwaInstSpvProductDividend)) {
            RwaInstSpvProductDividend rwaInstSpvProductDividend = new RwaInstSpvProductDividend();
            BeanUtils.copyProperties(reqrwaInstSpvProductDividend, rwaInstSpvProductDividend);
            RwaInstSpvProductDividend rwaInstSpvProductDividendDB = rwaInstSpvProductDividendService.selectOne(rwaInstSpvProductDividend);
            if ("5".equals(rwaInstSpvProductDividendDB.getState())){
                log.error("还在申购阶段，不能进行分红");
                throw new BusinessException(CommonEnums.ERROR_RWA_PROUDCT_ISSUE_NOT_START);
            }
            //
            if (null == rwaInstSpvProductDividend.getId()) {
                rwaInstSpvProductDividend.setCreateTime(System.currentTimeMillis());
                rwaInstSpvProductDividend.setUserId(principal.getId());
            }
//            rwaInstSpvProductDividend.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProductDividend.setState("pending");
            //
            log.info("entity:{}", rwaInstSpvProductDividend);
            if (null == rwaInstSpvProductDividend.getId()) {
                rwaInstSpvProductDividendService.insert(rwaInstSpvProductDividend);
            } else {
                rwaInstSpvProductDividendService.updateByPrimaryKeySelective(rwaInstSpvProductDividend);
            }
        }
        return json;
    }

    @PostMapping(value = "/executedRwaInstSpvProductDividend")
    @ApiOperation(value = "执行分红单", httpMethod = "POST")
    public JsonMessage executedRwaInstSpvProductDividend(@Validated @RequestBody ReqRwaInstSpvProductDividend reqrwaInstSpvProductDividend) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqrwaInstSpvProductDividend)) {
            RwaInstSpvProductDividend rwaInstSpvProductDividend = new RwaInstSpvProductDividend();
            BeanUtils.copyProperties(reqrwaInstSpvProductDividend, rwaInstSpvProductDividend);
            RwaInstSpvProductDividend rwaInstSpvProductDividendDB = rwaInstSpvProductDividendService.selectOne(rwaInstSpvProductDividend);
            if ("6".equals(rwaInstSpvProductDividendDB.getState())){
                log.error("该产品为发行失败，不能进行分红");
                throw new BusinessException(CommonEnums.ERROR_RWA_PROUDCT_ISSUE_FAIL);
            }
            if (null == rwaInstSpvProductDividendDB) {
                log.error("没有找到分红单");
                throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
            }
            // 获取合约是否有存款
            RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductDividendDB.getInstSpvProductId());
            if (null == rwaInstSpvProduct) {
                log.error("没有找到产品");
                throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
            }
            RwaBalances rwaBalances = new RwaBalances();
            rwaBalances.setUserId(principal.getId());
            rwaBalances.setCurrency(reqrwaInstSpvProductDividend.getDividendCurrency());
            RwaBalances  rwaBalancesDB = rwaBalancesService.selectOne(rwaBalances);
            if (null == rwaBalancesDB) {
                log.error("没有找到用户rwa资产");
                throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
            }
            if (rwaBalancesDB.getAvailBal().compareTo(reqrwaInstSpvProductDividend.getDividendAmount()) < 0) {
                log.error("用户rwa可用余额资产不足,请充值");
                throw new BusinessException(CommonEnums.ERROR_RWA_USER_INSUFFICIENT_AVAILABLE_BALANCE);
            }
            //
            rwaInstSpvProductDividendService.executedRwaInstSpvProductDividend(rwaInstSpvProductDividendDB);

//            //余额足够支付分红金额，可用余额减少，冻结余额增加
//            rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().subtract(reqrwaInstSpvProductDividend.getDividendAmount()));
//            rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().add(reqrwaInstSpvProductDividend.getDividendAmount()));
//            rwaBalancesService.updateByPrimaryKeySelective(rwaBalancesDB);
//            //给项目方管理地址转分红金额 项目方地址0x104fE772a9c1269b57272eF42be1B27A8dAA9064
//
//            //应该从一个钱包到管理地址中，然后调用存入金额（分红合约就有总存入资金）
//            ReqDeposit reqDeposit = new ReqDeposit();
//            reqDeposit.setContract_address(rwaInstSpvProduct.getShareContractAddress());
//            reqDeposit.setDeposited_total(reqrwaInstSpvProductDividend.getDividendAmount());
//            JSONObject jsonDeposit = ContractDepositApi.deposit(reqDeposit);
//            if (jsonDeposit.getInteger("code") != 200) {
//                log.error("存入金额失败");
////                throw new BusinessException(CommonEnums.ERROR_CONTRACT_DEPOSIT_FAILED);
//            }else {
//                System.out.println("自动存入金额："+jsonDeposit);
//            }
//
//            //
//            ReqDividend reqDividend = new ReqDividend();
//            reqDividend.setContract_address(rwaInstSpvProduct.getShareContractAddress());
//            JSONObject jsonObject = ContractDividendApi.getDividend(reqDividend);
//            String walletAddress = "0x104fE772a9c1269b57272eF42be1B27A8dAA9064";
//            BigDecimal totalBalance = null;
//            if (jsonObject.getInteger("code") == 200) {
//                JSONObject data = jsonObject.getJSONObject("data");
//                BigDecimal depositedBD = new BigDecimal(new BigInteger(data.getString("deposited")), 18);
//                BigDecimal distributedBD = new BigDecimal(new BigInteger(data.getString("distributed")), 18);
//                if ("0".equals(data.getString("deposited")) || depositedBD.subtract(distributedBD).compareTo(rwaInstSpvProductDividend.getDividendAmount()) < 0) {
//                    log.error("分红合约地址存款不足");
//                    throw new BusinessException(CommonEnums.ERROR_RWA_CONTRACT_DIVIDEND_DEPOSIT_NOT_ENOUGH);
//                }
//                reqDividend.setProject_address(rwaInstSpvProduct.getTokenContractAddress());
//                reqDividend.setAmount(rwaInstSpvProductDividend.getDividendAmount());
//                JSONObject jsonDividend = ContractDividendApi.dividend(reqDividend);
//                if (jsonDividend.getInteger("code") == 200) {
//                    System.out.println("jsonDividend:"+jsonDividend);
//                    rwaInstSpvProductDividend.setState("success");
//                    JSONObject dataDividend = jsonDividend.getJSONObject("data");
//                    String txHash = dataDividend.getString("txHash");
//                    log.info("分红交易：" + dataDividend.getString("txHash"));
//                    totalBalance  = new BigDecimal(new BigInteger(dataDividend.getString("totalBalance")), 18);
//                    System.out.println("最新分红总额：" + totalBalance);
//
//                    //该产品下的申购记录 根据记录进行分红
////            RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductDividend1.getInstSpvProductId());
//                    RwaInstSpvProductPurchase rwaInstSpvProductPurchase = new RwaInstSpvProductPurchase();
//                    rwaInstSpvProductPurchase.setInstSpvProductId(rwaInstSpvProductDividendDB.getInstSpvProductId());
//                    List<RwaInstSpvProductPurchase> rwaInstSpvProductPurchases = rwaInstSpvProductPurchaseService.findList(rwaInstSpvProductPurchase);
//                    for (int i = 0; i < rwaInstSpvProductPurchases.size(); i++) {
//                        RwaInstSpvProductDividendSnapshot productDividendSnapshot = new RwaInstSpvProductDividendSnapshot();
//                        productDividendSnapshot.setUserId(rwaInstSpvProductPurchases.get(i).getUserId());
//                        productDividendSnapshot.setInstInvestorId(rwaInstSpvProductPurchases.get(i).getInstInvestorId());
//                        productDividendSnapshot.setInstSpvProductId(rwaInstSpvProductPurchases.get(i).getInstSpvProductId());
//
//                        productDividendSnapshot.setInstSpvProductDividendNo(String.valueOf(rwaInstSpvProductDividendDB.getId()));
//                        productDividendSnapshot.setWalletAddress(walletAddress);
//                        productDividendSnapshot.setChainHoldAmount(totalBalance);
//                        productDividendSnapshot.setChainDividendAmount(rwaInstSpvProductDividendDB.getDividendAmount());
//                        BigDecimal holdAmount = rwaInstSpvProductPurchases.get(i).getPurchaseAmount();
//                        productDividendSnapshot.setHoldAmount(holdAmount);
//                        BigDecimal dividendAmount = holdAmount.divide(totalBalance, 8, RoundingMode.HALF_UP)
//                                .multiply(rwaInstSpvProductDividendDB.getDividendAmount())
//                                .setScale(8, RoundingMode.HALF_UP);
//                        productDividendSnapshot.setDividendAmount(dividendAmount);
//                        productDividendSnapshot.setCreateTime(System.currentTimeMillis());
//                        rwaInstSpvProductDividendSnapshotService.insert(productDividendSnapshot);
//                    }
//                }else {
//                    rwaInstSpvProductDividend.setState("failed");
//                }
//                rwaInstSpvProductDividendService.updateByPrimaryKeySelective(rwaInstSpvProductDividend);
//            }
        }
        return json;
    }

    @PostMapping(value = "/rwaInstSpvProductDividendInfo")
    @ApiOperation(value = "获取RWA机构SPV产品分红详情", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaDividendSnapshotInfoResultModel>> rwaInstSpvProductDividendInfo(@Validated @RequestBody ReqRwaInstSpvProductDividendSnapshotPagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        RwaInstSpvProductDividendSnapshot rwaInstSpvProductDividendSnapshot = new RwaInstSpvProductDividendSnapshot();
        BeanUtils.copyProperties(pagination, rwaInstSpvProductDividendSnapshot);
        PaginateResult<RwaDividendSnapshotInfoResultModel> result = rwaInstSpvProductDividendSnapshotService.selectGroupByUserId(pagination,rwaInstSpvProductDividendSnapshot);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/reqRwaInstSpvProductRealizedIncomeData")
    @ApiOperation(value = "查询RWA机构SPV产品实际收入列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ReqRwaInstSpvProductRealizedIncome>> reqRwaInstSpvProductRealizedIncomeData(@Validated @RequestBody ReqRwaInstSpvProductRealizedIncomePagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProductRealizedIncome rwaInstSpvProductRealizedIncome = new RwaInstSpvProductRealizedIncome();
        BeanUtils.copyProperties(pagin, rwaInstSpvProductRealizedIncome);
        rwaInstSpvProductRealizedIncome.setUserId(principal.getId());
        PaginateResult<RwaInstSpvProductRealizedIncome> result = rwaInstSpvProductRealizedIncomeService.search(pagin,rwaInstSpvProductRealizedIncome);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/submitReqRwaInstSpvProductRealizedIncome")
    @ApiOperation(value = "提交RWA机构SPV产品实际收入（批量）", httpMethod = "POST")
    public JsonMessage submitReqRwaInstSpvProductRealizedIncome(@Validated @RequestBody ReqRwaInstSpvProductRealizedIncomeBatch reqRwaInstSpvProductRealizedIncomeBatch) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        //只有spv发起人认证才能提交
        User user = userService.selectByPrimaryKey(principal.getId());
        if (user.getCertState() == 0) throw new BusinessException(CommonEnums.ERROR_USER_NOT_CERT);
        if (user.getCertState() != 3) throw new BusinessException(CommonEnums.ERROR_USER_CERT_STATE_NOT_CERT_INST_SPV);

        List<RwaInstSpvProductRealizedIncome> requestsToSave = reqRwaInstSpvProductRealizedIncomeBatch.getBatchRequests().stream()
                .map(req -> {
                    RwaInstSpvProductRealizedIncome entity = new RwaInstSpvProductRealizedIncome();
                    entity.setId(SerialnoUtils.buildPrimaryKey());
                    entity.setUserId(principal.getId());
                    entity.setInstSpvProductId(req.getInstSpvProductId());
                    entity.setIncomeDistributionDate(req.getIncomeDistributionDate());
                    entity.setIncomeAmount(req.getIncomeAmount());
                    entity.setIncomeCurrency(req.getIncomeCurrency());
                    entity.setRemark(req.getRemark());
                    entity.setCreateTime(System.currentTimeMillis());
                    return entity;
                })
                .collect(Collectors.toList());

        rwaInstSpvProductRealizedIncomeService.insertBatch(requestsToSave);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/rwaInstSpvProductPurchaseData")
    @ApiOperation(value = "RWA产品申购列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProductPurchase>> rwaInstSpvProductPurchaseData(@Validated @RequestBody ReqRwaInstSpvProductPurchasePagination pagin) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProductPurchase productPurchase = new RwaInstSpvProductPurchase();
        BeanUtils.copyProperties(pagin, productPurchase);
        PaginateResult<RwaInstSpvProductPurchase> result = rwaInstSpvProductPurchaseService.search(pagin, productPurchase);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

}
