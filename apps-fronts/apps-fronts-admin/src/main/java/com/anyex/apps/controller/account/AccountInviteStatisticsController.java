package com.anyex.apps.controller.account;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.account.entity.AccountInviteStatistics;
import com.anyex.apps.account.service.AccountInviteStatisticsService;

import com.anyex.apps.controller.account.req.ReqAccountInviteStatistics;
import com.anyex.apps.controller.account.req.ReqAccountInviteStatisticsPagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 账户邀请统计 控制器
 * <p>File：AccountInviteStatisticsController.java </p>
 * <p>Title: AccountInviteStatisticsController </p>
 * <p>Description:AccountInviteStatisticsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/accountinvitestatistics")
@Api(tags = "账户邀请统计")
public class AccountInviteStatisticsController extends GenericController
{
    @Autowired(required = false)
    private AccountInviteStatisticsService accountinvitestatisticsService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountinvitestatistics:data")
    @ApiOperation(value = "根据ID取账户邀请统计", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountinvitestatisticsService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountinvitestatistics:operator")
    @ApiOperation(value = "保存账户邀请统计", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqAccountInviteStatistics info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AccountInviteStatistics entity = new AccountInviteStatistics();
            BeanUtils.copyProperties(info, entity);
            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                    accountinvitestatisticsService.insert(entity);
            } else {
                    accountinvitestatisticsService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountinvitestatistics:data")
    @ApiOperation(value = "查询账户邀请统计", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountInviteStatistics>> data(@ModelAttribute ReqAccountInviteStatisticsPagination pagin) throws BusinessException
    {
        AccountInviteStatistics entity = new AccountInviteStatistics();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AccountInviteStatistics> result = accountinvitestatisticsService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountinvitestatistics:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountinvitestatisticsService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
