/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.DateConst;
import com.anyex.apps.controller.common.req.ReqIdParam;
import com.anyex.apps.controller.user.req.ReqUserApi;
import com.anyex.apps.controller.user.req.ReqUserApiPagination;
import com.anyex.apps.controller.user.resp.RespUserApi;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.UserApi;
import com.anyex.apps.user.service.UserApiService;
import com.anyex.apps.utils.CalendarUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * 用户API 控制器
 * <p>File：UserApiController.java </p>
 * <p>Title: UserApiController </p>
 * <p>Description:UserApiController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/userApi")
@Api(tags = "用户API")
public class UserApiController extends GenericController
{
    @Autowired(required = false)
    private UserApiService userApiService;

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询用户API", httpMethod = "POST")
    public JsonMessage<PaginateResult<UserApi>> data(@ModelAttribute ReqUserApiPagination pagin) throws BusinessException
    {
        UserApi entity = new UserApi();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserApi> result = userApiService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/createApi")
    @ApiOperation(value = "创建用户API", httpMethod = "POST")
    public JsonMessage createApi(@Validated @RequestBody ReqUserApi reqUserApi) throws BusinessException
    {
        //
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        UserApi userApi = new UserApi();
        BeanUtils.copyProperties(reqUserApi, userApi);
        //
        userApi.setUserId(principal.getId());
        // 生成随即签名字符串
        String key = generateSecretString(48, null);
        userApi.setPubKey(key);
        userApi.setPriKey(key);
        userApi.setApiKey(generateString(64, null));
        // 默认设置九十天过期
        userApi.setCloseTime(CalendarUtils.getCurrentLong() + 30 * 24 * DateConst.MILLIS_PER_HOUR);
        //
        userApi.setState(1); // 可用
        userApi.setCreateTime(System.currentTimeMillis());
        //
        log.info("插入 userApi:{}", userApi);
        userApiService.insert(userApi);
        //
        // 返回
        RespUserApi respUserApi = new RespUserApi();
        respUserApi.setApiKey(userApi.getApiKey());
        respUserApi.setPriKey(userApi.getPriKey());
        log.info("respUserApi:{}", respUserApi);
        //
        return this.getJsonMessage(CommonEnums.SUCCESS, respUserApi);
    }

    @PostMapping(value = "/delApi")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    public JsonMessage delApi(@Validated @RequestBody ReqIdParam reqIdParam) throws BusinessException
    {
        log.info("根据指定ID删除 reqIdParam:{}", reqIdParam);
        userApiService.remove(reqIdParam.getId());
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 创建签名米亚密钥
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateSecretString(int length, List<UserApi> userApiList) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
//        List<String> existApiKey = Lists.newArrayList();
//        userApiList.forEach(item ->
//                existApiKey.add(item.getPubKey())
//        );
//        if (existApiKey.contains(sb.toString())) generateSecretString(length, userApiList);
        return sb.toString();
    }

    /**
     * 创建随即API key
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length, List<UserApi> userApiList) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
//        List<String> existApiKey = Lists.newArrayList();
//        userApiList.forEach(item ->
//                existApiKey.add(item.getApiKey())
//        );
//        if (existApiKey.contains(sb.toString())) generateString(length, userApiList);
        return sb.toString();
    }
}
