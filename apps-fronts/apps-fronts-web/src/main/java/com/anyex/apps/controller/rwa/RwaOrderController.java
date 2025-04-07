package com.anyex.apps.controller.rwa;


import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.rwa.req.ReqRwaInstSpvProductPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import com.anyex.apps.rwa.service.RwaInstSpvProductPurchaseService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/rwa/rwaOrder")
@Api(tags = "RWA订单")
public class RwaOrderController extends GenericController
{
    @Autowired(required = false)
    private RwaInstSpvProductPurchaseService rwaInstSpvProductPurchaseService;


    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA订单列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaInstSpvProductPurchase>> data(@Validated @RequestBody ReqRwaInstSpvProductPagination pagin) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        RwaInstSpvProductPurchase productPurchase = new RwaInstSpvProductPurchase();
        BeanUtils.copyProperties(pagin, productPurchase);
        productPurchase.setUserId(principal.getId());
        PaginateResult<RwaInstSpvProductPurchase> result = rwaInstSpvProductPurchaseService.search(pagin, productPurchase);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
