package com.anyex.exchange.contract.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.exchange.contract.config.ContractConfig;
import com.anyex.exchange.contract.req.ReqDividend;

import java.math.BigDecimal;

public class ContractDividendApi extends ContractApi{

    public static JSONObject dividend(ReqDividend reqDividend) throws BusinessException {
        String apiUrl = "/api/v1/dividend";
        String response = null;
        try {
            String reqJson = JSON.toJSONString(reqDividend);
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
            throw new BusinessException("调用分红接口失败" + e);
        }
    }

    public static JSONObject getDividend(ReqDividend reqDividend) throws BusinessException {
        String apiUrl = "/api/v1/get_dividend";
        String response = null;
        try {
            String reqJson = JSON.toJSONString(reqDividend);
            System.out.println("reqDeploy reqJson:" + reqJson);

            response = postWithJSON(ContractConfig.baseUrl + apiUrl, reqJson);

            if (response == null || response.trim().isEmpty()) {
                throw new BusinessException("API 返回为空");
            }
            return JSON.parseObject(response);
        } catch (JSONException e) {
            throw new BusinessException("API 返回内容不是合法的 JSON：" + response);
        } catch (Exception e) {
            throw new BusinessException("调用分红接口失败" + e);
        }
    }

    public static void main(String[] args) {
//        ReqDividend reqDividend = new ReqDividend();
////        reqDividend.setAmount(BigDecimal.valueOf(10));
////        reqDividend.setBlock_height(8287421L);
//        reqDividend.setContract_address("0xe021fB725d85761515BA7267A802Ee0D6f1E43EB");
////        reqDividend.setProject_address("0x9fFbE399236CcB097e0752B353e5DD355Ee6CFc0");
////        JSONObject jsonObject = ContractDividendApi.dividend(reqDividend);
////        System.out.println(jsonObject);
//
//        JSONObject jsonObject1 = ContractDividendApi.getDividend(reqDividend);
//        System.out.println(jsonObject1);
    }
}
