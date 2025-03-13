package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志控制器 Introduce
 * <p>File：LogsController.java</p>
 * <p>Title: LogsController</p>
 * <p>Description: LogsController</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.USER)
@Api(tags = "日志信息")
public class LogsController extends GenericController
{
//    @Autowired(required = false)
//    AccountLogNoSql accountLogNoSql;
    
    /**
     * 登陆日志
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/logs/login", method = RequestMethod.GET)
    @ApiOperation(value = "登录日志", httpMethod = "GET")
    public JsonMessage loginLog() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        List<AccountLog> result = accountLogNoSql.findLastTenLoginLogs(principal.getId());
//        return this.getJsonMessage(CommonEnums.SUCCESS, result);
        return this.getJsonMessage(CommonEnums.SUCCESS, null);
    }
    
    /**
     * 操作日志
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/logs/setting", method = RequestMethod.GET)
    @ApiOperation(value = "操作日志", httpMethod = "GET")
    public JsonMessage settingLog() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        List<AccountLog> result = accountLogNoSql.findLastTenSettingLogs(principal.getId());
//        return this.getJsonMessage(CommonEnums.SUCCESS, result);
        return this.getJsonMessage(CommonEnums.SUCCESS, null);
    }
}
