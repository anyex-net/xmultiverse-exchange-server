package com.anyex.exchange.contract.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.exchange.contract.config.ContractConfig;
import com.anyex.exchange.contract.req.ReqDeploy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContractDeployApi extends ContractApi{

    static
    {
    }


    //{
    //	"code": 200,
    //	"msg": "success",
    //	"data": {
    //		"project_token_address": "0x953F76956620EA772d035C73524e5de5b58Ac5A9",
    //		"dividend_contract_address": "0xe021fB725d85761515BA7267A802Ee0D6f1E43EB"
    //	}
    //}
    /**
     * 部署合约 分红合约
     * @param reqDeploy
     * @return
     * @throws BusinessException
     */
    public static JSONObject deploy(ReqDeploy reqDeploy) throws BusinessException {
        String apiUrl = "/api/v1/deploy";
        String response = null;
        try {
            reqDeploy.setPayment_token(ContractConfig.payment_token);
            String reqJson = JSON.toJSONString(reqDeploy);
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
            throw new BusinessException("调用部署接口失败:" + e);
        }
    }

    public static void main(String[] args)
    {
//        ReqDeploy reqDeploy = new ReqDeploy();
//        reqDeploy.setToken_name("TZD");
//        reqDeploy.setToken_symbol("TZD");
//        reqDeploy.setTotal_supply("10000000");
////        reqDeploy.setPayment_token("0x4e8f67Ee68A43001C57EE15F085D6805A3854B0D");
//        JSONObject jsonObject = ContractDeployApi.deploy(reqDeploy);
//        System.out.println(jsonObject);

    }
}
