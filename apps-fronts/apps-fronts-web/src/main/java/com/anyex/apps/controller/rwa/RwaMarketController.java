package com.anyex.apps.controller.rwa;


import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.*;
import com.anyex.apps.controller.rwa.resp.RespRwaMarketList;
import com.anyex.apps.controller.rwa.resp.RespRwaMarketPrEnterprise;
import com.anyex.apps.controller.rwa.resp.RespRwaMarketTokenInfo;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.*;
import com.anyex.apps.rwa.service.*;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
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

@Slf4j
@RestController
@RequestMapping("/rwa/rwaMarket")
@Api(tags = "RWA市场")
public class RwaMarketController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductService rwaInstSpvProductService;

    @Autowired(required = false)
    private RwaCertInstSpvPromoterService rwaCertInstSpvPromoterService;

    @Autowired(required = false)
    private RwaInstSpvCompanyService rwaInstSpvCompanyService;

    @Autowired(required = false)
    private RwaCertInstInvestorService rwaCertInstInvestorService;

    @Autowired(required = false)
    private RwaInstSpvProductPurchaseService rwaInstSpvProductPurchaseService;

    @Autowired(required = false)
    private RwaInstSpvProductDividendService rwaInstSpvProductDividendService;

    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @Autowired(required = false)
    private RwaInstSpvProductRealizedIncomeService rwaInstSpvProductRealizedIncomeService;

    @Autowired(required = false)
    private RwaInstSpvProductNoticeService rwaInstSpvProductNoticeService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA市场产品列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RespRwaMarketList>> data(@Validated @RequestBody ReqRwaInstSpvProductPagination pagin) throws BusinessException
    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProduct instSpvProduct = new RwaInstSpvProduct();
        BeanUtils.copyProperties(pagin, instSpvProduct);
        PaginateResult<RwaInstSpvProduct> rwaInstSpvProducts = rwaInstSpvProductService.findListByState(pagin,instSpvProduct);
        List<RespRwaMarketList> responseList = rwaInstSpvProducts.getRecords().stream().map(rwaInstSpvProduct -> {
            RespRwaMarketList respRwaMarketList = new RespRwaMarketList();
            respRwaMarketList.setId(rwaInstSpvProduct.getId());
            respRwaMarketList.setProductNo(rwaInstSpvProduct.getProductNo());
            respRwaMarketList.setProductName(rwaInstSpvProduct.getProductName());
            respRwaMarketList.setTokenName(rwaInstSpvProduct.getTokenName());
            respRwaMarketList.setTokenLogo(rwaInstSpvProduct.getTokenLogo());
            respRwaMarketList.setTokenIssueNumber(rwaInstSpvProduct.getTokenIssueNumber());
            respRwaMarketList.setRaiseCurrency(rwaInstSpvProduct.getRaiseCurrency());
            respRwaMarketList.setRaiseAmount(rwaInstSpvProduct.getRaiseAmount());
            respRwaMarketList.setAssetEndValuation(rwaInstSpvProduct.getAssetEndValuation());
            respRwaMarketList.setIssueDays(rwaInstSpvProduct.getIssueDays());
            respRwaMarketList.setState(rwaInstSpvProduct.getState());
            respRwaMarketList.setPurchaseStartDate(rwaInstSpvProduct.getPurchaseStartDate());
            respRwaMarketList.setPurchaseEndDate(rwaInstSpvProduct.getPurchaseEndDate());
            return respRwaMarketList;
        }).collect(Collectors.toList());
        PaginateResult<RespRwaMarketList> result = new PaginateResult<>(pagin,responseList);
        result.setRecords(responseList);
        result.setTotal((long)rwaInstSpvProducts.getTotal());
        result.setCurrent(rwaInstSpvProducts.getCurrent());
        result.setSize(rwaInstSpvProducts.getSize());
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/getRwaMarketPrDetail")
    @ApiOperation(value = "获取RWA市场产品详情", httpMethod = "GET")
    public JsonMessage<RwaInstSpvProduct> getRwaMarketPrDetail(Long id) throws BusinessException
    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductService.selectByPrimaryKey(id));
    }

    @GetMapping(value = "/getRwaMarketPrEnterprise")
    @ApiOperation(value = "获取RWA市场产品企业", httpMethod = "GET")
    public JsonMessage<RespRwaMarketPrEnterprise> getRwaMarketPrEnterprise(Long id) throws BusinessException
    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(id);
        if (rwaInstSpvProduct == null) {
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }

        RespRwaMarketPrEnterprise respRwaMarketPrEnterprise = new RespRwaMarketPrEnterprise();
        //spv发起人信
        RwaCertInstSpvPromoter certInstSpvPromoter = rwaCertInstSpvPromoterService.selectByPrimaryKey(rwaInstSpvProduct.getInstSpvPromoterId());
        BeanUtils.copyProperties(certInstSpvPromoter, respRwaMarketPrEnterprise);

        //企业信息
        RwaInstSpvCompany rwaInstSpvCompany = rwaInstSpvCompanyService.selectByPrimaryKey(rwaInstSpvProduct.getInstSpvCompanyId());
        BeanUtils.copyProperties(rwaInstSpvCompany, respRwaMarketPrEnterprise);
        return this.getJsonMessage(CommonEnums.SUCCESS, respRwaMarketPrEnterprise);
    }

    @PostMapping(value = "/submitRwaInstSpvProductPurchase")
    @ApiOperation(value = "提交申购记录", httpMethod = "POST")
    public JsonMessage submitRwaInstSpvProductPurchase(@Validated @RequestBody ReqRwaInstSpvProductPurchase reqRwaInstSpvProductPurchase) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //

        RwaInstSpvProductPurchase rwaInstSpvProductPurchase = new RwaInstSpvProductPurchase();
        BeanUtils.copyProperties(reqRwaInstSpvProductPurchase, rwaInstSpvProductPurchase);
        rwaInstSpvProductPurchase.setUserId(principal.getId());
        //
         //先查询可用余额是否足够
        rwaBalancesService.purchaseFrozenBalCheckBefore(rwaInstSpvProductPurchase);
        //
        RwaCertInstInvestor rwaCertInstInvestor = new RwaCertInstInvestor();
        rwaCertInstInvestor.setUserId(principal.getId());
        RwaCertInstInvestor rwaCertInstInvestor1 = rwaCertInstInvestorService.selectOne(rwaCertInstInvestor);
        if (null == reqRwaInstSpvProductPurchase.getId())
        {
            rwaInstSpvProductPurchase.setInstInvestorId(rwaCertInstInvestor1.getId());
            rwaInstSpvProductPurchase.setCreateTime(System.currentTimeMillis());
        }
