package com.anyex.apps.asset;

import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class AssetUtil
{
    /**
     * 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
     * @param walletAssetBalance
     * @param walletAssetFlowsBalance
     * @return
     */
    public static Boolean checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(BigDecimal walletAssetBalance, BigDecimal walletAssetFlowsBalance)
    {
        if(walletAssetBalance.compareTo(walletAssetFlowsBalance) == 0){
            return true;
        } else {
            log.error("严重错误: Balance of the asset is not eq the balance after the occurrence of the asset flow, walletAssetBalance:{}, walletAssetFlowsBalance:{}",
                    walletAssetBalance, walletAssetFlowsBalance);
            throw new BusinessException(CommonEnums.RISK_CHECK_ASSETBALANCE_NOTEQ_ASSETFLOWAFTERBALANCE);
        }
    }
}
