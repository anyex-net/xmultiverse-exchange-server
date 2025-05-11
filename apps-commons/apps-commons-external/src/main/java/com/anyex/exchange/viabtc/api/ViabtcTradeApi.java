package com.anyex.exchange.viabtc.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.exchange.viabtc.config.ViabtcConfig;
import com.anyex.exchange.viabtc.req.*;

public class ViabtcTradeApi extends ViabtcApi {
    static
    {
    }

    /**
     * {"id":1740381702346,"error":{"code":1,"message":"invalid argument"}}
     *
     * {"method":"order.put_limit","id":1740382430042,"params":[1,"BIEXBCH",2,"1","10.23","0.001","0.001","web"]}
     * {"result":{"side":2,"amount":"1","taker_fee":"0.001","deal_stock":"0","source":"web","type":1,"mtime":1740382429.60852,"market":"BIEXBCH","left":"1","price":"10.23","maker_fee":"0.001","ctime":1740382429.60852,"id":5,"deal_fee":"0","user":1,"deal_money":"0"},"id":1740382430042}
     *
     * {"method":"order.put_limit","id":1740382658353,"params":[1,"BIEXBCH",1,"2","10.23","0.001","0.001","web"]}
     * {"result":{"side":1,"amount":"2","taker_fee":"0.001","deal_stock":"1","source":"web","type":1,"mtime":1740382657.8742001,"market":"BIEXBCH","left":"1","price":"10.23","maker_fee":"0.001","ctime":1740382657.874191,"id":6,"deal_fee":"0.01023","user":1,"deal_money":"10.23"},"id":1740382658353}
     *
     * @param reqTradeOrderPutLimit
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderPutLimit(ReqTradeOrderPutLimit reqTradeOrderPutLimit) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.put_limit");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqTradeOrderPutLimit.getUserId()) {
            paramsArray.add(reqTradeOrderPutLimit.getUserId());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutLimit.getMarket())) {
            paramsArray.add(reqTradeOrderPutLimit.getMarket());
        }
        if(null != reqTradeOrderPutLimit.getSide()) {
            paramsArray.add(reqTradeOrderPutLimit.getSide());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutLimit.getAmount())) {
            paramsArray.add(reqTradeOrderPutLimit.getAmount());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutLimit.getPrice())) {
            paramsArray.add(reqTradeOrderPutLimit.getPrice());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutLimit.getTakerFeeRate())) {
            paramsArray.add(reqTradeOrderPutLimit.getTakerFeeRate());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutLimit.getMakerFeeRate())) {
            paramsArray.add(reqTradeOrderPutLimit.getTakerFeeRate());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutLimit.getSource())) {
            paramsArray.add(reqTradeOrderPutLimit.getSource());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderPutLimit reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"method":"order.put_market","id":1740383258139,"params":[1,"BIEXBCH",2,"2","0.001","web"]}
     * {"result":{"side":2,"amount":"2","taker_fee":"0.001","deal_stock":"0.19550342","source":"web","type":2,"mtime":1740383257.8356941,"market":"BIEXBCH","left":"1.34000000e-8","price":"0","maker_fee":"0","ctime":1740383257.835686,"id":7,"deal_fee":"0.00019550342","user":1,"deal_money":"1.9999999866"},"id":1740383258139}
     *
     * @param reqTradeOrderPutMarket
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderPutMarket(ReqTradeOrderPutMarket reqTradeOrderPutMarket) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.put_market");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqTradeOrderPutMarket.getUserId()) {
            paramsArray.add(reqTradeOrderPutMarket.getUserId());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutMarket.getMarket())) {
            paramsArray.add(reqTradeOrderPutMarket.getMarket());
        }
        if(null != reqTradeOrderPutMarket.getSide()) {
            paramsArray.add(reqTradeOrderPutMarket.getSide());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutMarket.getAmount())) {
            paramsArray.add(reqTradeOrderPutMarket.getAmount());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutMarket.getTakerFeeRate())) {
            paramsArray.add(reqTradeOrderPutMarket.getTakerFeeRate());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPutMarket.getSource())) {
            paramsArray.add(reqTradeOrderPutMarket.getSource());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderPutMarket reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"method":"order.cancel","id":1740383733004,"params":[1,"BIEXBCH",7]}
     * {"id":1740383733004,"error":{"code":10,"message":"order not found"}}
     *
     * @param reqTradeOrderCancel
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderCancel(ReqTradeOrderCancel reqTradeOrderCancel) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.cancel");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqTradeOrderCancel.getUserId()) {
            paramsArray.add(reqTradeOrderCancel.getUserId());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderCancel.getMarket())) {
            paramsArray.add(reqTradeOrderCancel.getMarket());
        }
        if(null != reqTradeOrderCancel.getOrderId()) {
            paramsArray.add(reqTradeOrderCancel.getOrderId());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderCancel reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"id":1740384324162,"error":{"code":1,"message":"invalid argument"}}
     * {"result":{"offset":1,"records":[],"limit":100},"id":1740384384769}
     * {"result":{"offset":0,"records":[{"deal_order_id":6,"amount":"1","deal":"10.23","role":1,"price":"10.23","fee":"0.001","time":1740382657.8742001,"id":2,"user":1}],"limit":100},"id":1740384409540}
     *
     *
     * @param reqTradeOrderDeals
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderDeals(ReqTradeOrderDeals reqTradeOrderDeals) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.deals");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqTradeOrderDeals.getOrderId()) {
            paramsArray.add(reqTradeOrderDeals.getOrderId());
        }
        if(null != reqTradeOrderDeals.getOffset()) {
            paramsArray.add(reqTradeOrderDeals.getOffset());
        }
        if(null != reqTradeOrderDeals.getLimit()) {
            paramsArray.add(reqTradeOrderDeals.getLimit());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderDeals reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"method":"order.book","id":1740386246885,"params":["BIEXBCH",1,0,100]}
     * {"result":{"total":1,"offset":0,"limit":100,"orders":[{"side":1,"amount":"2","taker_fee":"0.001","deal_stock":"1.19550342","source":"web","type":1,"mtime":1740383257.8356941,"market":"BIEXBCH","left":"0.80449658","price":"10.23","maker_fee":"0.001","ctime":1740382657.874191,"id":6,"deal_fee":"0.0122299999866","user":1,"deal_money":"12.2299999866"}]},"id":1740386246885}
     *
     * {"method":"order.book","id":1740386405630,"params":["BIEXBCH",2,0,100]}
     * {"result":{"total":1,"offset":0,"limit":100,"orders":[{"side":2,"amount":"100","taker_fee":"0.001","deal_stock":"0e-8","type":1,"mtime":1737623287.61889,"market":"BIEXBCH","left":"100","price":"1.5","maker_fee":"0.001","ctime":1737623287.61889,"id":4,"deal_fee":"0e-12","user":1,"deal_money":"0e-16"}]},"id":1740386405630}
     *
     * @param reqTradeOrderBook
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderBook(ReqTradeOrderBook reqTradeOrderBook) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.book");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqTradeOrderBook.getMarket())) {
            paramsArray.add(reqTradeOrderBook.getMarket());
        }
        if(null != reqTradeOrderBook.getSide()) {
            paramsArray.add(reqTradeOrderBook.getSide());
        }
        if(null != reqTradeOrderBook.getOffset()) {
            paramsArray.add(reqTradeOrderBook.getOffset());
        }
        if(null != reqTradeOrderBook.getLimit()) {
            paramsArray.add(reqTradeOrderBook.getLimit());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderBook reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"method":"order.depth","id":1740386747874,"params":["BIEXBCH",100,"0"]}
     * {"result":{"asks":[["10.23","0.80449658"]],"bids":[["1.5","100"]]},"id":1740386747874}
     *
     * @param reqTradeOrderDepth
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderDepth(ReqTradeOrderDepth reqTradeOrderDepth) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.depth");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqTradeOrderDepth.getMarket())) {
            paramsArray.add(reqTradeOrderDepth.getMarket());
        }
        if(null != reqTradeOrderDepth.getLimit()) {
            paramsArray.add(reqTradeOrderDepth.getLimit());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderDepth.getInterval())) {
            paramsArray.add(reqTradeOrderDepth.getInterval());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderDepth reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"method":"order.pending","id":1740386974900,"params":[1,"BIEXBCH",0,100]}
     * {"result":{"total":2,"offset":0,"records":[{"side":1,"amount":"2","taker_fee":"0.001","deal_stock":"1.19550342","source":"web","type":1,"mtime":1740383257.8356941,"market":"BIEXBCH","left":"0.80449658","price":"10.23","maker_fee":"0.001","ctime":1740382657.874191,"id":6,"deal_fee":"0.0122299999866","user":1,"deal_money":"12.2299999866"},
     *                                            {"side":2,"amount":"100","taker_fee":"0.001","deal_stock":"0e-8","type":1,"mtime":1737623287.61889,"market":"BIEXBCH","left":"100","price":"1.5","maker_fee":"0.001","ctime":1737623287.61889,"id":4,"deal_fee":"0e-12","user":1,"deal_money":"0e-16"}],"limit":100}, "id":1740386974900}
     *
     * @param reqTradeOrderPending
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderPending(ReqTradeOrderPending reqTradeOrderPending) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.pending");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqTradeOrderPending.getUserId()) {
            paramsArray.add(reqTradeOrderPending.getUserId());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderPending.getMarket())) {
            paramsArray.add(reqTradeOrderPending.getMarket());
        }
        if(null != reqTradeOrderPending.getOffset()) {
            paramsArray.add(reqTradeOrderPending.getOffset());
        }
        if(null != reqTradeOrderPending.getLimit()) {
            paramsArray.add(reqTradeOrderPending.getLimit());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderPending reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"method":"order.pending_detail","id":1740387628233,"params":["BIEXBCH",6]}
     * {"result":{"side":1,"amount":"2","taker_fee":"0.001","deal_stock":"1.19550342","source":"web","type":1,"mtime":1740383257.8356941,"market":"BIEXBCH","left":"0.80449658","price":"10.23","maker_fee":"0.001","ctime":1740382657.874191,"id":6,"deal_fee":"0.0122299999866","user":1,"deal_money":"12.2299999866"},"id":1740387628233}
     *
     * {"method":"order.pending_detail","id":1740387668595,"params":["BIEXBCH",5]}
     * {"id":1740387668595}
     *
     * @param reqTradeOrderPendingDetail
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderPendingDetail(ReqTradeOrderPendingDetail reqTradeOrderPendingDetail) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.pending_detail");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(StringUtils.isNotEmpty(reqTradeOrderPendingDetail.getMarket())) {
            paramsArray.add(reqTradeOrderPendingDetail.getMarket());
        }
        if(null != reqTradeOrderPendingDetail.getOrderId()) {
            paramsArray.add(reqTradeOrderPendingDetail.getOrderId());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderPendingDetail reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"method":"order.finished","id":1740388027739,"params":[1,"BIEXBCH",0,0,0,100]}
     * {"method":"order.finished","id":1740388137512,"params":[1,"BIEXBCH",0,0,0,100,0]}
     * {"result":{"offset":0,"records":[{"ftime":1740383257.8356941,"side":2,"amount":"2","taker_fee":"0.001","deal_stock":"0.19550342","source":"web","type":2,"market":"BIEXBCH","price":"0","maker_fee":"0","ctime":1740383257.835686,"id":7,"deal_fee":"0.00019550342","user":1,"deal_money":"1.9999999866"},
     *                                  {"ftime":1740382657.8742001,"side":2,"amount":"1","taker_fee":"0.001","deal_stock":"1","source":"web","type":1,"market":"BIEXBCH","price":"10.23","maker_fee":"0.001","ctime":1740382429.60852,"id":5,"deal_fee":"0.001","user":1,"deal_money":"10.23"}],"limit":100},"id":1740388027739}
     *
     * {"method":"order.finished","id":1740388098800,"params":[1,"BIEXBCH",0,0,0,100,1]}
     * {"result":{"offset":0,"records":[],"limit":100},"id":1740388098800}
     *
     * @param reqTradeOrderFinished
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderFinished(ReqTradeOrderFinished reqTradeOrderFinished) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.finished");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqTradeOrderFinished.getUserId()) {
            paramsArray.add(reqTradeOrderFinished.getUserId());
        }
        if(StringUtils.isNotEmpty(reqTradeOrderFinished.getMarket())) {
            paramsArray.add(reqTradeOrderFinished.getMarket());
        }
        if(null != reqTradeOrderFinished.getStartTime()) {
            paramsArray.add(reqTradeOrderFinished.getStartTime());
        }
        if(null != reqTradeOrderFinished.getEndTime()) {
            paramsArray.add(reqTradeOrderFinished.getEndTime());
        }
        if(null != reqTradeOrderFinished.getOffset()) {
            paramsArray.add(reqTradeOrderFinished.getOffset());
        }
        if(null != reqTradeOrderFinished.getLimit()) {
            paramsArray.add(reqTradeOrderFinished.getLimit());
        }
        if(null != reqTradeOrderFinished.getSide()) {
            paramsArray.add(reqTradeOrderFinished.getSide());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderPendingDetail reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    /**
     * {"method":"order.finished_detail","id":1740388730952,"params":[6]}
     * {"id":1740388730952}
     *
     * {"method":"order.finished_detail","id":1740388763792,"params":[5]}
     * {"result":{"ftime":1740382657.8742001,"side":2,"amount":"1","taker_fee":"0.001","deal_stock":"1","source":"web","type":1,"market":"BIEXBCH","price":"10.23","maker_fee":"0.001","ctime":1740382429.60852,"id":5,"deal_fee":"0.001","user":1,"deal_money":"10.23"}, "id":1740388763792}
     *
     * @param reqTradeOrderFinishedDetail
     * @return
     * @throws BusinessException
     */
    public static JSONObject tradeOrderFinishedDetail(ReqTradeOrderFinishedDetail reqTradeOrderFinishedDetail) throws BusinessException
    {
        // 创建一个JSON对象
        JSONObject jsonRequest = new JSONObject();
        // 设置method和id字段
        jsonRequest.put("method", "order.finished_detail");
        jsonRequest.put("id", System.currentTimeMillis());
        // 动态创建params数组
        JSONArray paramsArray = new JSONArray();
        // 添加所有提供的参数到params数组
        if(null != reqTradeOrderFinishedDetail.getOrderId()) {
            paramsArray.add(reqTradeOrderFinishedDetail.getOrderId());
        }
        // 将params数组放入请求对象
        jsonRequest.put("params", paramsArray);

        // 使用Fastjson的toJSONString方法快速高效地转换为字符串
        String reqJson = JSON.toJSONString(jsonRequest);
        System.out.println("tradeOrderFinishedDetail reqJson:" + reqJson);
        JSONObject obj = JSON.parseObject(postWithJSON(ViabtcConfig.baseUrl, reqJson));
        return obj;
    }