//        rwaInstSpvProductPurchase.setUpdateTime(System.currentTimeMillis());
        rwaInstSpvProductPurchase.setState("pending");
        //
        log.info("rwaInstSpvProductPurchase:{}", rwaInstSpvProductPurchase);
        if(null == rwaInstSpvProductPurchase.getId()){
            rwaInstSpvProductPurchaseService.insert(rwaInstSpvProductPurchase);
        } else {
            rwaInstSpvProductPurchaseService.updateByPrimaryKeySelective(rwaInstSpvProductPurchase);
        }
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductPurchase.getInstSpvProductId());
        rwaInstSpvProduct.setPurchasedSumAmount(rwaInstSpvProduct.getPurchasedSumAmount().add(rwaInstSpvProductPurchase.getPurchaseAmount()));
        rwaInstSpvProductService.updateByPrimaryKeySelective(rwaInstSpvProduct);
        //
        return json;
    }

    @GetMapping(value = "/getRwaMarketTokenInfo")
    @ApiOperation(value = "获取市场代币信息", httpMethod = "GET")
    public JsonMessage<RespRwaMarketTokenInfo> getRwaMarketTokenInfo(Long id) throws BusinessException
    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(id);
        RespRwaMarketTokenInfo respRwaMarketTokenInfo = new RespRwaMarketTokenInfo();
        respRwaMarketTokenInfo.setTokenName(rwaInstSpvProduct.getTokenName());
        respRwaMarketTokenInfo.setTokenIssueNumber(rwaInstSpvProduct.getTokenIssueNumber());

        RwaInstSpvProductPurchase rwaInstSpvProductPurchase = new RwaInstSpvProductPurchase();
        rwaInstSpvProductPurchase.setInstSpvProductId(id);
        List<RwaInstSpvProductPurchase> rwaInstSpvProductPurchases = rwaInstSpvProductPurchaseService.findList(rwaInstSpvProductPurchase);
        respRwaMarketTokenInfo.setHolderCount(rwaInstSpvProductPurchases.size());

        BigDecimal distributedAmount = rwaInstSpvProductDividendService.selectDividendAmount(id);
        respRwaMarketTokenInfo.setDistributedAmount(distributedAmount);

        return this.getJsonMessage(CommonEnums.SUCCESS, respRwaMarketTokenInfo);
    }

    @PostMapping(value = "/rwaInstSpvProductNoticeData")
    @ApiOperation(value = "获取市场产品公告", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProductNotice>> rwaInstSpvProductNoticeData(@Validated @RequestBody ReqRwaInstSpvProductNoticePagination pagin) throws BusinessException
    {
        RwaInstSpvProductNotice entity = new RwaInstSpvProductNotice();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvProductNotice> result = rwaInstSpvProductNoticeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/reqRwaInstSpvProductRealizedIncomeData")
    @ApiOperation(value = "获取市场产品实际收入列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ReqRwaInstSpvProductRealizedIncome>> reqRwaInstSpvProductRealizedIncomeData(@Validated @RequestBody ReqRwaInstSpvProductRealizedIncomePagination pagin) throws BusinessException
    {
        RwaInstSpvProductRealizedIncome rwaInstSpvProductRealizedIncome = new RwaInstSpvProductRealizedIncome();
        BeanUtils.copyProperties(pagin, rwaInstSpvProductRealizedIncome);
        PaginateResult<RwaInstSpvProductRealizedIncome> result = rwaInstSpvProductRealizedIncomeService.search(pagin,rwaInstSpvProductRealizedIncome);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
