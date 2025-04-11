/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 现货市场与交易SpotWebSocket 控制器
 * <p>File：SpotWebSocketController.java </p>
 * <p>Title: SpotWebSocketController </p>
 * <p>Description:SpotWebSocketController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/websocket")
@Api(tags = "现货市场与交易SpotWebSocket")
public class SpotWebSocketController extends GenericController
{
    @ResponseBody
    @PostMapping(value = "/wsaddr")
    @ApiOperation(value = "WS地址(ws://114.55.147.64:8090)", httpMethod = "POST")
    public JsonMessage<JSONObject> wsaddr() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @ResponseBody
    @PostMapping(value = "/apidoc/http")
    @ApiOperation(value = "文档https://github.com/viabtc/viabtc_exchange_server/wiki/HTTP-Protocol", httpMethod = "POST")
    public JsonMessage<JSONObject> apidochttp() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @ResponseBody
    @PostMapping(value = "/apidoc/ws")
    @ApiOperation(value = "文档https://github.com/viabtc/viabtc_exchange_server/wiki/WebSocket-Protocol", httpMethod = "POST")
    public JsonMessage<JSONObject> apidocws() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
