/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqDepositAddress;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.DepositAddress;
import com.anyex.apps.fund.service.DepositAddressService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.wallet.XMWalletApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 充值地址 控制器
 * <p>File：DepositAddressController.java </p>
 * <p>Title: DepositAddressController </p>
 * <p>Description:DepositAddressController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/depositAddress")
@Api(tags = "充值地址")
public class DepositAddressController extends GenericController
{
    @Autowired(required = false)
    private UserService userService;
    
    @Autowired(required = false)
    private DepositAddressService depositAddressService;

    @PostMapping(value = "/getDepositAddress")
    @ApiOperation(value = "获取充值地址", httpMethod = "POST")
    public JsonMessage<DepositAddress> getDepositAddress(@Validated @RequestBody ReqDepositAddress reqDepositAddress) throws BusinessException
    {
        DepositAddress depositAddressQuery = new DepositAddress();
        BeanUtils.copyProperties(depositAddressQuery, reqDepositAddress);
        depositAddressQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        DepositAddress depositAddressDB = depositAddressService.selectOne(depositAddressQuery);
        // 先写死
//        DepositAddress result = new DepositAddress();
//        result.setUserId(OnLineUserUtils.getPrincipal().getId());
//        result.setCurrency(reqDepositAddress.getCurrency());
//        result.setBlockchain(reqDepositAddress.getBlockchain());
//        result.setDepositAddress("0x123456");
//        result.setRemark("先写死");

        if(null != depositAddressDB) {
            // 数据库中已经存在对应公链对应币种的充值地址 直接返回
            return getJsonMessage(CommonEnums.SUCCESS, depositAddressDB);
            //
        } else {
            //
            User userDB = userService.selectByPrimaryKey(OnLineUserUtils.getPrincipal().getId());
            if(StringUtils.isEmpty(userDB.getRemark())){
                // 保存钱包API返回的 user_no字段值
                JSONObject jsonObjectResp = XMWalletApi.register_user();
                log.info("register_user jsonObjectResp:{}", jsonObjectResp);
                if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
                    JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
                    log.info("register_user jsonObjectData user_no: {}", jsonObjectData.getString("user_no"));
                    // user_no: e2a58b28-db67-4fc6-a27f-5d11d6de322e
                    userDB.setRemark(jsonObjectData.getString("user_no"));
                    log.info("userDB: {}", userDB);
                    userService.updateByPrimaryKeySelective(userDB);
                } else {
                    log.error("XMWalletApi.register_user() error:{}", jsonObjectResp);
                    throw new BusinessException("call XMWalletApi register_user error, please check!");
                }
            }
            //
            if("BTC".equals(reqDepositAddress.getCurrency())){
                JSONObject jsonObjectResp = XMWalletApi.get_address(userDB.getRemark(), "BTC编码");
                log.info("get_address jsonObjectResp:{}", jsonObjectResp);
                if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
                    JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
                    log.info("get_address jsonObjectData address: {}", jsonObjectData.getString("address"));
                    // address: 0x39f03686b2d673f94f0b555e42bf33167cf033e2
                    DepositAddress result = new DepositAddress();
                    result.setUserId(OnLineUserUtils.getPrincipal().getId());
                    result.setCurrency(reqDepositAddress.getCurrency());
                    result.setBlockchain(reqDepositAddress.getBlockchain());
                    result.setDepositAddress(jsonObjectData.getString("address"));
                    result.setAccDeposit(BigDecimal.ZERO);
                    result.setUnconfAccDeposit(BigDecimal.ZERO);
                    result.setRemark("address init");
                    log.info("插入 depositAddress: {}", result);
                    depositAddressService.insert(result);
                    return getJsonMessage(CommonEnums.SUCCESS, result);
                    //
                } else {
                    log.error("XMWalletApi.get_address error:{}", jsonObjectResp);
                    throw new BusinessException("call XMWalletApi get_address error, please check!");
                }
            } else if("ETH".equals(reqDepositAddress.getCurrency())){
                JSONObject jsonObjectResp = XMWalletApi.get_address(userDB.getRemark(), "c2577bd5-9043-4bfd-ae88-177673a533e4");
                log.info("get_address jsonObjectResp:{}", jsonObjectResp);
                if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
                    JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
                    log.info("get_address jsonObjectData address: {}", jsonObjectData.getString("address"));
                    // address: 0x39f03686b2d673f94f0b555e42bf33167cf033e2
                    DepositAddress result = new DepositAddress();
                    result.setUserId(OnLineUserUtils.getPrincipal().getId());
                    result.setCurrency(reqDepositAddress.getCurrency());
                    result.setBlockchain(reqDepositAddress.getBlockchain());
                    result.setDepositAddress(jsonObjectData.getString("address"));
                    result.setAccDeposit(BigDecimal.ZERO);
                    result.setUnconfAccDeposit(BigDecimal.ZERO);
                    result.setRemark("address init");
                    log.info("插入 depositAddress: {}", result);
                    depositAddressService.insert(result);
                    return getJsonMessage(CommonEnums.SUCCESS, result);
                    //
                } else {
                    log.error("XMWalletApi.get_address error:{}", jsonObjectResp);
                    throw new BusinessException("call XMWalletApi get_address error, please check!");
                }
            } else if("USDT".equals(reqDepositAddress.getCurrency())){
                return getJsonMessage(CommonEnums.SUCCESS, null);
            } else {
                return getJsonMessage(CommonEnums.SUCCESS, null);
            }
            //
        }
    }

//    @PostMapping(value = "/data")
//    @ApiOperation(value = "查询充值地址列表", httpMethod = "POST")
//    public JsonMessage<PaginateResult<DepositAddress>> data(@Validated @RequestBody ReqDepositAddressPagination pagin) throws BusinessException
//    {
//        DepositAddress depositAddressQuery = new DepositAddress();
//        BeanUtils.copyProperties(pagin, depositAddressQuery);
//        depositAddressQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
//        //
//        PaginateResult<DepositAddress> result = depositAddressService.search(pagin, depositAddressQuery);
//        return getJsonMessage(CommonEnums.SUCCESS, result);
//    }
//
//    @GetMapping(value = "/findBy")
//    @ApiOperation(value = "根据ID取充值地址", httpMethod = "GET")
//    public JsonMessage<DepositAddress> findBy(Long id) throws BusinessException
//    {
//        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        return this.getJsonMessage(CommonEnums.SUCCESS, depositAddressService.selectByPrimaryKey(id));
//    }
}
