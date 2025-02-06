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

import com.anyex.apps.account.entity.AccountSignInInfo;
import com.anyex.apps.account.service.AccountSignInInfoService;

import com.anyex.apps.controller.account.req.ReqAccountSignInInfo;
import com.anyex.apps.controller.account.req.ReqAccountSignInInfoPagination;
import java.util.Date;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 账户签到信息 控制器
 * <p>File：AccountSignInInfoController.java </p>
 * <p>Title: AccountSignInInfoController </p>
 * <p>Description:AccountSignInInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/accountsignininfo")
@Api(tags = "账户签到信息")
public class AccountSignInInfoController extends GenericController
{
    @Autowired(required = false)
    private AccountSignInInfoService accountsignininfoService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountsignininfo:data")
    @ApiOperation(value = "根据ID取账户签到信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountsignininfoService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountsignininfo:operator")
    @ApiOperation(value = "保存账户签到信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqAccountSignInInfo info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AccountSignInInfo entity = new AccountSignInInfo();
            BeanUtils.copyProperties(info, entity);
            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                    accountsignininfoService.insert(entity);
            } else {
                    accountsignininfoService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountsignininfo:data")
    @ApiOperation(value = "查询账户签到信息", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountSignInInfo>> data(@ModelAttribute ReqAccountSignInInfoPagination pagin) throws BusinessException
    {
        AccountSignInInfo entity = new AccountSignInInfo();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AccountSignInInfo> result = accountsignininfoService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountsignininfo:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountsignininfoService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
