package com.anyex.apps.controller.spot;


import com.alibaba.fastjson.JSONObject;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceQuery;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.exchange.viabtc.api.ViabtcAssetApi;
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
@RequestMapping("/spot/spotBalances")
@Api(tags = "现货账户余额")
public class SpotBalancesController {

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:spotBalances:data")
    @ApiOperation(value = "现货账户余额", httpMethod = "POST")
    public JsonMessage<JSONObject> data(@ModelAttribute ReqAssetBalanceQuery reqAssetBalanceQuery) throws BusinessException {
        JSONObject jsonObject = ViabtcAssetApi.balanceQuery(reqAssetBalanceQuery);
        return new JsonMessage<>(CommonEnums.SUCCESS,jsonObject);
    }
}