    //
    public static void main(String[] args)
    {
//        ReqTradeOrderPutLimit reqTradeOrderPutLimit = new ReqTradeOrderPutLimit();
//        reqTradeOrderPutLimit.setUserId(1l);
//        reqTradeOrderPutLimit.setMarket("BIEXBCH");
//        reqTradeOrderPutLimit.setSide(1);
//        reqTradeOrderPutLimit.setAmount("2");
//        reqTradeOrderPutLimit.setPrice("10.23");
//        reqTradeOrderPutLimit.setTakerFeeRate("0.001");
//        reqTradeOrderPutLimit.setMakerFeeRate("0.001");
//        reqTradeOrderPutLimit.setSource("web");
//        System.out.println("tradeOrderPutLimit respJson:" + tradeOrderPutLimit(reqTradeOrderPutLimit));

//        ReqTradeOrderPutMarket reqTradeOrderPutMarket = new ReqTradeOrderPutMarket();
//        reqTradeOrderPutMarket.setUserId(1l);
//        reqTradeOrderPutMarket.setMarket("BIEXBCH");
//        reqTradeOrderPutMarket.setSide(2);
//        reqTradeOrderPutMarket.setAmount("2");
//        reqTradeOrderPutMarket.setTakerFeeRate("0.001");
//        reqTradeOrderPutMarket.setSource("web");
//        System.out.println("tradeOrderPutMarket respJson:" + tradeOrderPutMarket(reqTradeOrderPutMarket));

//        ReqTradeOrderCancel reqTradeOrderCancel = new ReqTradeOrderCancel();
//        reqTradeOrderCancel.setUserId(1l);
//        reqTradeOrderCancel.setMarket("BIEXBCH");
//        reqTradeOrderCancel.setOrderId(7l);
//        System.out.println("tradeOrderCancel respJson:" + tradeOrderCancel(reqTradeOrderCancel));

//        ReqTradeOrderDeals reqTradeOrderDeals = new ReqTradeOrderDeals();
//        reqTradeOrderDeals.setOrderId(5l);
//        reqTradeOrderDeals.setOffset(0);
//        reqTradeOrderDeals.setLimit(100);
//        System.out.println("tradeOrderDeals respJson:" + tradeOrderDeals(reqTradeOrderDeals));

        ReqTradeOrderBook reqTradeOrderBook = new ReqTradeOrderBook();
        reqTradeOrderBook.setMarket("BIEXBCH");
        reqTradeOrderBook.setSide(2);
        reqTradeOrderBook.setOffset(0);
        reqTradeOrderBook.setLimit(100);
        System.out.println("tradeOrderBook respJson:" + tradeOrderBook(reqTradeOrderBook));

//        ReqTradeOrderDepth reqTradeOrderDepth = new ReqTradeOrderDepth();
//        reqTradeOrderDepth.setMarket("BIEXBCH");
//        reqTradeOrderDepth.setLimit(100);
//        reqTradeOrderDepth.setInterval("0");
//        System.out.println("tradeOrderDepth respJson:" + tradeOrderDepth(reqTradeOrderDepth));

//        ReqTradeOrderPending reqTradeOrderPending = new ReqTradeOrderPending();
//        reqTradeOrderPending.setUserId(1l);
//        reqTradeOrderPending.setMarket("BIEXBCH");
//        reqTradeOrderPending.setOffset(0);
//        reqTradeOrderPending.setLimit(100);
//        System.out.println("tradeOrderPending respJson:" + tradeOrderPending(reqTradeOrderPending));

//        ReqTradeOrderPendingDetail reqTradeOrderPendingDetail = new ReqTradeOrderPendingDetail();
//        reqTradeOrderPendingDetail.setMarket("BIEXBCH");
//        reqTradeOrderPendingDetail.setOrderId(5l);
//        System.out.println("tradeOrderPendingDetail respJson:" + tradeOrderPendingDetail(reqTradeOrderPendingDetail));

//        ReqTradeOrderFinished reqTradeOrderFinished = new ReqTradeOrderFinished();
//        reqTradeOrderFinished.setUserId(1l);
//        reqTradeOrderFinished.setMarket("BIEXBCH");
//        reqTradeOrderFinished.setStartTime(0);
//        reqTradeOrderFinished.setEndTime(0);
//        reqTradeOrderFinished.setOffset(0);
//        reqTradeOrderFinished.setLimit(100);
//        reqTradeOrderFinished.setSide(0);
//        System.out.println("tradeOrderFinished respJson:" + tradeOrderFinished(reqTradeOrderFinished));

//        ReqTradeOrderFinishedDetail reqTradeOrderFinishedDetail = new ReqTradeOrderFinishedDetail();
//        reqTradeOrderFinishedDetail.setOrderId(5l);
//        System.out.println("tradeOrderFinishedDetail respJson:" + tradeOrderFinishedDetail(reqTradeOrderFinishedDetail));
    }

}
