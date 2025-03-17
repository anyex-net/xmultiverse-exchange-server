package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.user.req.ReqUserModifyLang;
import com.anyex.apps.controller.user.req.ReqUserModifyLocalCurrency;
import com.anyex.apps.controller.user.req.ReqUserModifyStableCoinPreference;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户偏好设置 控制器
 * <p>File：UserPreferenceController.java </p>
 * <p>Title: UserPreferenceController </p>
 * <p>Description:UserPreferenceController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping(GlobalConst.USER)
@Api(tags = "用户偏好设置")
public class UserPreferenceController extends GenericController
{
    @Autowired(required = false)
    UserService userService;

    /**
     * 修改语言
     * @param reqUserModifyLang
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "修改语言", httpMethod = "POST")
    @RequestMapping(value = "/preference/modifyLang", method = RequestMethod.POST)
    public JsonMessage modifyLang(@Validated @RequestBody ReqUserModifyLang reqUserModifyLang) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        User userDB = userService.selectByPrimaryKey(principal.getId());
        userDB.setLang(reqUserModifyLang.getLang());
        log.info("modifyLang user:{}", userDB);
        userService.updateByPrimaryKeySelective(userDB);
        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 修改本地货币
     * @param reqUserModifyLocalCurrency
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "修改本地货币", httpMethod = "POST")
    @RequestMapping(value = "/preference/modifyLocalCurrency", method = RequestMethod.POST)
    public JsonMessage modifyLocalCurrency(@Validated @RequestBody ReqUserModifyLocalCurrency reqUserModifyLocalCurrency) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        User userDB = userService.selectByPrimaryKey(principal.getId());
        userDB.setLocalCurrency(reqUserModifyLocalCurrency.getLocalCurrency());
        log.info("modifyLocalCurrency user:{}", userDB);
        userService.updateByPrimaryKeySelective(userDB);
        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 修改稳定币偏好
     * @param reqUserModifyStableCoinPreference
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "修改稳定币偏好", httpMethod = "POST")
    @RequestMapping(value = "/preference/modifyStableCoinPreference", method = RequestMethod.POST)
    public JsonMessage modifyStableCoinPreference(@Validated @RequestBody ReqUserModifyStableCoinPreference reqUserModifyStableCoinPreference) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        User userDB = userService.selectByPrimaryKey(principal.getId());
        userDB.setStableCoinPreference(reqUserModifyStableCoinPreference.getStableCoinPreference());
        log.info("modifyStableCoinPreference user:{}", userDB);
        userService.updateByPrimaryKeySelective(userDB);
        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }
}
