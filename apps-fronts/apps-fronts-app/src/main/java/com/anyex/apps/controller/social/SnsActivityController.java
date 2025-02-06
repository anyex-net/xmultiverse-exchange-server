
package com.anyex.apps.controller.social;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.common.req.ReqIdParam;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.entity.SnsActivity;
import com.anyex.apps.social.service.SnsActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 社交活动 控制器
 * <p>File：SnsActivityController.java </p>
 * <p>Title: SnsActivityController </p>
 * <p>Description:SnsActivityController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/social/snsactivity")
@Api(tags = "社交活动")
public class SnsActivityController extends GenericController
{
    @Autowired(required = false)
    private SnsActivityService snsactivityService;


    @PostMapping(value = "/data")
    @ApiOperation(value = "查询社交活动", httpMethod = "POST")
    public JsonMessage<PaginateResult<SnsActivity>> data(@ModelAttribute Pagination pagin) throws BusinessException
    {
        SnsActivity entity = new SnsActivity();
        BeanUtils.copyProperties(pagin, entity);
        entity.setStatus(1);
        PaginateResult<SnsActivity> result = snsactivityService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "查询明细", httpMethod = "POST")
    public JsonMessage<SnsActivity> detail(@Validated @ModelAttribute ReqIdParam req) throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, snsactivityService.selectByPrimaryKey(req.getId()));
    }

}
