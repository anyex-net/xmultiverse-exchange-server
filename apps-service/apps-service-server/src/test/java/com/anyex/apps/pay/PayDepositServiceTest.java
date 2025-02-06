package com.anyex.apps.pay;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.BaseServiceImplTest;

import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.model.AssetDepositApplyResultModel;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.wivpay.api.WivPayApi;
import com.anyex.wivpay.config.WivPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;


/**
 * 支付业务-充值(代收)  相关测试类
 * @author Playguy
 */
@Slf4j
public class PayDepositServiceTest extends BaseServiceImplTest {

    @Autowired
    WalletAssetTransactionsService walletAssetTransactionsService;

    @Autowired
    AccountReceivingBankService bankService;

    @Autowired
    WalletAssetService walletAssetService;

    @Autowired
    WivPayConfig config;

    private Long bankId = 7788L;
    private Long accountId = 7788L;


    @Before
    public void pre()
    {

    }

    public AccountReceivingBank bankinfo()
    {
        AccountReceivingBank bank = bankService.selectByPrimaryKey(bankId);
        System.out.println("bankinfo "+ JSON.toJSONString(bank));
        return bank;
    }

    public WalletAsset wallet()
    {
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
        if(null == asset)
        {
            asset = new WalletAsset();
            asset.setAccountId(accountId);
            asset.setCurrency(GlobalConst.CURRENCY_PKR);
            asset.setBalance(BigDecimal.ZERO);
            asset.setFrozenBal(BigDecimal.ZERO);
            asset.setUpdateTime(System.currentTimeMillis());
            asset.setRemark("7788");
            asset.setId(accountId);
            walletAssetService.insert(asset);
        }
        System.out.println("asset "+ JSON.toJSONString(asset));
        return asset;
    }

    @Test
    public void depositeGetPayUrlTest()
    {
        System.out.println(config);
        AssetDepositApplyResultModel model = walletAssetTransactionsService.depositApply(
                GlobalConst.PAYMENT_CHANNEL_WIVPAY,
                "BANK",
                "CNIC12332232",
                "ZHANGSAN",
                "1388888888",
                "1@1.COM",
                1d,
                accountId);
        System.out.println("AssetDepositApplyResultModel "+ JSON.toJSONString(model));
    }

    @Test
    public void depositeNotifyTest()
    {
        String trxNo = "202403181616045";
        String status = "1";
        String model = walletAssetTransactionsService.depositNotify(trxNo,status,"");
        System.out.println("depositeNotifyTest "+ model);
    }


    @Test
    public void depositeQueryTest()
    {
        wallet();
       Long depositId = 864048328957104128L;
       walletAssetTransactionsService.depositQueryAndUpdate(depositId,accountId);


        /**

        TreeMap<String, String> map = new TreeMap<String, String>();
        // step 1 组装参数
        String merchant_sn = "3b11969f45c04e7da519d4834ac6a7df";
        map.put("merchantSn", merchant_sn);
        map.put("tradeNo", "202403181616045");// 202403181616045  17107498109511006482
       String str = "";
        log.info("代收业务结果查询：str:{}", str);
        map.put("sign", DigestUtils.md5Hex(merchant_sn+map.get("tradeNo")+"92449c375397461098f18db0e6b108dc"));
        System.out.println(map.get("sign"));
        // step 2 发送请求
        String url = "https://wivpay.titlisnet.com/gateway/pay/order/query/payin";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity httpEntity = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        log.info("代收业务结果查询：url:{}", url);
        log.info("代收业务结果查询：request body:{}", map);
        ResponseEntity<Object> rsp = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Object.class);
        str = JSONObject.toJSONString(rsp.getBody());
        log.info("代收业务结果查询：response body:{}", str);
        // step 3 报文解析
        JSONObject object = JSONObject.parseObject(str);
         */

    }


}