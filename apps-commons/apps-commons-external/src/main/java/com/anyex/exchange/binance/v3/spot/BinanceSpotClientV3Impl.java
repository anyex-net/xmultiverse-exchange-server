package com.anyex.exchange.binance.v3.spot;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.exchange.binance.bean.account.*;
import com.anyex.exchange.binance.bean.account.request.AllOrdersRequest;
import com.anyex.exchange.binance.bean.account.request.CancelOrderRequest;
import com.anyex.exchange.binance.bean.account.request.OrderRequest;
import com.anyex.exchange.binance.bean.account.request.OrderStatusRequest;
import com.anyex.exchange.binance.bean.general.Asset;
import com.anyex.exchange.binance.bean.market.*;
import com.anyex.exchange.binance.config.BinanceApiConstants;

import static com.anyex.exchange.binance.service.impl.BinanceApiServiceGenerator.createService;
import static com.anyex.exchange.binance.service.impl.BinanceApiServiceGenerator.executeSync;

/**
 * Implementation of Binance's REST API using Retrofit with synchronous/blocking method calls.
 */
public class BinanceSpotClientV3Impl implements BinanceSpotClientV3
{
    private final BinanceSpotV3Service binanceSpotV3Service;
    
    public BinanceSpotClientV3Impl(String apiKey, String secret)
    {
        binanceSpotV3Service = createService(BinanceSpotV3Service.class, apiKey, secret);
    }
    
    // General endpoints
    @Override
    public void ping()
    {
        executeSync(binanceSpotV3Service.ping());
    }
    
    @Override
    public Long getServerTime()
    {
        return executeSync(binanceSpotV3Service.getServerTime()).getServerTime();
    }
    
    @Override
    public JSONObject getExchangeInfo()
    {
        return executeSync(binanceSpotV3Service.getExchangeInfo());
    }
    
    @Override
    public List<Asset> getAllAssets()
    {
        return executeSync(binanceSpotV3Service.getAllAssets(BinanceApiConstants.ASSET_INFO_API_BASE_URL + "assetWithdraw/getAllAsset.html"));
    }
    
    // Market Data endpoints
    @Override
    public JSONObject getOrderBook(String symbol, Integer limit)
    {
        return executeSync(binanceSpotV3Service.getOrderBook(symbol, limit));
    }
    
    @Override
    public List<TradeHistoryItem> getTrades(String symbol, Integer limit)
    {
        return executeSync(binanceSpotV3Service.getTrades(symbol, limit));
    }
    
    @Override
    public List<TradeHistoryItem> getHistoricalTrades(String symbol, Integer limit, Long fromId)
    {
        return executeSync(binanceSpotV3Service.getHistoricalTrades(symbol, limit, fromId));
    }
    
