package com.anyex.exchange.viabtc.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.exchange.viabtc.config.ViabtcConfig;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceHistory;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceQuery;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceUpdate;
import com.anyex.exchange.viabtc.req.ReqAssetSummary;

public class ViabtcAssetApi extends ViabtcApi {
    static
    {
    }

    /**
     * {"id":1,"error":{"code":1,"message":"invalid argument"}}
     * {"result":{"BTC":{"freeze":"0","available":"0"}},"id":1}
     * {"result":{"BTC":{"freeze":"0","available":"999.9"}},"id":1}
     * balanceQuery:{"result":{"BTC":{"freeze":"0","available":"0"},"ETC":{"freeze":"0","available":"0"},"BIEX":{"freeze":"0","available":"0"},"BCH":{"freeze":"0","available":"0"},
     * "ETH":{"freeze":"0","available":"0"},"DSH":{"freeze":"0","available":"0"},"ZEC":{"freeze":"0","available":"0"},"LTC":{"freeze":"0","available":"0"},"XMR":{"freeze":"0","available":"0"}},"id":1740368346171}
     *
     * @param reqAssetBalanceQuery
     * @return
     * @throws BusinessException
     */
    public static JSONObject balanceQuery(ReqAssetBalanceQuery reqAssetBalanceQuery) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "balance.query");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqAssetBalanceQuery.getUserId()) {
            paramsArray.add(reqAssetBalanceQuery.getUserId());
        }
        if(StringUtils.isNotEmpty(reqAssetBalanceQuery.getCurrency())) {
            paramsArray.add(reqAssetBalanceQuery.getCurrency());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("balanceQuery reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"id":1740368997223,"error":{"code":1,"message":"invalid argument"}}
     * {"result":{"status":"success"},"id":1740374315228}
     *
     * @param reqAssetBalanceUpdate
     * @return
     * @throws BusinessException
     */
    public static JSONObject balanceUpdate(ReqAssetBalanceUpdate reqAssetBalanceUpdate) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "balance.update");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqAssetBalanceUpdate.getUserId()) {
            paramsArray.add(reqAssetBalanceUpdate.getUserId());
        }
        if(StringUtils.isNotEmpty(reqAssetBalanceUpdate.getCurrency())) {
            paramsArray.add(reqAssetBalanceUpdate.getCurrency());
        }
        if(StringUtils.isNotEmpty(reqAssetBalanceUpdate.getBusiness())) {
            paramsArray.add(reqAssetBalanceUpdate.getBusiness());
        }
        if(null != reqAssetBalanceUpdate.getBusinessId()) {
            paramsArray.add(reqAssetBalanceUpdate.getBusinessId());
        }
        if(null != reqAssetBalanceUpdate.getChange()) {
            paramsArray.add(reqAssetBalanceUpdate.getChange());
        }
        if(null != reqAssetBalanceUpdate.getDetail()) {
            paramsArray.add(reqAssetBalanceUpdate.getDetail());
        }

        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("balanceUpdate reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"id":1740375333025,"error":{"code":1,"message":"invalid argument"}}
     * {"result":{"offset":1,"records":[{"business":"deposit","balance":"999.9002","change":"0.0001","time":1740374274.607707,"detail":{"detail":"ReqAssetBalanceUpdate(userId=1, currency=BTC, business=deposit, businessId=1740374274617, change=0.0001, detail=null)","id":1740374274617},"asset":"BTC"},
     *                                  {"business":"deposit","balance":"999.9001","change":"0.0001","time":1740374259.1324179,"detail":{"id":1740374259183},"asset":"BTC"},
     *                                  {"business":"trade","balance":"999.9","change":"-0.1","time":1737019719.222589,"detail":{"p":"1.5","a":"100","f":"0.001","i":2,"m":"BTCBCH"},"asset":"BTC"},
     *                                  {"business":"trade","balance":"1000","change":"100","time":1737019719.222589,"detail":{"p":"1.5","a":"100","i":2,"m":"BTCBCH"},"asset":"BTC"},
     *                                  {"business":"trade","balance":"900","change":"-100","time":1737019719.222589,"detail":{"p":"1.5","a":"100","i":3,"m":"BTCBCH"},"asset":"BTC"},
     *                                  {"business":"test","balance":"1000","change":"1000","time":1737019353.7849269,"detail":{"id":1},"asset":"BTC"}],"limit":100}, "id":1740375571312}
     * @param reqAssetBalanceHistory
     * @return
     * @throws BusinessException
     */
    public static JSONObject balanceHistory(ReqAssetBalanceHistory reqAssetBalanceHistory) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "balance.history");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqAssetBalanceHistory.getUserId()) {
            paramsArray.add(reqAssetBalanceHistory.getUserId());
        }
        if(StringUtils.isNotEmpty(reqAssetBalanceHistory.getCurrency())) {
            paramsArray.add(reqAssetBalanceHistory.getCurrency());
        }
        if(StringUtils.isNotEmpty(reqAssetBalanceHistory.getBusiness())) {
            paramsArray.add(reqAssetBalanceHistory.getBusiness());
        } else {
            paramsArray.add("");
        }
        if(null != reqAssetBalanceHistory.getStartTime()) {
            paramsArray.add(reqAssetBalanceHistory.getStartTime());
        } else {
            paramsArray.add(0);
        }
        if(null != reqAssetBalanceHistory.getEndTime()) {
            paramsArray.add(reqAssetBalanceHistory.getEndTime());
        } else {
            paramsArray.add(0);
        }
        if(null != reqAssetBalanceHistory.getOffset()) {
            paramsArray.add(reqAssetBalanceHistory.getOffset());
        }
        if(null != reqAssetBalanceHistory.getLimit()) {
            paramsArray.add(reqAssetBalanceHistory.getLimit());
        }

        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("balanceUpdate reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"result":[{"prec":8,"name":"BCH"},{"prec":8,"name":"BTC"},
     *            {"prec":8,"name":"LTC"},{"prec":8,"name":"ETH"},
     *            {"prec":8,"name":"ETC"},{"prec":8,"name":"ZEC"},
     *            {"prec":8,"name":"XMR"},{"prec":8,"name":"DSH"},
     *            {"prec":8,"name":"BIEX"}],"id":1740375923582}
     *
     * @return
     * @throws BusinessException
     */
    public static JSONObject assetList() throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "asset.list");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        // if(null != assetList.getUserId()) {
            // paramsArray.add(assetList.getUserId());
        // }

        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("assetList reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"result":[{"total_balance":"999.85","available_count":1,"freeze_count":1,"name":"BCH","freeze_balance":"150","available_balance":"849.85"},
     *            {"total_balance":"999.9003","available_count":1,"freeze_count":0,"name":"BTC","freeze_balance":"0","available_balance":"999.9003"},
     *            {"total_balance":"0","available_count":0,"freeze_count":0,"name":"LTC","freeze_balance":"0","available_balance":"0"},
     *            {"total_balance":"0","available_count":0,"freeze_count":0,"name":"ETH","freeze_balance":"0","available_balance":"0"},
     *            {"total_balance":"0","available_count":0,"freeze_count":0,"name":"ETC","freeze_balance":"0","available_balance":"0"},
     *            {"total_balance":"0","available_count":0,"freeze_count":0,"name":"ZEC","freeze_balance":"0","available_balance":"0"},
     *            {"total_balance":"0","available_count":0,"freeze_count":0,"name":"XMR","freeze_balance":"0","available_balance":"0"},
     *            {"total_balance":"0","available_count":0,"freeze_count":0,"name":"DSH","freeze_balance":"0","available_balance":"0"},
     *            {"total_balance":"5000","available_count":1,"freeze_count":0,"name":"BIEX","freeze_balance":"0","available_balance":"5000"}], "id":1740376038542}
     *
     * {"result":[{"total_balance":"999.9003","available_count":1,"freeze_count":0,"name":"BTC","freeze_balance":"0","available_balance":"999.9003"}], "id":1740376278694}
     *
     * @return
     * @throws BusinessException
     */
    public static JSONObject assetSummary(ReqAssetSummary reqAssetSummary) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "asset.summary");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqAssetSummary.getCurrency())) {
            paramsArray.add(reqAssetSummary.getCurrency());
        }

        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("assetSummary reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    //
    public static void main(String[] args)
    {
//        ReqAssetBalanceQuery reqAssetBalanceQuery = new ReqAssetBalanceQuery();
//        reqAssetBalanceQuery.setUserId(1l);
//        // reqAssetBalanceQuery.setCurrency("BTC");
//        System.out.println("balanceQuery respJson:" + balanceQuery(reqAssetBalanceQuery));

//        ReqAssetBalanceUpdate reqAssetBalanceUpdate = new ReqAssetBalanceUpdate();
//        reqAssetBalanceUpdate.setUserId(1l);
//        reqAssetBalanceUpdate.setCurrency("BTC");
//        reqAssetBalanceUpdate.setBusiness("deposit");
//        reqAssetBalanceUpdate.setBusinessId(System.currentTimeMillis());
//        reqAssetBalanceUpdate.setChange("0.0001");
//        // 更新明细说明Json对象
//        JSONObject detailJsonObject = new JSONObject();
//        detailJsonObject.put("detail", JSON.toJSONString(reqAssetBalanceUpdate));
//        reqAssetBalanceUpdate.setDetail(detailJsonObject);
//        System.out.println("balanceUpdate respJson:" + balanceUpdate(reqAssetBalanceUpdate));

//        ReqAssetBalanceHistory reqAssetBalanceHistory = new ReqAssetBalanceHistory();
//        reqAssetBalanceHistory.setUserId(1l);
//        reqAssetBalanceHistory.setCurrency("BTC");
//        reqAssetBalanceHistory.setBusiness("");
//        reqAssetBalanceHistory.setStartTime(0);
//        reqAssetBalanceHistory.setEndTime(0);
//        reqAssetBalanceHistory.setOffset(1);
//        reqAssetBalanceHistory.setLimit(100);
//        System.out.println("balanceHistory respJson:" + balanceHistory(reqAssetBalanceHistory));

//        System.out.println("assetList respJson:" + assetList());

//        ReqAssetSummary reqAssetSummary = new ReqAssetSummary();
//        reqAssetSummary.setCurrency("BTC");
//        System.out.println("assetSummary respJson:" + assetSummary(reqAssetSummary));
    }

}
