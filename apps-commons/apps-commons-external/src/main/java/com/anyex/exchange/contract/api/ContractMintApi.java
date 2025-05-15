package com.anyex.exchange.contract.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.exchange.contract.config.ContractConfig;
import com.anyex.exchange.contract.req.ReqMint;

import java.math.BigDecimal;

public class ContractMintApi extends ContractApi{

    static {}

    /**
     * 铸币
     * @param reqMint
     * @return
     * @throws BusinessException
     */
    public static JSONObject mint(ReqMint reqMint) throws BusinessException {
        String apiUrl = "/api/v1/mint";
        String response = null;
        try {
            String reqJson = JSON.toJSONString(reqMint);
            System.out.println("reqMint reqJson:" + reqJson);

            response = postWithJSON(ContractConfig.baseUrl + apiUrl, reqJson);

            if (response == null || response.trim().isEmpty()) {
                throw new BusinessException("API 返回为空");
            }
            System.out.println("response:" + response);
            return JSON.parseObject(response);
        } catch (JSONException e) {
            throw new BusinessException("API 返回内容不是合法的 JSON：" + response);
        } catch (Exception e) {
            throw new BusinessException("调用铸币接口失败" + e);
        }
    }

    public static JSONObject getMint(ReqMint reqMint) throws BusinessException {
        String apiUrl = "/api/v1/get_mint";
        String response = null;
        try {
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("contract_address", reqMint.getContract_address());
            jsonRequest.put("recipient_address", reqMint.getRecipient_address());
            String reqJson = JSON.toJSONString(jsonRequest);
            System.out.println("reqMint reqJson:" + reqJson);

            response = postWithJSON(ContractConfig.baseUrl + apiUrl, reqJson);

            if (response == null || response.trim().isEmpty()) {
                throw new BusinessException("API 返回为空");
            }
            return JSON.parseObject(response);
        } catch (JSONException e) {
            throw new BusinessException("API 返回内容不是合法的 JSON：" + response);
        } catch (Exception e) {
            throw new BusinessException("调用代币信息接口失败" + e);
        }
    }
    public static void main(String[] args) {
//        ReqMint reqMint = new ReqMint();
//        reqMint.setContract_address("0x9fFbE399236CcB097e0752B353e5DD355Ee6CFc0");
//        reqMint.setRecipient_address("0x104fe772a9c1269b57272ef42be1b27a8daa9064");
////        reqMint.setAmount(BigDecimal.valueOf(1000));
////        JSONObject jsonObject = ContractMintApi.mint(reqMint);
////        System.out.println(jsonObject);
//
//        JSONObject jsonObject1 = ContractMintApi.getMint(reqMint);
//        System.out.println(jsonObject1);
    }
}
