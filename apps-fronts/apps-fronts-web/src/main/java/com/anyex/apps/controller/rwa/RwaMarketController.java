package com.anyex.apps.controller.rwa;


import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductPagination;
import com.anyex.apps.controller.rwa.resp.RespRwaMarketList;
import com.anyex.apps.controller.rwa.resp.RespRwaMarketPrEnterprise;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.RwaCertInstSpvPromoter;
import com.anyex.apps.rwa.entity.RwaInstSpvCompany;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.service.RwaCertInstSpvPromoterService;
import com.anyex.apps.rwa.service.RwaInstSpvCompanyService;
import com.anyex.apps.rwa.service.RwaInstSpvProductService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA市场产品列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RespRwaMarketList>> data(@Validated @RequestBody ReqRwaInstSpvProductPagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProduct InstSpvProduct = new RwaInstSpvProduct();
        BeanUtils.copyProperties(pagin, InstSpvProduct);
        List<RwaInstSpvProduct> rwaInstSpvProducts = rwaInstSpvProductService.findListByState(InstSpvProduct);
        List<RespRwaMarketList> responseList = rwaInstSpvProducts.stream().map(rwaInstSpvProduct -> {
            RespRwaMarketList respRwaMarketList = new RespRwaMarketList();
            respRwaMarketList.setId(rwaInstSpvProduct.getId());
            respRwaMarketList.setProductNo(rwaInstSpvProduct.getProductNo());
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
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/getRwaMarketPrDetail")
    @ApiOperation(value = "获取RWA市场产品详情", httpMethod = "GET")
    public JsonMessage<RwaInstSpvProduct> getRwaMarketPrDetail(Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductService.selectByPrimaryKey(id));
    }

    @GetMapping(value = "/getRwaMarketPrEnterprise")
    @ApiOperation(value = "获取RWA市场产品企业", httpMethod = "GET")
    public JsonMessage<RespRwaMarketPrEnterprise> getRwaMarketPrEnterprise(Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RespRwaMarketPrEnterprise respRwaMarketPrEnterprise = new RespRwaMarketPrEnterprise();
        //spv发起人信息
        RwaCertInstSpvPromoter rwaCertInstSpvPromoter = new RwaCertInstSpvPromoter();
        rwaCertInstSpvPromoter.setUserId(principal.getId());
        RwaCertInstSpvPromoter certInstSpvPromoter = rwaCertInstSpvPromoterService.selectOne(rwaCertInstSpvPromoter);
        BeanUtils.copyProperties(certInstSpvPromoter, respRwaMarketPrEnterprise);

        //企业信息
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductService.selectByPrimaryKey(id);
        if (rwaInstSpvProduct == null) {
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        RwaInstSpvCompany rwaInstSpvCompany = rwaInstSpvCompanyService.selectByPrimaryKey(rwaInstSpvProduct.getInstSpvCompanyId());
        BeanUtils.copyProperties(rwaInstSpvCompany, respRwaMarketPrEnterprise);
        return this.getJsonMessage(CommonEnums.SUCCESS, respRwaMarketPrEnterprise);
    }
}