    @Override
    public List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime)
    {
        return executeSync(binanceSpotV3Service.getAggTrades(symbol, fromId, limit, startTime, endTime));
    }
    
    @Override
    public List<AggTrade> getAggTrades(String symbol)
    {
        return getAggTrades(symbol, null, null, null, null);
    }
    
    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime)
    {
        return executeSync(binanceSpotV3Service.getCandlestickBars(symbol, interval.getIntervalId(), limit, startTime, endTime));
    }
    
    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval)
    {
        return getCandlestickBars(symbol, interval, null, null, null);
    }
    
    @Override
    public TickerStatistics get24HrPriceStatistics(String symbol)
    {
        return executeSync(binanceSpotV3Service.get24HrPriceStatistics(symbol));
    }
    
    @Override
    public List<TickerStatistics> getAll24HrPriceStatistics()
    {
        return executeSync(binanceSpotV3Service.getAll24HrPriceStatistics());
    }
    
    @Override
    public TickerPrice getPrice(String symbol)
    {
        return executeSync(binanceSpotV3Service.getLatestPrice(symbol));
    }
    
    @Override
    public List<TickerPrice> getAllPrices()
    {
        return executeSync(binanceSpotV3Service.getLatestPrices());
    }
    
    @Override
    public List<BookTicker> getBookTickers()
    {
        return executeSync(binanceSpotV3Service.getBookTickers());
    }
    
    @Override
    public JSONObject newOrder(NewOrder order)
    {
        return executeSync(binanceSpotV3Service.newOrder(order.getSymbol(), order.getSide(), order.getType(), order.getTimeInForce(), order.getQuantity(), order.getPrice(),
                order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(), null, order.getTimestamp()));
    }
    
    @Override
    public void newOrderTest(NewOrder order)
    {
        executeSync(binanceSpotV3Service.newOrderTest(order.getSymbol(), order.getSide(), order.getType(), order.getTimeInForce(), order.getQuantity(), order.getPrice(),
                order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(), null, order.getTimestamp()));
    }
    
    // Account endpoints
    @Override
    public JSONObject getOrderStatus(OrderStatusRequest orderStatusRequest)
    {
        return executeSync(binanceSpotV3Service.getOrderStatus(orderStatusRequest.getSymbol(), orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
                null, orderStatusRequest.getTimestamp()));
    }
    
    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest)
    {
        executeSync(binanceSpotV3Service.cancelOrder(cancelOrderRequest.getSymbol(), cancelOrderRequest.getOrderId(), cancelOrderRequest.getOrigClientOrderId(),
                cancelOrderRequest.getNewClientOrderId(), null, cancelOrderRequest.getTimestamp()));
    }
    
    @Override
    public List<Order> getOpenOrders(OrderRequest orderRequest)
    {
        return executeSync(binanceSpotV3Service.getOpenOrders(orderRequest.getSymbol(), null, orderRequest.getTimestamp()));
    }
    
    @Override
    public List<Order> getAllOrders(AllOrdersRequest orderRequest)
    {
        return executeSync(
                binanceSpotV3Service.getAllOrders(orderRequest.getSymbol(), orderRequest.getOrderId(), orderRequest.getLimit(), null, orderRequest.getTimestamp()));
    }
    
    @Override
    public JSONObject getAccount(Long recvWindow, Long timestamp)
    {
        return executeSync(binanceSpotV3Service.getAccount(recvWindow, timestamp));
    }
    
    @Override
    public JSONObject getAccount()
    {
        return getAccount(null, System.currentTimeMillis());
    }
    
    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp)
    {
        return executeSync(binanceSpotV3Service.getMyTrades(symbol, limit, fromId, recvWindow, timestamp));
    }
    
    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit)
    {
        return getMyTrades(symbol, limit, null, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
    }
    
    @Override
    public List<Trade> getMyTrades(String symbol)
    {
        return getMyTrades(symbol, null, null, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
    }
    
    @Override
    public WithdrawResult withdraw(String asset, String address, String amount, String name, String addressTag)
    {
        return executeSync(
                binanceSpotV3Service.withdraw(asset, address, amount, name, addressTag, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
    
    @Override
    public DepositHistory getDepositHistory(String asset)
    {
        return executeSync(binanceSpotV3Service.getDepositHistory(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
    
    @Override
    public WithdrawHistory getWithdrawHistory(String asset)
    {
        return executeSync(binanceSpotV3Service.getWithdrawHistory(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
    
    @Override
    public DepositAddress getDepositAddress(String asset)
    {
        return executeSync(binanceSpotV3Service.getDepositAddress(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
    
    // User stream endpoints
    @Override
    public String startUserDataStream()
    {
        return executeSync(binanceSpotV3Service.startUserDataStream()).toString();
    }
    
    @Override
    public void keepAliveUserDataStream(String listenKey)
    {
        executeSync(binanceSpotV3Service.keepAliveUserDataStream(listenKey));
    }
    
    @Override
    public void closeUserDataStream(String listenKey)
    {
        executeSync(binanceSpotV3Service.closeAliveUserDataStream(listenKey));
    }
    
    @Override
    public MarginAccount getMarginAccount(Long recvWindow, Long timestamp)
    {
        return executeSync(binanceSpotV3Service.getMarginAccount(recvWindow, timestamp));
    }
    
    @Override
    public String startMarginUserDataStream()
    {
        return executeSync(binanceSpotV3Service.startMarginUserDataStream()).toString();
    }
    
    @Override
    public void keepMarginAliveUserDataStream(String listenKey)
    {
        executeSync(binanceSpotV3Service.keepMarginAliveUserDataStream(listenKey));
    }
    
    @Override
    public void closeMarginUserDataStream(String listenKey)
    {
        executeSync(binanceSpotV3Service.closeMarginAliveUserDataStream(listenKey));
    }
    
    /**
     * [
     *     {
     *         "symbol": "ADABNB",
     *         "makerCommission": 0.9000,
     *         "takerCommission": 1.0000
     *     },
     *     {
     *         "symbol": "BNBBTC",
     *         "makerCommission": 0.3000,
     *         "takerCommission": 0.3000
     *     }
     * ]
     * @param symbol
     * @return
     */
    @Override
    public JSONArray getSpotTradeFee(String symbol)
    {
        return executeSync(binanceSpotV3Service.getSpotTradeFee(symbol, null, System.currentTimeMillis()));
    }
    
    @Override
    public JSONObject transfer(String asset, BigDecimal amt, Integer type)
    {
        return executeSync(binanceSpotV3Service.transfer(asset, amt, type, null, System.currentTimeMillis()));
    }
    
    @Override
    public void cancelAllOrder(String symbol)
    {
        executeSync(binanceSpotV3Service.cancelAllOrder(symbol, null, System.currentTimeMillis()));
    }
    
    @Override
    public JSONObject getCrossMarginAccount(Long recvWindow, Long timestamp)
    {
        return executeSync(binanceSpotV3Service.getCrossMarginAccount(recvWindow, timestamp));
    }
    
    @Override
    public JSONObject getFixedMarginAccount(String symbols, Long recvWindow, Long timestamp)
    {
        return executeSync(binanceSpotV3Service.getFixedMarginAccount(symbols, recvWindow, timestamp));
    }
    
    @Override
    public JSONObject newMarginOrder(NewOrder order)
    {
        return executeSync(binanceSpotV3Service.newMarginOrder(order.getSymbol(), order.getSide(), order.getType(), order.getTimeInForce(), order.getQuantity(),
                order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(), null, order.getTimestamp()));
    }
    
    @Override
    public JSONObject getMarginOrder(String symbol, String orderId, Long timestamp)
    {
        return executeSync(binanceSpotV3Service.getMarginOrder(symbol, orderId, timestamp));
    }
    
    @Override
    public void cancelMarginOrder(String symbol, String orderId, Long timestamp)
    {
        executeSync(binanceSpotV3Service.cancelMarginOrder(symbol, orderId, timestamp));
    }
}
