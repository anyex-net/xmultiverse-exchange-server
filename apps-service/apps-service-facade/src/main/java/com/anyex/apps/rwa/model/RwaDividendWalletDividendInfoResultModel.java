package com.anyex.apps.rwa.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "链上分成对象")
public class RwaDividendWalletDividendInfoResultModel {


    @ApiModelProperty(value = "机构SPV产品ID", position = 3, required = true)
    private Long instSpvProductId;

    @ApiModelProperty(value = "机构SPV产品分红记录编号", position = 4, required = true)
    private String instSpvProductDividendNo;

    @ApiModelProperty(value = "链上钱包地址", position = 5, required = true)
    private String walletAddress;

    @ApiModelProperty(value = "链上持币数量", position = 6, required = true)
    private java.math.BigDecimal chainHoldAmount;

    @ApiModelProperty(value = "链上分成金额", position = 7, required = true)
    private java.math.BigDecimal chainDividendAmount;

    private List<RwaDividendSnapshotInfoResultModel> rwaDividendSnapshotInfo;
}
