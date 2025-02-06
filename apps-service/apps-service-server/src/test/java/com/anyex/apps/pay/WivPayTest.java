package com.anyex.apps.pay;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.wivpay.api.WivPayApi;
import com.anyex.wivpay.config.WivPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WivPayTest extends BaseServiceImplTest
{
    @Autowired
    WivPayConfig wivPayConfig;

    @Test
    public void testWivPayPayIn() throws BusinessException {
        String cnic = "3740526887492";
        String mobile = "3230505914";
        Double amount = 15d;
        Long accountId = 1L;
        String walletType = "jazzcash1";
        String trxNo = SerialnoUtils.getOrderNum();
        String channel = (StringUtils.equalsIgnoreCase(walletType,"jazzcash")?"LuckyboxKingJC":"LuckyboxKingEP");
        log.info("充值通道选择:{}={}", walletType, channel);

        // 调用接口拉起支付链接
        JSONObject object = WivPayApi.payIn(wivPayConfig, channel, trxNo, cnic, "", mobile, "", amount,accountId);
        log.info("调用充值获取支付链接应答：response:{}", object.toJSONString());
        if (200 == object.getInteger("status")) {
            // 调取支付链接成功
//            object = object.getJSONObject("result");
//            log.info("调用充值获取支付链接成功:{}", object.toJSONString());
//            String url = object.getString("payUrl");
//            return new AssetDepositApplyResultModel(depositId, trxNo, url, trxChannel, amount, "", mobile, "", "", cnic);
        } else {
            // 调取支付链接失败
//            trans.setTrxStatus(GlobalConst.STATUS_FAILED);
//            walletAssetTransactionsService.updateByPrimaryKey(trans);
//            log.error("调用充值获取支付链接失败:{}", object.toJSONString());
//            throw new BusinessException(CommonEnums.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 代收查询
     */
    @Test
    public void testWivPayPayInQuery() throws BusinessException {
        JSONObject object = WivPayApi.payInQuery(wivPayConfig, "20240506133305487880");
        log.info("代收业务结果查询结果：responce:{}", object.toJSONString());
        if (200 == object.getInteger("status")) {
            // 调取支付链接成功
            object = object.getJSONObject("result");
            Integer state = object.getInteger("orderStatus");
            if (state == null) {
                log.error("代收业务回调失败:{}", object.toJSONString());
            }
            log.info("代收业务结果查询结果业务处理成功：responce:{}", object.toJSONString());
        } else {
            log.error("代收业务结果查询结果业务未处理：responce:{}", object.toJSONString());
        }
    }
}
