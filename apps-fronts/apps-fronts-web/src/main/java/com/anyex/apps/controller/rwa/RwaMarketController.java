package com.anyex.apps.controller.rwa;


import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.*;
import com.anyex.apps.controller.rwa.resp.RespRwaMarketPrEnterprise;
import com.anyex.apps.controller.rwa.resp.RespRwaMarketTokenInfo;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.DepositAddress;
import com.anyex.apps.fund.service.DepositAddressService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.*;
import com.anyex.apps.rwa.service.*;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.exchange.contract.api.ContractMintApi;
import com.anyex.exchange.contract.req.ReqMint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private DepositAddressService depositAddressService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA市场产品列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<ReqRwaInstSpvProduct>> data(@Validated @RequestBody ReqRwaInstSpvProductPagination pagin) throws BusinessException
    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProduct instSpvProduct = new RwaInstSpvProduct();
        BeanUtils.copyProperties(pagin, instSpvProduct);
        PaginateResult<RwaInstSpvProduct> rwaInstSpvProducts = rwaInstSpvProductService.findListByState(pagin,instSpvProduct);
        return getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProducts);
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

        RwaInstSpvProductPurchase rwaInstSpvProductPurchase = new RwaInstSpvProductPurchase();
        BeanUtils.copyProperties(reqRwaInstSpvProductPurchase, rwaInstSpvProductPurchase);
        rwaInstSpvProductPurchase.setUserId(principal.getId());
        //中心化业务处理
        rwaInstSpvProductPurchaseService.submitRwaInstSpvProductPurchase(rwaInstSpvProductPurchase);

//        //给用户铸造代币
//        //先找用户eth钱包地址有无
//        DepositAddress depositAddress = new DepositAddress();
//        depositAddress.setUserId(principal.getId());
//        depositAddress.setCurrency("ETH");
//        DepositAddress depositAddressDB = depositAddressService.selectOne(depositAddress);
//        if (null == depositAddressDB) {
//            log.error("用户ETH钱包地址不存在");
//            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
//        }

//        //钱包存在 进行铸币
//        ReqMint reqMint = new ReqMint();
//        reqMint.setRecipient_address(depositAddressDB.getDepositAddress());
//
//        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(rwaInstSpvProductPurchase.getInstSpvProductId());
//        reqMint.setContract_address(rwaInstSpvProduct.getTokenContractAddress());
//        reqMint.setAmount(rwaInstSpvProductPurchase.getPurchaseAmount());
//        JSONObject jsonObject = ContractMintApi.mint(reqMint);
//        if (jsonObject.getInteger("code") != 200) {
//            log.error("代币铸币失败");
//            throw new BusinessException(CommonEnums.ERROR_RWA_TOKEN_MINT_FAIL);
//        }else {
//            System.out.println(jsonObject);
//
//        }

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
        if (null == distributedAmount) distributedAmount = BigDecimal.ZERO;
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
