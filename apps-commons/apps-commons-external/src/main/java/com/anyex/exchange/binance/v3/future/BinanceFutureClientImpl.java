package com.anyex.exchange.binance.v3.future;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.exchange.binance.bean.account.*;
import com.anyex.exchange.binance.bean.account.request.AllOrdersRequest;
import com.anyex.exchange.binance.bean.account.request.OrderRequest;
import com.anyex.exchange.binance.bean.account.request.OrderStatusRequest;
import com.anyex.exchange.binance.bean.general.Asset;
import com.anyex.exchange.binance.bean.market.*;
import com.anyex.exchange.binance.bean.market.CandlestickInterval;
import com.anyex.exchange.binance.config.BinanceApiConstants;
import com.anyex.exchange.binance.future.model.enums.*;

import static com.anyex.exchange.binance.v3.future.BinanceFutureServiceGenerator.createService;
import static com.anyex.exchange.binance.v3.future.BinanceFutureServiceGenerator.executeSync;

/**
 * Implementation of Binance's REST API using Retrofit with synchronous/blocking method calls.
 */
public class BinanceFutureClientImpl implements BinanceFutureClient
{
    private final BinanceFutureService binanceFutureService;
    
    public BinanceFutureClientImpl(String apiKey, String secret)
    {
        binanceFutureService = createService(BinanceFutureService.class, apiKey, secret);
    }
    
    // General endpoints
    @Override
    public void ping()
    {
        executeSync(binanceFutureService.ping());
    }
    
    @Override
    public Long getServerTime()
    {
        return executeSync(binanceFutureService.getServerTime()).getServerTime();
    }
    
    @Override
    public JSONObject getExchangeInfo()
    {
        return executeSync(binanceFutureService.getExchangeInfo());
    }
    
    @Override
    public List<Asset> getAllAssets()
    {
        return executeSync(binanceFutureService.getAllAssets(BinanceApiConstants.ASSET_INFO_API_BASE_URL + "assetWithdraw/getAllAsset.html"));
    }
    
    // Market Data endpoints
    @Override
    public JSONObject getOrderBook(String symbol, Integer limit)
    {
        return executeSync(binanceFutureService.getOrderBook(symbol, limit));
    }
    
    @Override
    public List<TradeHistoryItem> getTrades(String symbol, Integer limit)
    {
        return executeSync(binanceFutureService.getTrades(symbol, limit));
    }
    
    @Override
    public List<TradeHistoryItem> getHistoricalTrades(String symbol, Integer limit, Long fromId)
    {
        return executeSync(binanceFutureService.getHistoricalTrades(symbol, limit, fromId));
    }
    
