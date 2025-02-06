package com.anyex.apps.controller.asset.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "钱包资产分页请求对象")
public class ReqWalletAssetPagination extends Pagination
{
}