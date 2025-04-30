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
import com.anyex.apps.rwa.service.*;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
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
        respRwaInstSpvProductAsset.setTotalAmount(purchasedSumAmount);
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
            RwaCertInstInvestor rwaCertInstInvestor = new RwaCertInstInvestor();
            rwaCertInstInvestor.setUserId(principal.getId());
            RwaCertInstInvestor rwaCertInstInvestor1 = rwaCertInstInvestorService.selectOne(rwaCertInstInvestor);
            rwaInstSpvProductAsset.setInstInvestorId(rwaCertInstInvestor1.getId());
            RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductAsset.getInstSpvProductId());
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
            //
            if (null == rwaInstSpvProductDividend.getId()) {
                rwaInstSpvProductDividend.setCreateTime(System.currentTimeMillis());
                rwaInstSpvProductDividend.setUserId(principal.getId());
            }
//            rwaInstSpvProductDividend.setUpdateTime(System.currentTimeMillis());
            rwaInstSpvProductDividend.setState("pending");
            RwaCertInstInvestor rwaCertInstInvestor = new RwaCertInstInvestor();
            rwaCertInstInvestor.setUserId(principal.getId());
            RwaCertInstInvestor rwaCertInstInvestor1 = rwaCertInstInvestorService.selectOne(rwaCertInstInvestor);
            rwaInstSpvProductDividend.setInstInvestorId(rwaCertInstInvestor1.getId());
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
            RwaInstSpvProductDividend rwaInstSpvProductDividend1 = rwaInstSpvProductDividendService.selectOne(rwaInstSpvProductDividend);
            if (null == rwaInstSpvProductDividend.getId()) {
                throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
            }
            //该产品下的申购记录 根据记录进行分红
            RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductDividend1.getInstSpvProductId());
            RwaInstSpvProductPurchase rwaInstSpvProductPurchase = new RwaInstSpvProductPurchase();
            rwaInstSpvProductPurchase.setInstSpvProductId(rwaInstSpvProductDividend1.getInstSpvProductId());
            List<RwaInstSpvProductPurchase> rwaInstSpvProductPurchases = rwaInstSpvProductPurchaseService.findList(rwaInstSpvProductPurchase);
            for (int i = 0; i < rwaInstSpvProductPurchases.size(); i++) {
                RwaInstSpvProductDividendSnapshot productDividendSnapshot = new RwaInstSpvProductDividendSnapshot();
                productDividendSnapshot.setUserId(rwaInstSpvProductPurchases.get(i).getUserId());
                productDividendSnapshot.setInstInvestorId(rwaInstSpvProductPurchases.get(i).getInstInvestorId());
                productDividendSnapshot.setInstSpvProductId(rwaInstSpvProductPurchases.get(i).getInstSpvProductId());

                productDividendSnapshot.setInstSpvProductDividendNo(String.valueOf(rwaInstSpvProductDividend1.getId()));
                productDividendSnapshot.setWalletAddress("链上钱包地址");
                BigDecimal holdAmount = rwaInstSpvProductPurchases.get(i).getPurchaseAmount();
                productDividendSnapshot.setHoldAmount(holdAmount);
                BigDecimal dividendAmount = BigDecimal.ZERO;
                BigDecimal totalAmount = rwaInstSpvProduct.getTokenIssueNumber();
                dividendAmount = holdAmount.divide(totalAmount).multiply(rwaInstSpvProductDividend1.getDividendAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
                productDividendSnapshot.setDividendAmount(dividendAmount);
                productDividendSnapshot.setCreateTime(System.currentTimeMillis());
                rwaInstSpvProductDividendSnapshotService.insert(productDividendSnapshot);
            }
            rwaInstSpvProductDividend1.setState("success");
            rwaInstSpvProductDividendService.updateByPrimaryKeySelective(rwaInstSpvProductDividend);
        }
        return json;
    }

    @GetMapping(value = "/getRwaInstSpvProductDividend")
    @ApiOperation(value = "获取RWA机构SPV产品分红详情", httpMethod = "GET")
    public JsonMessage<PaginateResult<RwaInstSpvProductDividendSnapshot>> getRwaInstSpvProductDividend(@Validated @RequestBody ReqRwaInstSpvProductDividendSnapshotPagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        RwaInstSpvProductDividendSnapshot rwaInstSpvProductDividendSnapshot = new RwaInstSpvProductDividendSnapshot();
        BeanUtils.copyProperties(pagination, rwaInstSpvProductDividendSnapshot);
        PaginateResult<RwaInstSpvProductDividendSnapshot> result = rwaInstSpvProductDividendSnapshotService.search(pagination,rwaInstSpvProductDividendSnapshot);
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
