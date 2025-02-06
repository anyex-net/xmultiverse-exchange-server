package com.anyex.apps.operation.model;

import lombok.Data;

/**
 * 账户资金流水监控结果对象
 */

@Data
public class MonitorWalletAssetFlowsResultModel {

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 资金流水咬合错误数量
     */
    private Integer errCnt;

    /**
     * 资金流水咬合错误id组 例如[1,2],[3,4].....
     */
    private String errMsg;
}
