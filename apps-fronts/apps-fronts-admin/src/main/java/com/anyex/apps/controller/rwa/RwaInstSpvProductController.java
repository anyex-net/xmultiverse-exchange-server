/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.rwa.service.RwaBalancesService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.exchange.contract.api.ContractApi;
import com.anyex.exchange.contract.api.ContractDeployApi;
import com.anyex.exchange.contract.req.ReqDeploy;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.service.RwaInstSpvProductService;

import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * RWA机构SPV产品 控制器
 * <p>File：RwaInstSpvProductController.java </p>
 * <p>Title: RwaInstSpvProductController </p>
 * <p>Description:RwaInstSpvProductController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaInstSpvProduct")
@Api(tags = "RWA机构SPV产品")
public class RwaInstSpvProductController extends GenericController {
    @Autowired(required = false)
    private RwaInstSpvProductService rwaInstSpvProductService;

    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaInstSpvProduct:data")
    @ApiOperation(value = "根据ID取RWA机构SPV产品", httpMethod = "GET")
    public JsonMessage<RwaInstSpvProduct> findBy(Long id) throws BusinessException {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaInstSpvProductService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaInstSpvProduct:data")
    @ApiOperation(value = "查询RWA机构SPV产品", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProduct>> data(@ModelAttribute ReqRwaInstSpvProductPagination pagin) throws BusinessException {
        RwaInstSpvProduct entity = new RwaInstSpvProduct();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaInstSpvProduct> result = rwaInstSpvProductService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/check")
    @RequiresPermissions("rwa:rwaInstSpvProduct:check")
    @ApiOperation(value = "复核", httpMethod = "POST")
    public JsonMessage check(Long id, String state) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        RwaInstSpvProduct entity = rwaInstSpvProductService.selectByPrimaryKey(id);
        if ("1".equals(state)) {
            entity.setState(state);
            ReqDeploy reqDeploy = new ReqDeploy();
            reqDeploy.setToken_name(entity.getTokenName());
            reqDeploy.setToken_symbol(entity.getTokenName());//简写

            BigDecimal oneE18 = new BigDecimal("1000000000000000000");
            BigInteger totalSupply = entity.getRaiseAmount().multiply(oneE18).toBigIntegerExact();
            reqDeploy.setTotal_supply(totalSupply.toString());
            JSONObject jsonObject = ContractDeployApi.deploy(reqDeploy);
            if (jsonObject.getInteger("code") == 200) {
                JSONObject jsonData = jsonObject.getJSONObject("data");
                entity.setTokenContractAddress(jsonData.getString("project_token_address"));
                entity.setShareContractAddress(jsonData.getString("dividend_contract_address"));
                entity.setState("4");
//                rwaBalancesService.raiseMarginFrozenBal(entity);
            }
            if (jsonObject.getInteger("code") == 502){
                log.error(jsonObject.getString("msg"));
                entity.setState("6");
            }
            if (jsonObject.getInteger("code") == 500){
                throw new BusinessException(jsonObject.getString("msg"));
            }

        }else if ("2".equals(state)) {
            rwaBalancesService.raiseMarginFrozenBalUncheck(entity);
            entity.setState("2");
        }else {
            entity.setState(state);
        }
        if (principal != null) {
            entity.setCheckBy(principal.getUserName());
        }
        entity.setCheckTime(System.currentTimeMillis());
        rwaInstSpvProductService.updateByPrimaryKeySelective(entity);
        return json;
    }

    @PostMapping(value = "/checkIsActive")
    @RequiresPermissions("rwa:rwaInstSpvProduct:check")
    @ApiOperation(value = "上架（下架）", httpMethod = "POST")
    public JsonMessage checkIsActive(Long id, Integer isActive) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        RwaInstSpvProduct entity = rwaInstSpvProductService.selectByPrimaryKey(id);
        entity.setIsActive(isActive);
        if (principal != null) {
            entity.setCheckBy(principal.getUserName());
        }
        entity.setCheckTime(System.currentTimeMillis());
        rwaInstSpvProductService.updateByPrimaryKeySelective(entity);
        return json;
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("rwa:rwaInstSpvProduct:operator")
//    @ApiOperation(value = "保存RWA机构SPV产品", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqRwaInstSpvProduct info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            RwaInstSpvProduct entity = new RwaInstSpvProduct();
//            BeanUtils.copyProperties(info, entity);
//            //
//            if (null == info.getId())
//            {
//                entity.setCreateTime(System.currentTimeMillis());
//            }
//            entity.setUpdateTime(System.currentTimeMillis());
//            //
//            log.info("entity:{}", entity);
//            if(null == entity.getId()){
//                rwaInstSpvProductService.insert(entity);
//            } else {
//                rwaInstSpvProductService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("rwa:rwaInstSpvProduct:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        rwaInstSpvProductService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
