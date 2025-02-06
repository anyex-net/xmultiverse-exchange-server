package com.anyex.apps.controller.business.luckybox.shop.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "店铺分页请求对象")
public class ReqShopPagination extends Pagination
{
    @ApiModelProperty(value = "店铺ID")
    private Long id;

    /**城市*/
    @ApiModelProperty(value = "城市")
    private String city;

    /**地址*/
    @ApiModelProperty(value = "地址")
    private String address;

    /**电话*/
    @ApiModelProperty(value = "电话")
    private String tel;
}