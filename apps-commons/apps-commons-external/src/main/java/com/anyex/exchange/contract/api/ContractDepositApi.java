package com.anyex.exchange.contract.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.exchange.contract.config.ContractConfig;
import com.anyex.exchange.contract.req.ReqDeposit;

public class ContractDepositApi extends ContractApi{

    public static JSONObject deposit(ReqDeposit reqDeposit) throws BusinessException {
        String apiUrl = "/api/v1/deposit";
        String response = null;
        try {
            reqDeposit.setPayment_token(ContractConfig.payment_token);
            String reqJson = JSON.toJSONString(reqDeposit);
            System.out.println("reqDeploy reqJson:" + reqJson);

            response = postWithJSON(ContractConfig.baseUrl + apiUrl, reqJson);

            if (response == null || response.trim().isEmpty()) {
                throw new BusinessException("API 返回为空");
            }
            System.out.println("response:" + response);
            return JSON.parseObject(response);
        } catch (JSONException e) {
            throw new BusinessException("API 返回内容不是合法的 JSON：" + response);
        } catch (Exception e) {
            throw new BusinessException("调用存入资金接口失败:" + e);
        }
    }
}
