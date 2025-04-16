package com.anyex.exchange.viabtc.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.exchange.viabtc.config.ViabtcConfig;
import com.anyex.exchange.viabtc.req.*;

import java.util.ArrayList;
import java.util.List;

public class ViabtcMarketApi extends ViabtcApi {
    static
    {
    }

    /**
     * {"result":"0","id":1740377194260}
     *
     * @param reqMarketLast
     * @return
     * @throws BusinessException
     */
    public static JSONObject marketLast(ReqMarketLast reqMarketLast) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "market.last");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqMarketLast.getMarket())) {
            paramsArray.add(reqMarketLast.getMarket());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("marketLast reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"result":[],"id":1740377730133}
     *
     * @param reqMarketDeals
     * @return
     * @throws BusinessException
     */
    public static JSONObject marketDeals(ReqMarketDeals reqMarketDeals) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "market.deals");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqMarketDeals.getMarket())) {
            paramsArray.add(reqMarketDeals.getMarket());
        }
        if(null != reqMarketDeals.getLimit()) {
            paramsArray.add(reqMarketDeals.getLimit());
        }
        if(null != reqMarketDeals.getLastId()) {
            paramsArray.add(reqMarketDeals.getLastId());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("marketDeals reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"result":{"offset":1,"records":[],"limit":100},"id":1740378294318}
     *
     * @param reqMarketUserDeals
     * @return
     * @throws BusinessException
     */
    public static JSONObject marketUserDeals(ReqMarketUserDeals reqMarketUserDeals) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "market.user_deals");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqMarketUserDeals.getUserId()) {
            paramsArray.add(reqMarketUserDeals.getUserId());
        }
        if(StringUtils.isNotEmpty(reqMarketUserDeals.getMarket())) {
            paramsArray.add(reqMarketUserDeals.getMarket());
        }
        if(null != reqMarketUserDeals.getOffset()) {
            paramsArray.add(reqMarketUserDeals.getOffset());
        }
        if(null != reqMarketUserDeals.getLimit()) {
            paramsArray.add(reqMarketUserDeals.getLimit());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("marketUserDeals reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"result":[],"id":1740379879524}
     *
     * @param reqMarketKline
     * @return
     * @throws BusinessException
     */
    public static JSONObject marketKline(ReqMarketKline reqMarketKline) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "market.kline");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqMarketKline.getMarket())) {
            paramsArray.add(reqMarketKline.getMarket());
        }
        if(null != reqMarketKline.getStart()) {
            paramsArray.add(reqMarketKline.getStart());
        }
        if(null != reqMarketKline.getEnd()) {
            paramsArray.add(reqMarketKline.getEnd());
        }
        if(null != reqMarketKline.getInterval()) {
            paramsArray.add(reqMarketKline.getInterval());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("marketKline reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"result":{"volume":"0","period":86400,"high":"0","deal":"0","last":"0","low":"0","close":"0","open":"0"},"id":1740378544428}
     *
     * @param reqMarketStatus
     * @return
     * @throws BusinessException
     */
    public static JSONObject marketStatus(ReqMarketStatus reqMarketStatus) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "market.status");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqMarketStatus.getMarket())) {
            paramsArray.add(reqMarketStatus.getMarket());
        }
        if(null != reqMarketStatus.getPeriod()) {
            paramsArray.add(reqMarketStatus.getPeriod());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("marketStatus reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"result":{"volume":"0","high":"0","deal":"0","last":"0","low":"0","open":"0"},"id":1740378777386}
     *
     * @param reqMarketStatusToday
     * @return
     * @throws BusinessException
     */
    public static JSONObject marketStatusToday(ReqMarketStatusToday reqMarketStatusToday) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "market.status_today");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqMarketStatusToday.getMarket())) {
            paramsArray.add(reqMarketStatusToday.getMarket());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("marketStatusToday reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"result":[{"money":"BCH","min_amount":"0.001","name":"BTCBCH","stock_prec":8,"stock":"BTC","money_prec":8,"fee_prec":4},
     *            {"money":"BCH","min_amount":"0.001","name":"LTCBCH","stock_prec":8,"stock":"LTC","money_prec":8,"fee_prec":4},
     *            {"money":"BCH","min_amount":"0.001","name":"ETHBCH","stock_prec":8,"stock":"ETH","money_prec":8,"fee_prec":4},
     *            {"money":"BCH","min_amount":"0.001","name":"ETCBCH","stock_prec":8,"stock":"ETC","money_prec":8,"fee_prec":4},
     *            {"money":"BCH","min_amount":"0.001","name":"ZECBCH","stock_prec":8,"stock":"ZEC","money_prec":8,"fee_prec":4},
     *            {"money":"BCH","min_amount":"0.001","name":"XMRBCH","stock_prec":8,"stock":"XMR","money_prec":8,"fee_prec":4},
     *            {"money":"BCH","min_amount":"0.001","name":"DSHBCH","stock_prec":8,"stock":"DSH","money_prec":8,"fee_prec":4},
     *            {"money":"BCH","min_amount":"0.001","name":"BIEXBCH","stock_prec":8,"stock":"BIEX","money_prec":8,"fee_prec":4}], "id":1740378892154}
     *
     * @return
     * @throws BusinessException
     */
    public static JSONObject marketList() throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "market.list");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        // if(null != marketList.getUserId()) {
        // paramsArray.add(marketList.getUserId());
        // }

        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("marketList reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     *
     * @return
     * @throws BusinessException
     */
    public static List<JSONObject> marketListStatusToday() throws BusinessException
    {
        List<JSONObject> list = new ArrayList<JSONObject>();
        //
        JSONObject marketListJsonObject = ViabtcMarketApi.marketList();
        if(null != marketListJsonObject && marketListJsonObject.size() > 0)
        {
            JSONArray marketListJsonObjectArray = marketListJsonObject.getJSONArray("result");
            ReqMarketStatusToday reqMarketStatusToday = new ReqMarketStatusToday();
            //
            for(int i=0; i<marketListJsonObjectArray.size(); i++)
            {
                reqMarketStatusToday.setMarket(marketListJsonObjectArray.getJSONObject(i).getString("name"));
                //
                System.out.println("marketListStatusToday reqMarketStatusToday:" + reqMarketStatusToday);
                JSONObject respJsonObject = ViabtcMarketApi.marketStatusToday(reqMarketStatusToday);
                respJsonObject.put("name", reqMarketStatusToday.getMarket());
                respJsonObject.remove("id");
                respJsonObject.remove("error");
                System.out.println("marketListStatusToday respJsonObject:" + respJsonObject);
                //
                list.add(respJsonObject);
            }
        }
        //
        for(int j=0; j<list.size(); j++)
        {
            System.out.println("list index j:" + list.get(j));
        }

        return list;
    }

    /**
     * {"result":[{"bid_amount":"100","ask_amount":"0","name":"BIEXBCH","ask_count":0,"bid_count":1}],"id":1740379055049}
     *
     * @param reqMarketSummary
     * @return
     * @throws BusinessException
     */
    public static JSONObject marketSummary(ReqMarketSummary reqMarketSummary) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "market.summary");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqMarketSummary.getMarket()) {
            paramsArray.add(reqMarketSummary.getMarket());
        }

        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("marketSummary reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    //
    public static void main(String[] args)
    {
//        ReqMarketLast reqMarketLast = new ReqMarketLast();
//        reqMarketLast.setMarket("BIEXBCH");
//        System.out.println("marketLast respJson:" + marketLast(reqMarketLast));

//        ReqMarketDeals reqMarketDeals = new ReqMarketDeals();
//        reqMarketDeals.setMarket("BIEXBCH");
//        reqMarketDeals.setLimit(10);
//        reqMarketDeals.setLastId(1);
//        System.out.println("marketDeals respJson:" + marketDeals(reqMarketDeals));

//        ReqMarketUserDeals reqMarketUserDeals = new ReqMarketUserDeals();
//        reqMarketUserDeals.setUserId(1l);
//        reqMarketUserDeals.setMarket("BIEXBCH");
//        reqMarketUserDeals.setOffset(1);
//        reqMarketUserDeals.setLimit(100);
//        System.out.println("marketUserDeals respJson:" + marketUserDeals(reqMarketUserDeals));

//        ReqMarketKline reqMarketKline = new ReqMarketKline();
//        reqMarketKline.setMarket("BIEXBCH");
//        reqMarketKline.setStart(1593654291);
//        reqMarketKline.setEnd(1593740691);
//        reqMarketKline.setInterval(3600);
//        System.out.println("marketKline respJson:" + marketKline(reqMarketKline));

//        ReqMarketStatus reqMarketStatus = new ReqMarketStatus();
//        reqMarketStatus.setMarket("BIEXBCH");
//        reqMarketStatus.setPeriod(86400);
//        System.out.println("marketStatus respJson:" + marketStatus(reqMarketStatus));

//        ReqMarketStatusToday reqMarketStatusToday = new ReqMarketStatusToday();
//        reqMarketStatusToday.setMarket("BIEXBCH");
//        System.out.println("marketStatusToday respJson:" + marketStatusToday(reqMarketStatusToday));

//        System.out.println("marketList respJson:" + marketList());

//        ReqMarketSummary reqMarketSummary = new ReqMarketSummary();
//        reqMarketSummary.setMarket("BIEXBCH");
//        System.out.println("marketSummary respJson:" + marketSummary(reqMarketSummary));

        marketListStatusToday();
    }

}
