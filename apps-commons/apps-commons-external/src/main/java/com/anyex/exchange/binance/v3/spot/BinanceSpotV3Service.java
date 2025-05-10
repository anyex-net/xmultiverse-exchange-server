package com.anyex.exchange.binance.v3.spot;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.exchange.binance.bean.OrderSide;
import com.anyex.exchange.binance.bean.OrderType;
import com.anyex.exchange.binance.bean.TimeInForce;
import com.anyex.exchange.binance.bean.account.*;
import com.anyex.exchange.binance.bean.event.ListenKey;
import com.anyex.exchange.binance.bean.general.Asset;
import com.anyex.exchange.binance.bean.general.ExchangeInfo;
import com.anyex.exchange.binance.bean.general.ServerTime;
import com.anyex.exchange.binance.bean.market.*;
import com.anyex.exchange.binance.config.BinanceApiConstants;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * Binance's REST API URL mappings and endpoint security configuration.
 */
public interface BinanceSpotV3Service
{
    // General endpoints
    @GET("/api/v3/ping")
    Call<Void> ping();

    @GET("/api/v3/time")
    Call<ServerTime> getServerTime();

    @GET("/api/v3/exchangeInfo")
    Call<JSONObject> getExchangeInfo();

    @GET
    Call<List<Asset>> getAllAssets(@Url String url);

    // Market data endpoints
    @GET("/api/v3/depth")
    Call<JSONObject> getOrderBook(@Query("symbol") String symbol, @Query("limit") Integer limit);

    @GET("/api/v3/trades")
    Call<List<TradeHistoryItem>> getTrades(@Query("symbol") String symbol, @Query("limit") Integer limit);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @GET("/api/v3/historicalTrades")
    Call<List<TradeHistoryItem>> getHistoricalTrades(@Query("symbol") String symbol, @Query("limit") Integer limit, @Query("fromId") Long fromId);

    @GET("/api/v3/aggTrades")
    Call<List<AggTrade>> getAggTrades(@Query("symbol") String symbol, @Query("fromId") String fromId, @Query("limit") Integer limit, @Query("startTime") Long startTime,
            @Query("endTime") Long endTime);

    @GET("/api/v3/klines")
    Call<List<Candlestick>> getCandlestickBars(@Query("symbol") String symbol, @Query("interval") String interval, @Query("limit") Integer limit,
            @Query("startTime") Long startTime, @Query("endTime") Long endTime);

    @GET("/api/v3/ticker/24hr")
    Call<TickerStatistics> get24HrPriceStatistics(@Query("symbol") String symbol);

    @GET("/api/v3/ticker/24hr")
    Call<List<TickerStatistics>> getAll24HrPriceStatistics();

    @GET("/api/v3/ticker/allPrices")
    Call<List<TickerPrice>> getLatestPrices();

    @GET("/api/v3/ticker/price")
    Call<TickerPrice> getLatestPrice(@Query("symbol") String symbol);

    @GET("/api/v3/ticker/allBookTickers")
    Call<List<BookTicker>> getBookTickers();

    // Account endpoints
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/api/v3/order")
    Call<JSONObject> newOrder(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type, @Query("timeInForce") TimeInForce timeInForce,
            @Query("quantity") String quantity, @Query("price") String price, @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
            @Query("icebergQty") String icebergQty, @Query("newOrderRespType") NewOrderResponseType newOrderRespType, @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/api/v3/order/test")
    Call<Void> newOrderTest(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type, @Query("timeInForce") TimeInForce timeInForce,
            @Query("quantity") String quantity, @Query("price") String price, @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
            @Query("icebergQty") String icebergQty, @Query("newOrderRespType") NewOrderResponseType newOrderRespType, @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/order")
    Call<JSONObject> getOrderStatus(@Query("symbol") String symbol, @Query("orderId") Long orderId, @Query("origClientOrderId") String origClientOrderId,
            @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("/api/v3/order")
    Call<Void> cancelOrder(@Query("symbol") String symbol, @Query("orderId") Long orderId, @Query("origClientOrderId") String origClientOrderId,
            @Query("newClientOrderId") String newClientOrderId, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/openOrders")
    Call<List<Order>> getOpenOrders(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/allOrders")
    Call<List<Order>> getAllOrders(@Query("symbol") String symbol, @Query("orderId") Long orderId, @Query("limit") Integer limit, @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/account")
    Call<JSONObject> getAccount(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/myTrades")
    Call<List<Trade>> getMyTrades(@Query("symbol") String symbol, @Query("limit") Integer limit, @Query("fromId") Long fromId, @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/sapi/v1/capital/withdraw/apply")
    Call<WithdrawResult> withdraw(@Query("asset") String asset, @Query("address") String address, @Query("amount") String amount, @Query("name") String name,
            @Query("addressTag") String addressTag, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/capital/deposit/hisrec")
    Call<DepositHistory> getDepositHistory(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/capital/withdraw/history")
    Call<WithdrawHistory> getWithdrawHistory(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/capital/deposit/address")
    Call<DepositAddress> getDepositAddress(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/asset/tradeFee")
    Call<JSONArray> getSpotTradeFee(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);
    // User stream endpoints

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("/api/v3/userDataStream")
    Call<ListenKey> startUserDataStream();

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("/api/v3/userDataStream")
    Call<Void> keepAliveUserDataStream(@Query("listenKey") String listenKey);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/api/v3/userDataStream")
    Call<Void> closeAliveUserDataStream(@Query("listenKey") String listenKey);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v3/margin/account")
    Call<MarginAccount> getMarginAccount(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("/sapi/v3/userDataStream")
    Call<ListenKey> startMarginUserDataStream();

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("/sapi/v3/userDataStream")
    Call<Void> keepMarginAliveUserDataStream(@Query("listenKey") String listenKey);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/sapi/v3/userDataStream")
    Call<Void> closeMarginAliveUserDataStream(@Query("listenKey") String listenKey);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/sapi/v1/futures/transfer")
    Call<JSONObject> transfer(@Query("asset") String asset, @Query("amount") BigDecimal amount, @Query("type") Integer type, @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("api/v3/openOrders")
    Call<Void> cancelAllOrder(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    // 补充v1杠杆交易相关接口到该服务中
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/sapi/v1/margin/order")
    Call<JSONObject> newMarginOrder(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type,
            @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity, @Query("price") String price,
            @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice, @Query("icebergQty") String icebergQty,
            @Query("newOrderRespType") NewOrderResponseType newOrderRespType, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/margin/account")
    Call<JSONObject> getCrossMarginAccount(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/margin/isolated/account")
    Call<JSONObject> getFixedMarginAccount(@Query("symbols") String symbols, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/margin/order")
    Call<JSONObject> getMarginOrder(@Query("symbol") String symbol, @Query("orderId") String orderId, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("/sapi/v1/margin/order")
    Call<Void> cancelMarginOrder(@Query("symbol") String symbol, @Query("orderId") String orderId, @Query("timestamp") Long timestamp);
}
