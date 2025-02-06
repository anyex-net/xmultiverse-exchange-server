package com.anyex.apps.controller.openim;/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *//*

package com.anyex.apps.controller.openim;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.account.entity.Attribute;
import com.anyex.apps.account.service.AttributeService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.openim.admin.clientconfig.resp.GetClientConfigResp;
import com.anyex.apps.openim.chat.req.FindAppletReq;
import com.anyex.apps.openim.chat.req.GetClientConfigReq;
import com.anyex.apps.openim.chat.req.OpenIMCallbackReq;
import com.anyex.apps.openim.chat.req.UploadLogsReq;
import com.anyex.apps.openim.chat.resp.FindAppletResp;
import com.anyex.apps.openim.service.OpenImChatService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(GlobalConst.IM+"/old")
@Api(tags = "其他服务")
public class OpenImChatOtherController extends GenericController
{
    @Autowired(required = false)
    private OpenImChatService openImChatService;

    @Autowired(required = false)
    private AttributeService attributeService;

    @PostMapping(value = "/applet/find")
    @ApiOperation(value = "查找小程序", httpMethod = "POST")
    public OpenImResult<FindAppletResp> appletFind(@Validated @RequestBody FindAppletReq req) throws BusinessException
    {
        ValidatorUtils.validate(req);
       */
/* UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        *//*

        OpenImResult<FindAppletResp> openImResult = new OpenImResult<>();
        openImResult.setErrCode(9999);
        openImResult.setErrMsg("待升级");
        return openImResult;
    }

    @PostMapping(value = "/client_config/get")
    @ApiOperation(value = "获取客户端配置", httpMethod = "POST")
    public OpenImResult<GetClientConfigResp> getClientConfig(@Validated @RequestBody GetClientConfigReq req) throws BusinessException
    {
        ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Map<String, String> config = new HashMap<String, String>();
        config.put("allowSendMsgNotFriend",GlobalConst.IM_ALLOW_SEND_MSG_NOT_FRIEND);
        config.put("needInvitationCodeRegister",GlobalConst.IM_NEED_INVITATION_CODE_REGISTER);
        GetClientConfigResp r = new GetClientConfigResp();
        r.setConfig(config);

        OpenImResult<GetClientConfigResp> openImResult = new OpenImResult<GetClientConfigResp>();
        openImResult.success();
        openImResult.setData(r);
        return openImResult;
    }


    @PostMapping(value = "/callback/open_im")
    @ApiOperation(value = "回调", httpMethod = "POST")
    public Map<String,String> callbackOpenIm(@Validated @RequestBody OpenIMCallbackReq req) throws BusinessException
    {
        ValidatorUtils.validate(req);
        Map<String,String> result = new HashMap<>();
        JSONObject body = JSONObject.parseObject(req.getBody());
        Attribute attribute = attributeService.findByUserId(body.getString("toUserID"));
        switch (req.getCommand())
        {
            case "callbackBeforeAddFriendCommand":
                result.put("errCode", "0");
                result.put("errMsg", "Success");
                result.put("errDlt", "");
                result.put("actionCode", "0");
                result.put("nextCode", attribute.getAllowAddFriend()==1?"0":"1");
                break;
            default:
                break;
        }
        return result;
    }

    @PostMapping(value = "/logs/upload")
    @ApiOperation(value = "上传日志", httpMethod = "POST")
    public OpenImResult<String> uploadLogs(@Validated @RequestBody UploadLogsReq req) throws BusinessException
    {
        ValidatorUtils.validate(req);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        OpenImResult<String> openImResult = new OpenImResult<String>();
        openImResult.setErrCode(9999);
        openImResult.setErrMsg("待升级");
        log.error("用户上传日志：｛｝", JSONObject.toJSONString(req));
        return openImResult;
    }

}
*/
