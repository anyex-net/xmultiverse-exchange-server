package com.anyex.apps.controller.spot;


import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.exchange.viabtc.api.ViabtcMarketApi;
import com.anyex.exchange.viabtc.req.ReqMarketDeals;
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
@RequestMapping("/spot/spotMarketDeals")
@Api(tags = "现货市场成交")
public class SpotMarketDealsController extends GenericController {

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:spotMarketDeals:data")
    @ApiOperation(value = "现货市场成交", httpMethod = "POST")
    public JsonMessage<JSONObject> data(@ModelAttribute ReqMarketDeals reqMarketDeals) throws BusinessException {
        JSONObject jsonObject = ViabtcMarketApi.marketDeals(reqMarketDeals);
        return new JsonMessage<>(CommonEnums.SUCCESS,jsonObject);
    }
}
