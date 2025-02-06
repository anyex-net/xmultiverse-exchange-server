
package com.anyex.apps.controller.openim;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.openim.entity.AccountFavorite;
import com.anyex.apps.openim.service.AccountFavoriteService;

import com.anyex.apps.controller.openim.req.ReqAccountFavorite;
import com.anyex.apps.controller.openim.req.ReqAccountFavoritePagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 账户收藏 控制器
 * <p>File：AccountFavoriteController.java </p>
 * <p>Title: AccountFavoriteController </p>
 * <p>Description:AccountFavoriteController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/openim/account/accountfavorite")
@Api(tags = "账户收藏")
public class AccountFavoriteController extends GenericController
{
    @Autowired(required = false)
    private AccountFavoriteService accountfavoriteService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountfavorite:data")
    @ApiOperation(value = "根据ID取账户收藏", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountfavoriteService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountfavorite:operator")
    @ApiOperation(value = "保存账户收藏", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqAccountFavorite info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AccountFavorite entity = new AccountFavorite();
            BeanUtils.copyProperties(info, entity);
            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                    accountfavoriteService.insert(entity);
            } else {
                    accountfavoriteService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountfavorite:data")
    @ApiOperation(value = "查询账户收藏", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqAccountFavoritePagination pagin) throws BusinessException
    {
        AccountFavorite entity = new AccountFavorite();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AccountFavorite> result = accountfavoriteService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountfavorite:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountfavoriteService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
