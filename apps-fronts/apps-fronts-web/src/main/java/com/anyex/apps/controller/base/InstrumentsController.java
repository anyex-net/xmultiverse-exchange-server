/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.base.entity.Instruments;
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
import org.springframework.beans.BeanUtils;
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

    /**
     * 现货专区
     * ETH链的ETH/USDT
     * BSC链的BNB/USDT
     * SOL链的SOL/USDT
     *
     * MEME专区
     * DOGE/USDT
     *
     * RWA专区
     * ONDO/USDT
     *
     * AI专区
     * AIXBT/USDT
     *
     * GAME专区
     * AXS/USDT
     *
     * @return
     * @throws BusinessException
     */
    @PostMapping(value = "/tags")
    @ApiOperation(value = "查询交易产品分区板块标签", httpMethod = "POST")
    public JsonMessage<List<InstrumentsTagsModel>> tags() throws BusinessException
    {
        List<InstrumentsTagsModel> listInstrumentsTagsModel = new ArrayList<InstrumentsTagsModel>();
        Instruments instrumentsSearch = new Instruments();
        instrumentsSearch.setInstType("SPOT");
        List<Instruments> listInstruments = instrumentsService.findList(instrumentsSearch);
        if(null != listInstruments && listInstruments.size() > 0)
        {
            for(int i=0; i<listInstruments.size(); i++)
            {
                Instruments instruments = listInstruments.get(i);
                log.info("instruments tags:{}", instruments.getTags());
                //
                boolean exists = listInstrumentsTagsModel.stream()
                        .anyMatch(obj -> obj.getTags().equals(instruments.getTags()));
                if(!exists){
                    InstrumentsTagsModel instrumentsTagsModel = new InstrumentsTagsModel();
                    instrumentsTagsModel.setTags(instruments.getTags());
                    instrumentsTagsModel.setTagsName(instruments.getTags()+"专区");
                    log.info("instrumentsTagsModel:{}", instrumentsTagsModel);
                    //
                    listInstrumentsTagsModel.add(instrumentsTagsModel);
                } else {
                    log.error("instruments tags:{} 已经存在 直接忽略",instruments.getTags());
                }
                //
            }
        }
        //
        log.info("listInstrumentsTagsModel:{}", listInstrumentsTagsModel);
        //
        return getJsonMessage(CommonEnums.SUCCESS, listInstrumentsTagsModel);
    }

    @PostMapping(value = "/tagsInstruments")
    @ApiOperation(value = "查询对应标签平台交易产品列表", httpMethod = "POST")
    public JsonMessage<List<JSONObject>> tagsInstruments(@Validated @RequestBody ReqInstruments reqInstruments) throws BusinessException
    {
        Instruments instruments = new Instruments();
        instruments.setInstType("SPOT");
        instruments.setTags(reqInstruments.getTags());
        log.info("tagsInstruments instruments:{}", instruments);
        List<Instruments> listInstruments = instrumentsService.findList(instruments);
        //

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
                boolean exists = listInstruments.stream()
                        .anyMatch(obj -> obj.getInstId().equals(itemJsonObject.getString("tradepair")));
                if(exists){
                    log.info("exists:{}", exists);
                    listJSONObject.add(itemJsonObject);
                } else {
                    log.info("exists:{}", exists);
                }
                //
            }
        }
        //
        return getJsonMessage(CommonEnums.SUCCESS, listJSONObject);
    }
}
