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

import com.anyex.apps.account.entity.AccountSignInDetail;
import com.anyex.apps.account.service.AccountSignInDetailService;

import com.anyex.apps.controller.account.req.ReqAccountSignInDetail;
import com.anyex.apps.controller.account.req.ReqAccountSignInDetailPagination;
import java.util.Date;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 账户签到明细 控制器
 * <p>File：AccountSignInDetailController.java </p>
 * <p>Title: AccountSignInDetailController </p>
 * <p>Description:AccountSignInDetailController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/accountsignindetail")
@Api(tags = "账户签到明细")
public class AccountSignInDetailController extends GenericController
{
    @Autowired(required = false)
    private AccountSignInDetailService accountsignindetailService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountsignindetail:data")
    @ApiOperation(value = "根据ID取账户签到明细", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountsignindetailService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountsignindetail:operator")
    @ApiOperation(value = "保存账户签到明细", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqAccountSignInDetail info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AccountSignInDetail entity = new AccountSignInDetail();
            BeanUtils.copyProperties(info, entity);
            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                    accountsignindetailService.insert(entity);
            } else {
                    accountsignindetailService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountsignindetail:data")
    @ApiOperation(value = "查询账户签到明细", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountSignInDetail> > data(@ModelAttribute ReqAccountSignInDetailPagination pagin) throws BusinessException
    {
        AccountSignInDetail entity = new AccountSignInDetail();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AccountSignInDetail> result = accountsignindetailService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountsignindetail:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountsignindetailService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
