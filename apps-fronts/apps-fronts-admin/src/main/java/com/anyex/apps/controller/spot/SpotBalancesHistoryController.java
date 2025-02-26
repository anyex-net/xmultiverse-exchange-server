package com.anyex.apps.controller.spot;


import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.exchange.viabtc.api.ViabtcAssetApi;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spot/spotBalancesHistory")
@Api(tags = "现货账户流水")
public class SpotBalancesHistoryController extends GenericController {

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:spotBalancesHistory:data")
    @ApiOperation(value = "现货账户流水", httpMethod = "POST")
    public JsonMessage<JSONObject> data(@ModelAttribute ReqAssetBalanceHistory reqAssetBalanceHistory) throws BusinessException {
        JSONObject jsonObject = ViabtcAssetApi.balanceHistory(reqAssetBalanceHistory);
        return new JsonMessage<>(CommonEnums.SUCCESS,jsonObject);
    }
}
