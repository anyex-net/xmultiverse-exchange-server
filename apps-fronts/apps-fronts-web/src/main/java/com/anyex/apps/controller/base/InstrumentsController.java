/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.base.model.InstrumentsTagsModel;
import com.anyex.apps.base.service.InstrumentsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.base.req.ReqInstruments;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.exchange.viabtc.api.ViabtcMarketApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 平台交易产品 控制器
 * <p>File：InstrumentsController.java </p>
 * <p>Title: InstrumentsController </p>
 * <p>Description:InstrumentsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/base/instruments")
@Api(tags = "平台交易产品")
public class InstrumentsController extends GenericController
{
    @Autowired(required = false)
    private InstrumentsService instrumentsService;

    @PostMapping(value = "/tags")
    @ApiOperation(value = "查询交易产品分区板块标签", httpMethod = "POST")
    public JsonMessage<List<InstrumentsTagsModel>> tags() throws BusinessException
    {
        List<InstrumentsTagsModel> list = new ArrayList<InstrumentsTagsModel>();
        InstrumentsTagsModel instrumentsTagsModel = new InstrumentsTagsModel();
        //
        instrumentsTagsModel.setTags("USD");
        instrumentsTagsModel.setTagsName("USD板块");
        list.add(instrumentsTagsModel);
        //
        instrumentsTagsModel.setTags("USDT");
        instrumentsTagsModel.setTagsName("USDT板块");
        list.add(instrumentsTagsModel);
        //
        return getJsonMessage(CommonEnums.SUCCESS, list);
    }

    @PostMapping(value = "/tagsInstruments")
    @ApiOperation(value = "查询对应标签平台交易产品列表", httpMethod = "POST")
    public JsonMessage<List<JSONObject>> tagsInstruments(@Validated @RequestBody ReqInstruments reqInstruments) throws BusinessException
    {
//        Instruments instruments = new Instruments();
//        BeanUtils.copyProperties(reqInstruments, instruments);
//        log.info("tagsInstruments instruments:{}", instruments);
//        List<Instruments> result = instrumentsService.findList(instruments);
//        return getJsonMessage(CommonEnums.SUCCESS, result);

        List<JSONObject> listJSONObject = new ArrayList<JSONObject>();
        //
        JSONObject marketListJsonObject = ViabtcMarketApi.marketList();
        log.info("marketList marketListJsonObject:{}", marketListJsonObject);
        if(null != marketListJsonObject && marketListJsonObject.size() > 0)
        {
            JSONArray marketListJsonObjectArray = marketListJsonObject.getJSONArray("result");
            //
            for(int i=0; i<marketListJsonObjectArray.size(); i++)
            {
                //
                JSONObject itemJsonObject = marketListJsonObjectArray.getJSONObject(i);
                itemJsonObject.put("tradepair", itemJsonObject.getString("stock") + "/" + itemJsonObject.getString("money"));
                log.info("marketList itemJsonObject:{}", itemJsonObject);
                //
                listJSONObject.add(itemJsonObject);
            }
        }
        //
        return getJsonMessage(CommonEnums.SUCCESS, listJSONObject);
    }
}
