package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
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
     * 获取用户信息
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public JsonMessage<User> getUserInfo() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User user = userService.selectByPrimaryKey(OnLineUserUtils.getId());
        //
        return getJsonMessage(CommonEnums.SUCCESS, user);
    }
}
