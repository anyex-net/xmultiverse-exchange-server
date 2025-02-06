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

import com.anyex.apps.account.entity.AccountInviteRewardsDetail;
import com.anyex.apps.account.service.AccountInviteRewardsDetailService;

import com.anyex.apps.controller.account.req.ReqAccountInviteRewardsDetail;
import com.anyex.apps.controller.account.req.ReqAccountInviteRewardsDetailPagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 账户邀请奖励 控制器
 * <p>File：AccountInviteRewardsDetailController.java </p>
 * <p>Title: AccountInviteRewardsDetailController </p>
 * <p>Description:AccountInviteRewardsDetailController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/accountinviterewardsdetail")
@Api(tags = "账户邀请奖励")
public class AccountInviteRewardsDetailController extends GenericController
{
    @Autowired(required = false)
    private AccountInviteRewardsDetailService accountinviterewardsdetailService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountinviterewardsdetail:data")
    @ApiOperation(value = "根据ID取账户邀请奖励", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountinviterewardsdetailService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountinviterewardsdetail:operator")
    @ApiOperation(value = "保存账户邀请奖励", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqAccountInviteRewardsDetail info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AccountInviteRewardsDetail entity = new AccountInviteRewardsDetail();
            BeanUtils.copyProperties(info, entity);
            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                    accountinviterewardsdetailService.insert(entity);
            } else {
                    accountinviterewardsdetailService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountinviterewardsdetail:data")
    @ApiOperation(value = "查询账户邀请奖励", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountInviteRewardsDetail>> data(@ModelAttribute ReqAccountInviteRewardsDetailPagination pagin) throws BusinessException
    {
        AccountInviteRewardsDetail entity = new AccountInviteRewardsDetail();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AccountInviteRewardsDetail> result = accountinviterewardsdetailService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountinviterewardsdetail:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountinviterewardsdetailService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
