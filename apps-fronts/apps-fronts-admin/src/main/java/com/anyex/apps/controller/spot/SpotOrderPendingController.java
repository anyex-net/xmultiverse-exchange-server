package com.anyex.apps.controller.spot;


import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.exchange.viabtc.api.ViabtcTradeApi;
import com.anyex.exchange.viabtc.req.ReqTradeOrderPending;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spot/spotOrderPending")
@Api(tags = "现货待处理订单")
public class SpotOrderPendingController extends GenericController {

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:spotOrderPending:data")
    @ApiOperation(value = "现货待处理订单", httpMethod = "POST")
    public JsonMessage<JSONObject> data(@ModelAttribute ReqTradeOrderPending reqTradeOrderPending) throws BusinessException {
        JSONObject jsonObject = ViabtcTradeApi.tradeOrderPending(reqTradeOrderPending);
        return new JsonMessage<>(CommonEnums.SUCCESS,jsonObject);
    }
}
