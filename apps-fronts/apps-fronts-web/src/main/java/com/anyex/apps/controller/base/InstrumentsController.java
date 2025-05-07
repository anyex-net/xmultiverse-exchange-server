/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.anyex.apps.base.entity.Instruments;
import com.anyex.apps.base.model.InstrumentsTagsModel;
import com.anyex.apps.base.service.InstrumentsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.base.req.ReqInstruments;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
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

    @PostMapping(value = "/tags")
    @ApiOperation(value = "查询交易产品分区板块标签", httpMethod = "POST")
    public JsonMessage<List<InstrumentsTagsModel>> tags() throws BusinessException
    {
        List<InstrumentsTagsModel> list = new ArrayList<InstrumentsTagsModel>();
        InstrumentsTagsModel instrumentsTagsModel = new InstrumentsTagsModel();
        //
        instrumentsTagsModel.setPartition("USD");
        instrumentsTagsModel.setPartitionName("USD板块");
        list.add(instrumentsTagsModel);
        //
        instrumentsTagsModel.setPartition("USDT");
        instrumentsTagsModel.setPartitionName("USDT板块");
        list.add(instrumentsTagsModel);
        //
        return getJsonMessage(CommonEnums.SUCCESS, list);
    }

    @PostMapping(value = "/tagsInstruments")
    @ApiOperation(value = "查询对应标签平台交易产品列表", httpMethod = "POST")
    public JsonMessage<List<Instruments>> tagsInstruments(@Validated @RequestBody ReqInstruments reqInstruments) throws BusinessException
    {
        Instruments instruments = new Instruments();
        BeanUtils.copyProperties(reqInstruments, instruments);
        log.info("tagsInstruments instruments:{}", instruments);
        List<Instruments> result = instrumentsService.findList(instruments);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
