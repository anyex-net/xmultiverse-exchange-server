package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.UserLog;
import com.anyex.apps.user.service.UserLogService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户日志控制器 Introduce
 * <p>File：UserLogsController.java</p>
 * <p>Title: UserLogsController</p>
 * <p>Description: UserLogsController</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.USER)
@Api(tags = "用户日志")
public class UserLogsController extends GenericController
{
//    @Autowired(required = false)
//    AccountLogNoSql accountLogNoSql;

    @Autowired(required = false)
    UserLogService userLogService;

    /**
     * 登陆日志
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/userLog/login", method = RequestMethod.GET)
    @ApiOperation(value = "登录日志", httpMethod = "GET")
    public JsonMessage<List<UserLog>> loginLog() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        UserLog userLogSearch = new UserLog();
        userLogSearch.setUserId(principal.getId());
        userLogSearch.setOpType("login");
        List<UserLog> result = userLogService.findTopTenUserLog(userLogSearch);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    /**
     * 操作日志
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/userLog/setting", method = RequestMethod.GET)
    @ApiOperation(value = "操作日志", httpMethod = "GET")
    public JsonMessage<List<UserLog>> settingLog() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        UserLog userLogSearch = new UserLog();
        userLogSearch.setUserId(principal.getId());
        userLogSearch.setOpType("setting");
        List<UserLog> result = userLogService.findTopTenUserLog(userLogSearch);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
