package com.anyex.apps.controller.user;

import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.consts.UserConsts;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.enums.SecurityPolicyEnums;
import com.anyex.apps.user.enums.TradePolicyEnums;
import com.anyex.apps.user.enums.UserEnums;
import com.anyex.apps.user.model.PolicyModel;
import com.anyex.apps.user.service.UserPolicyService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.EnumUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 验证策略管理控制器
 * <p>File：SecurityController.java</p>
 * <p>Title: SecurityController</p>
 * <p>Description: SecurityController</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.USER)
@Api(tags = "验证策略管理")
public class SecurityController extends GenericController
{
    @Autowired(required = false)
    UserService userService;
    
//    @Autowired(required = false)
//    AccountLogNoSql      accountLogNoSql;
    
    @Autowired(required = false)
    UserPolicyService userPolicyService;
    
    /**
     * 获取交易验证策略
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/policy/trade", method = RequestMethod.GET)
    @ApiOperation(value = "获取交易验证策略", httpMethod = "GET")
    public JsonMessage getTradePolicys() throws BusinessException
    {
        List<TradePolicyEnums> EnumConstants = EnumUtils.toList(TradePolicyEnums.class);
        Map<String, Object> result = Maps.newHashMap();
        for (TradePolicyEnums policyEnums : EnumConstants)
        {
            result.put(String.valueOf(policyEnums.code), policyEnums.getMessage());
            // result.put(String.valueOf(policyEnums.code), getMessage(policyEnums.getMessage()));
        }
        return super.getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    /**
    * 获取安全验证策略
    * @return {@link JsonMessage}
    * @throws BusinessException
    */
    @ResponseBody
    @RequestMapping(value = "/policy/security", method = RequestMethod.GET)
    @ApiOperation(value = "获取安全验证策略", httpMethod = "GET")
    public JsonMessage getSecurityPolicys() throws BusinessException
    {
        List<SecurityPolicyEnums> EnumConstants = EnumUtils.toList(SecurityPolicyEnums.class);
        Map<String, Object> result = Maps.newHashMap();
        for (SecurityPolicyEnums policyEnums : EnumConstants)
        {
            result.put(String.valueOf(policyEnums.code), policyEnums.getMessage());
            // result.put(String.valueOf(policyEnums.code), getMessage(policyEnums.getMessage()));
        }
        return super.getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    /**
     * 设置交易验证策略
     * @param policy
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/policy/trade/save", method = RequestMethod.POST)
    @ApiImplicitParam(name = "level", value = "验证策略", required = true, paramType = "form")
    @ApiOperation(value = "设置交易验证策略", httpMethod = "POST", consumes = "application/x-www-form-urlencoded")
    public JsonMessage saveTradePolicy(Integer level, @ModelAttribute PolicyModel policy) throws BusinessException
    {
        if (level == null) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (UserConsts.TRADE_POLICY_TWOHOUR == level || UserConsts.TRADE_POLICY_EVERYTIME == level)
        {// 如果启动资金密码后需要先判断用户是否已设置资金密码
            if (StringUtils.isBlank(userDB.getTradePwd())) throw new BusinessException(CommonEnums.ERROR_WALLET_VALID_NOEXIST);
        }
        userPolicyService.validSecurityPolicy(userDB, policy);
        userDB.setTradePolicy(level);
        userService.updateByPrimaryKeySelective(userDB);
        return super.getJsonMessage(CommonEnums.SUCCESS);
    }
    
    /**
     * 设置安全验证验证策略
     * @param policy
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/policy/security/save", method = RequestMethod.POST)
    @ApiImplicitParam(name = "level", value = "验证策略", required = true, paramType = "form")
    @ApiOperation(value = "设置安全验证策略", httpMethod = "POST", consumes = "application/x-www-form-urlencoded")
    public JsonMessage saveSecurityPolicy(Integer level, @ModelAttribute PolicyModel policy) throws BusinessException
    {
        if (level == null) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (UserConsts.SECURITY_POLICY_NEEDGA == level)
        {// 判断是否已绑定GA
            if (StringUtils.isBlank(userDB.getGaAuthKey())) throw new BusinessException(CommonEnums.ERROR_GA_NOT_BIND);
        }
        if (UserConsts.SECURITY_POLICY_NEEDSMS == level)
        {// 判断是否已绑定SMS
            if (StringUtils.isBlank(userDB.getMobileNo())) throw new BusinessException(UserEnums.USER_PHONE_NOTBIND);
        }
        if (UserConsts.SECURITY_POLICY_NEEDGAANDSMS == level)
        {// 判断是否已绑定SMS和GA
            if (StringUtils.isBlank(userDB.getGaAuthKey())) throw new BusinessException(CommonEnums.ERROR_GA_NOT_BIND);
            if (StringUtils.isBlank(userDB.getMobileNo())) throw new BusinessException(UserEnums.USER_PHONE_NOTBIND);
        }
        userPolicyService.validSecurityPolicy(userDB, policy);
        userDB.setSecurityPolicy(level);
        userService.updateByPrimaryKey(userDB);
        return super.getJsonMessage(CommonEnums.SUCCESS);
    }
}
