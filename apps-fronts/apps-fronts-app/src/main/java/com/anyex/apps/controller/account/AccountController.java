package com.anyex.apps.controller.account;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.controller.account.req.ReqAccountLngLatUpdate;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.account.req.ReqAccountUpdate;
import com.anyex.apps.controller.account.resp.RespAccount;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.EncryptUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * 帐户信息控制器
 * <p>File: AccountController.java </p>
 * <p>Title: AccountController </p>
 * <p>Description: AccountController </p>
 * <p>Copyright: Copyright (c) 2019-05-21</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.ACCOUNT)
@Api(tags = "帐户信息")
public class AccountController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private WalletAssetService walletAssetService;

    @GetMapping(value = "/getAccountInfo")
    @ApiOperation(value = "获取账户信息", httpMethod = "GET")
    //@AccessLimit(limit = 1, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求10次
    public JsonMessage<RespAccount> getAccountInfo(HttpServletRequest request) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (null != account && !account.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        //
        RespAccount respAccount = new RespAccount();
        BeanUtils.copyProperties(account, respAccount);
        //
        WalletAsset walletAsset = new WalletAsset();
        walletAsset.setAccountId(principal.getId());
        //
        List<WalletAsset> listWalletAsset = walletAssetService.findList(walletAsset);
        if(null != listWalletAsset && listWalletAsset.size() >= 1) {
            log.info("getAccountWalletAsset walletAsset:{}", listWalletAsset.get(0));
            respAccount.setBalance(listWalletAsset.get(0).getBalance());
            respAccount.setFrozenBal(listWalletAsset.get(0).getFrozenBal());
        } else {
            respAccount.setBalance(BigDecimal.ZERO);
            respAccount.setFrozenBal(BigDecimal.ZERO);
        }
        log.info("respAccount:{}", respAccount);
        return this.getJsonMessage(CommonEnums.SUCCESS, respAccount);
    }

    @PostMapping(value = "/updateHeadUrl")
    @ApiOperation(value = "更新账户信息账户头像", httpMethod = "POST")
    public JsonMessage updateHeadUrl(@RequestBody ReqAccountUpdate reqAccountUpdate) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        if (beanValidator(json, reqAccountUpdate))
        {
            Account accountDB = accountService.selectByPrimaryKey(principal.getId());
            if (null != accountDB && !accountDB.verifySignature())
            {// 校验数据
                throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
            }
            //
            accountDB.setHeadUrl(reqAccountUpdate.getHeadUrl());
            accountDB.setUpdateTime(System.currentTimeMillis());
            log.info("accountDB更新:{}", accountDB);
            accountService.updateByPrimaryKeySelective(accountDB);
        }
        //
        return json;
    }

    @PostMapping(value = "/updateAccountName")
    @ApiOperation(value = "更新账户信息账户昵称", httpMethod = "POST")
    public JsonMessage updateAccountName(@RequestBody ReqAccountUpdate reqAccountUpdate) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        if (beanValidator(json, reqAccountUpdate))
        {
            Account accountDB = accountService.selectByPrimaryKey(principal.getId());
            if (null != accountDB && !accountDB.verifySignature())
            {// 校验数据
                throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
            }
            //
            accountDB.setAccountName(reqAccountUpdate.getAccountName());
            accountDB.setUpdateTime(System.currentTimeMillis());
            log.info("accountDB更新:{}", accountDB);
            accountService.updateByPrimaryKeySelective(accountDB);
        }
        //
        return json;
    }

    @PostMapping(value = "/updateLoginPwd")
    @ApiOperation(value = "更新账户信息登录密码", httpMethod = "POST")
    public JsonMessage updateLoginPwd(@RequestBody ReqAccountUpdate reqAccountUpdate) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        if (beanValidator(json, reqAccountUpdate))
        {
            Account accountDB = accountService.selectByPrimaryKey(principal.getId());
            if (null != accountDB && !accountDB.verifySignature())
            {// 校验数据
                throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
            }
            //
            accountDB.setLoginPwd(EncryptUtils.entryptPassword(reqAccountUpdate.getLoginPwd()));
            accountDB.setUpdateTime(System.currentTimeMillis());
            log.info("accountDB更新:{}", accountDB);
            accountService.updateByPrimaryKeySelective(accountDB);
        }
        //
        return json;
    }

    @PostMapping(value = "/updateAccountInfo")
    @ApiOperation(value = "更新账户信息", httpMethod = "POST")
    public JsonMessage updateAccountInfo(@RequestBody ReqAccountUpdate reqAccountUpdate) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        if (beanValidator(json, reqAccountUpdate))
        {
            Account accountDB = accountService.selectByPrimaryKey(principal.getId());
            if (null != accountDB && !accountDB.verifySignature())
            {// 校验数据
                throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
            }
            //
            accountDB.setMobile(reqAccountUpdate.getMobile());
            accountDB.setRealName(reqAccountUpdate.getRealName());
            accountDB.setEmail(reqAccountUpdate.getEmail());
            accountDB.setCnic(reqAccountUpdate.getCnic());
            accountDB.setUpdateTime(System.currentTimeMillis());
            log.info("accountDB更新:{}", accountDB);
            accountService.updateByPrimaryKeySelective(accountDB);
        }
        //
        return json;
    }

    @PostMapping(value = "/updateAccountLngLat")
    @ApiOperation(value = "更新账户信息经纬度", httpMethod = "POST")
    public JsonMessage updateAccountLngLat(@RequestBody ReqAccountLngLatUpdate reqAccountLngLatUpdate) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        if (beanValidator(json, reqAccountLngLatUpdate))
        {
            Account accountDB = accountService.selectByPrimaryKey(principal.getId());
            if (null != accountDB && !accountDB.verifySignature())
            {// 校验数据
                throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
            }
            //
            accountDB.setLng(reqAccountLngLatUpdate.getLng());
            accountDB.setLat(reqAccountLngLatUpdate.getLat());
            accountDB.setUpdateTime(System.currentTimeMillis());
            log.info("accountDB更新:{}", accountDB);
            accountService.updateByPrimaryKeySelective(accountDB);
        }
        //
        return json;
    }
}
