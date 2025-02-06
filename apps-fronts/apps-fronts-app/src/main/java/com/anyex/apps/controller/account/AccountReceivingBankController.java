/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account;

import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.account.req.ReqAccountReceivingBank;
import com.anyex.apps.controller.account.req.ReqAccountReceivingBankPagination;
import com.anyex.apps.controller.common.req.ReqIdParam;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 账户收款银行 控制器
 * <p>File：AccountReceivingBankController.java </p>
 * <p>Title: AccountReceivingBankController </p>
 * <p>Description:AccountReceivingBankController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/accountReceivingBank")
@Api(tags = "账户收款银行")
public class AccountReceivingBankController extends GenericController
{
    @Autowired(required = false)
    private AccountReceivingBankService accountReceivingBankService;

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取账户收款银行", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "业务记录唯一Id", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<AccountReceivingBank> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        AccountReceivingBank accountReceivingBankDB = accountReceivingBankService.selectByPrimaryKey(id);
        if(null == accountReceivingBankDB || principal.getId().longValue() != accountReceivingBankDB.getAccountId().longValue())
        {
            log.error("非法请求");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountReceivingBankDB);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存或更新账户收款银行", httpMethod = "POST")
    public JsonMessage save(@RequestBody ReqAccountReceivingBank info) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AccountReceivingBank accountReceivingBank = new AccountReceivingBank();
            accountReceivingBank.setAccountId(principal.getId());
            if(StringUtils.equalsIgnoreCase(info.getAccountType(), GlobalConst.PAYMENT_ACCOUNTTYPE_BANK))
            {
                accountReceivingBank.setAccountType(GlobalConst.PAYMENT_ACCOUNTTYPE_BANK); //银行卡
            } else {
                accountReceivingBank.setAccountType(GlobalConst.PAYMENT_ACCOUNTTYPE_WALLET); //电子钱包
                accountReceivingBank.setBankName(info.getBankName());
            }
            List<AccountReceivingBank> listAccountReceivingBank = accountReceivingBankService.findList(accountReceivingBank);
            //
            BeanUtils.copyProperties(info, accountReceivingBank);
            accountReceivingBank.setAccountId(principal.getId());
            accountReceivingBank.setCreateTime(System.currentTimeMillis());
            accountReceivingBank.setUpdateTime(System.currentTimeMillis());
            //
            if(null != listAccountReceivingBank && listAccountReceivingBank.size() > 0)
            {
                AccountReceivingBank accountReceivingBankDB = listAccountReceivingBank.get(0);
                log.info("principal.getId().longValue():{}, accountReceivingBankDB:{}", principal.getId().longValue(), accountReceivingBankDB);
                if(null == accountReceivingBankDB || principal.getId().longValue() != accountReceivingBankDB.getAccountId().longValue())
                {
                    log.error("非法请求");
                    throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
                }
                accountReceivingBank.setStatus(0); // 0未验证
                accountReceivingBank.setId(accountReceivingBankDB.getId());
                log.info("更新accountReceivingBank:{}", accountReceivingBank);
                accountReceivingBankService.updateByPrimaryKey(accountReceivingBank);
            } else {
                accountReceivingBank.setStatus(0); // 0未验证
                log.info("插入accountReceivingBank:{}", accountReceivingBank);
                accountReceivingBankService.insert(accountReceivingBank);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询账户收款银行分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountReceivingBank>> data(@Validated @RequestBody ReqAccountReceivingBankPagination reqAccountReceivingBankPagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        AccountReceivingBank accountReceivingBank = new AccountReceivingBank();
        BeanUtils.copyProperties(reqAccountReceivingBankPagination, accountReceivingBank);
        accountReceivingBank.setAccountId(principal.getId());
        //
        PaginateResult<AccountReceivingBank> result = accountReceivingBankService.search(reqAccountReceivingBankPagination, accountReceivingBank);
        // 加工处理账户实际类型
        result.getRecords().stream().forEach(entity->
        {
            if(entity.getAccountType().equals(GlobalConst.PAYMENT_ACCOUNTTYPE_BANK)){
                entity.setAccountActualType(GlobalConst.PAYMENT_ACCOUNTACTUALTYPE_BANK);
            } else if(entity.getAccountType().equals(GlobalConst.PAYMENT_ACCOUNTTYPE_WALLET) && entity.getBankName().equals("EASYPAISA")){
                entity.setAccountActualType(GlobalConst.PAYMENT_ACCOUNTACTUALTYPE_EASYPAISA);
            } else if(entity.getAccountType().equals(GlobalConst.PAYMENT_ACCOUNTTYPE_WALLET) && entity.getBankName().equals("JAZZCASH")){
                entity.setAccountActualType(GlobalConst.PAYMENT_ACCOUNTACTUALTYPE_JAZZCASH);
            }
        });
        //
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    public JsonMessage del(@RequestBody ReqIdParam reqIdParam) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        AccountReceivingBank accountReceivingBankDB = accountReceivingBankService.selectByPrimaryKey(reqIdParam.getId());
        if(null == accountReceivingBankDB || principal.getId().longValue() != accountReceivingBankDB.getAccountId().longValue())
        {
            log.error("非法请求");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        accountReceivingBankService.remove(reqIdParam.getId());
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