    @Override
    public List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime)
    {
        return executeSync(binanceFutureService.getAggTrades(symbol, fromId, limit, startTime, endTime));
    }
    
    @Override
    public List<AggTrade> getAggTrades(String symbol)
    {
        return getAggTrades(symbol, null, null, null, null);
    }
    
    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime)
    {
        return executeSync(binanceFutureService.getCandlestickBars(symbol, interval.getIntervalId(), limit, startTime, endTime));
    }
    
    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval)
    {
        return getCandlestickBars(symbol, interval, null, null, null);
    }
    
    @Override
    public List<TickerStatistics> get24HrPriceStatistics(String symbol)
    {
        return executeSync(binanceFutureService.get24HrPriceStatistics(symbol));
    }
    
    @Override
    public List<TickerStatistics> getAll24HrPriceStatistics()
    {
        return executeSync(binanceFutureService.getAll24HrPriceStatistics());
    }
    
    @Override
    public TickerPrice getPrice(String symbol)
    {
        return executeSync(binanceFutureService.getLatestPrice(symbol));
    }
    
    @Override
    public List<TickerPrice> getAllPrices()
    {
        return executeSync(binanceFutureService.getLatestPrices());
    }
    
    @Override
    public List<BookTicker> getBookTickers()
    {
        return executeSync(binanceFutureService.getBookTickers());
    }
    
    @Override
    public JSONObject newOrder(NewOrder order)
    {
        return executeSync(binanceFutureService.newOrder(order.getSymbol(), order.getSide(), order.getType(), order.getTimeInForce(), order.getQuantity(), order.getPrice(),
                order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(), order.getRecvWindow(), order.getTimestamp()));
    }
    
    @Override
    public void newOrderTest(NewOrder order)
    {
        executeSync(binanceFutureService.newOrderTest(order.getSymbol(), order.getSide(), order.getType(), order.getTimeInForce(), order.getQuantity(), order.getPrice(),
                order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(), order.getRecvWindow(), order.getTimestamp()));
    }
    
    // Account endpoints
    @Override
    public JSONObject getOrderStatus(OrderStatusRequest orderStatusRequest)
    {
        return executeSync(binanceFutureService.getOrderStatus(orderStatusRequest.getSymbol(), orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
                orderStatusRequest.getRecvWindow(), orderStatusRequest.getTimestamp()));
    }
    
    @Override
    public JSONObject cancelOrder(String symbol, Long orderId, String clientId)
    {
        return executeSync(binanceFutureService.cancelOrder(symbol, orderId, clientId, System.currentTimeMillis()));
    }
    
    @Override
    public List<Order> getOpenOrders(OrderRequest orderRequest)
    {
        return executeSync(binanceFutureService.getOpenOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
    }
    
    @Override
    public List<Order> getAllOrders(AllOrdersRequest orderRequest)
    {
        return executeSync(binanceFutureService.getAllOrders(orderRequest.getSymbol(), orderRequest.getOrderId(), orderRequest.getLimit(), orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()));
    }
    
    @Override
    public JSONObject getAccount(Long recvWindow, Long timestamp)
    {
        return executeSync(binanceFutureService.getAccount(recvWindow, timestamp));
    }
    
    @Override
    public JSONObject getAccount()
    {
        return getAccount(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
    }
    
    @Override
    public JSONObject getCoinFutureAccount(Long recvWindow, Long timestamp)
    {
        return executeSync(binanceFutureService.getCoinFutureAccount(recvWindow, timestamp));
    }
    
    @Override
    public JSONArray getCoinFutureBalance(Long recvWindow, Long timestamp)
    {
        return executeSync(binanceFutureService.getCoinFutureBalance(recvWindow, timestamp));
    }
    
    @Override
    public JSONArray getCoinFuturePositionRisk(String currency, String pair, Long recvWindow, Long timestamp)
    {
        return executeSync(binanceFutureService.getCoinFuturePositionRisk(currency, pair, recvWindow, timestamp));
    }
    
    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp)
    {
        return executeSync(binanceFutureService.getMyTrades(symbol, limit, fromId, recvWindow, timestamp));
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
                binanceFutureService.withdraw(asset, address, amount, name, addressTag, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
    
    @Override
    public DepositHistory getDepositHistory(String asset)
    {
        return executeSync(binanceFutureService.getDepositHistory(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
    
    @Override
    public WithdrawHistory getWithdrawHistory(String asset)
    {
        return executeSync(binanceFutureService.getWithdrawHistory(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
    
    @Override
    public DepositAddress getDepositAddress(String asset)
    {
        return executeSync(binanceFutureService.getDepositAddress(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
    
    // User stream endpoints
    @Override
    public String startUserDataStream()
    {
        return executeSync(binanceFutureService.startUserDataStream()).toString();
    }
    
    @Override
    public void keepAliveUserDataStream(String listenKey)
    {
        executeSync(binanceFutureService.keepAliveUserDataStream(listenKey));
    }
    
    @Override
    public void closeUserDataStream(String listenKey)
    {
        executeSync(binanceFutureService.closeAliveUserDataStream(listenKey));
    }
    
    @Override
    public MarginAccount getMarginAccount(Long recvWindow, Long timestamp)
    {
        return executeSync(binanceFutureService.getMarginAccount(recvWindow, timestamp));
    }
    
    @Override
    public String startMarginUserDataStream()
    {
        return executeSync(binanceFutureService.startMarginUserDataStream()).toString();
    }
    
    @Override
    public void keepMarginAliveUserDataStream(String listenKey)
    {
        executeSync(binanceFutureService.keepMarginAliveUserDataStream(listenKey));
    }
    
    @Override
    public void closeMarginUserDataStream(String listenKey)
    {
        executeSync(binanceFutureService.closeMarginAliveUserDataStream(listenKey));
    }
    
    @Override
    public JSONObject getSpotTradeFee(String symbol)
    {
        return executeSync(binanceFutureService.getSpotTradeFee(symbol, null, System.currentTimeMillis()));
    }
    
    @Override
    public JSONObject transfer(String asset, BigDecimal amt, Integer type)
    {
        return executeSync(binanceFutureService.transfer(asset, amt, type, null, System.currentTimeMillis()));
    }
    
    @Override
    public JSONObject postOrder(String symbol, OrderSide side, PositionSide positionSide, OrderType orderType, TimeInForce timeInForce, String quantity, String price,
            String reduceOnly, String newClientOrderId, String stopPrice, WorkingType workingType, NewOrderRespType newOrderRespType)
    {
        return executeSync(binanceFutureService.postOrder(symbol, side, positionSide, orderType, timeInForce, quantity, price, reduceOnly, newClientOrderId, stopPrice,
                workingType, newOrderRespType, System.currentTimeMillis()));
    }
    
    @Override
    public JSONObject getOrder(String symbol, Long orderId, String origClientOrderId)
    {
        return executeSync(binanceFutureService.getOrder(symbol, orderId, origClientOrderId, null, System.currentTimeMillis()));
    }
    
    @Override
    public JSONObject cancelAllOpenOrder(String symbol)
    {
        return executeSync(binanceFutureService.cancelAllOrder(symbol, null, System.currentTimeMillis()));
    }
    
    @Override
    public JSONArray getSymbolOrderBookTicker(String symbol)
    {
        return executeSync(binanceFutureService.getSymbolOrderBookTicker(symbol, null));
    }
    
    @Override
    public JSONArray getSymbolPriceTicker(String symbol)
    {
        return executeSync(binanceFutureService.getSymbolPriceTicker(symbol, null));
    }
}
