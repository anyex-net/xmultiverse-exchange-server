package com.anyex.exchange.binance.v3.future;

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
import com.anyex.exchange.binance.bean.market.CandlestickInterval;
import com.anyex.exchange.binance.future.model.ResponseResult;
import com.anyex.exchange.binance.future.model.enums.*;
import com.anyex.exchange.binance.future.model.market.SymbolOrderBook;
import com.anyex.exchange.binance.future.model.market.SymbolPrice;

/**
 * Binance API façade, supporting synchronous/blocking access Binance's REST API.
 * 该客户端包含了币安多个市场的行情与交易接口
 */
public interface BinanceFutureClient
{
    // General endpoints
    /**
     * Test connectivity to the Rest API.
     */
    void ping();
    
    /**
     * Test connectivity to the Rest API and get the current server time.
     *
     * @return current server time.
     */
    Long getServerTime();
    
    /**
     * @return Current exchange trading rules and symbol information
     */
    JSONObject getExchangeInfo();
    
    /**
     * @return All the supported assets and whether or not they can be withdrawn.
     */
    List<Asset> getAllAssets();
    
    // Market Data endpoints
    /**
     * Get order book of a symbol.
     *
     * @param symbol ticker symbol (e.g. ETHBTC)
     * @param limit  depth of the order book (max 100)
     */
    JSONObject getOrderBook(String symbol, Integer limit);
    
    /**
     * Get recent trades (up to last 500). Weight: 1
     *
     * @param symbol ticker symbol (e.g. ETHBTC)
     * @param limit  of last trades (Default 500; max 500.)
     */
    List<TradeHistoryItem> getTrades(String symbol, Integer limit);
    
    /**
     * Get older trades. Weight: 5
     *
     * @param symbol ticker symbol (e.g. ETHBTC)
     * @param limit  of last trades (Default 500; max 500.)
     * @param fromId TradeId to fetch from. Default gets most recent trades.
     */
    List<TradeHistoryItem> getHistoricalTrades(String symbol, Integer limit, Long fromId);
    
    /**
     * Get compressed, aggregate trades. Trades that fill at the time, from the same order, with
     * the same price will have the quantity aggregated.
     * <p>
     * If both <code>startTime</code> and <code>endTime</code> are sent, <code>limit</code>should not
     * be sent AND the distance between <code>startTime</code> and <code>endTime</code> must be less than 24 hours.
     *
     * @param symbol    symbol to aggregate (mandatory)
     * @param fromId    ID to get aggregate trades from INCLUSIVE (optional)
     * @param limit     Default 500; max 500 (optional)
     * @param startTime Timestamp in ms to get aggregate trades from INCLUSIVE (optional).
     * @param endTime   Timestamp in ms to get aggregate trades until INCLUSIVE (optional).
     * @return a list of aggregate trades for the given symbol
     */
    List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime);
    
    /**
     * Return the most recent aggregate trades for <code>symbol</code>
     *
     * @see #getAggTrades(String, String, Integer, Long, Long)
     */
    List<AggTrade> getAggTrades(String symbol);
    
    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     *
     * @param symbol    symbol to aggregate (mandatory)
     * @param interval  candlestick interval (mandatory)
     * @param limit     Default 500; max 500 (optional)
     * @param startTime Timestamp in ms to get candlestick bars from INCLUSIVE (optional).
     * @param endTime   Timestamp in ms to get candlestick bars until INCLUSIVE (optional).
     * @return a candlestick bar for the given symbol and interval
     */
    List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime);
    
    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     *
     * @see #getCandlestickBars(String, CandlestickInterval, Integer, Long, Long)
     */
    List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval);
    
    /**
     * Get 24 hour price change statistics.
     *
     * @param symbol ticker symbol (e.g. ETHBTC)
     */
    List<TickerStatistics> get24HrPriceStatistics(String symbol);
    
    /**
     * Get 24 hour price change statistics for all symbols.
     */
    List<TickerStatistics> getAll24HrPriceStatistics();
    
    /**
     * Get Latest price for all symbols.
     */
    List<TickerPrice> getAllPrices();
    
    /**
     * Get latest price for <code>symbol</code>.
     *
     * @param symbol ticker symbol (e.g. ETHBTC)
     */
    TickerPrice getPrice(String symbol);
    
    /**
     * Get best price/qty on the order book for all symbols.
     */
    List<BookTicker> getBookTickers();
    
    // Account endpoints
    /**
     * Send in a new order.
     *
     * @param order the new order to submit.
     * @return a response containing details about the newly placed order.
     */
    JSONObject newOrder(NewOrder order);
    
    /**
     * Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
     *
     * @param order the new TEST order to submit.
     */
    void newOrderTest(NewOrder order);
    
    /**
     * Check an order's status.
     *
     * @param orderStatusRequest order status request options/filters
     * @return an order
     */
    JSONObject getOrderStatus(OrderStatusRequest orderStatusRequest);
    
    /**
     * Cancel an active order.
     *
     */
    JSONObject cancelOrder(String symbol, Long orderId, String clinetId);
    
    /**
     * Get all open orders on a symbol.
     *
     * @param orderRequest order request parameters
     * @return a list of all account open orders on a symbol.
     */
    List<Order> getOpenOrders(OrderRequest orderRequest);
    
    /**
     * Get all account orders; active, canceled, or filled.
     *
     * @param orderRequest order request parameters
     * @return a list of all account orders
     */
    List<Order> getAllOrders(AllOrdersRequest orderRequest);
    
    /**
     * Get current account information.
     */
    JSONObject getAccount(Long recvWindow, Long timestamp);
    
    /**
     * Get current account information using default parameters.
     */
    JSONObject getAccount();
    
    /**
     * 获取币本位交割合约账户与仓位信息（所有币种）,权重5
     * @return
     */
    JSONObject getCoinFutureAccount(Long recvWindow, Long timestamp);
    
    /**
     * 获取币本位交割合约账户余额（所有币种）,权重1
     * @return
     */
    JSONArray getCoinFutureBalance(Long recvWindow, Long timestamp);
    
    /**
     * 获取币本位交割合约持仓信息（所有币种）,权重1
     * currency 和 pair 不要同时提供
     * currency 和 pair 均不提供则返回所有上市状态和结算中的symbol
     * 对于单向持仓模式，仅会展示"BOTH"方向的持仓
     * 对于双向持仓模式，会展示所有"BOTH", "LONG", 和"SHORT"方向的持仓
     *
     * @return
     */
    JSONArray getCoinFuturePositionRisk(String currency, String pair, Long recvWindow, Long timestamp);
    
    /**
     * Get trades for a specific account and symbol.
     *
     * @param symbol symbol to get trades from
     * @param limit  default 500; max 500
     * @param fromId TradeId to fetch from. Default gets most recent trades.
     * @return a list of trades
     */
    List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp);
    
    /**
     * Get trades for a specific account and symbol.
     *
     * @param symbol symbol to get trades from
     * @param limit  default 500; max 500
     * @return a list of trades
     */
    List<Trade> getMyTrades(String symbol, Integer limit);
    
    /**
     * Get trades for a specific account and symbol.
     *
     * @param symbol symbol to get trades from
     * @return a list of trades
     */
    List<Trade> getMyTrades(String symbol);
    
    /**
     * Submit a withdraw request.
     * <p>
     * Enable Withdrawals option has to be active in the API settings.
     *
     * @param asset      asset symbol to withdraw
     * @param address    address to withdraw to
     * @param amount     amount to withdraw
     * @param name       description/alias of the address
     * @param addressTag Secondary address identifier for coins like XRP,XMR etc.
     */
    WithdrawResult withdraw(String asset, String address, String amount, String name, String addressTag);
    
    /**
     * Fetch account deposit history.
     *
     * @return deposit history, containing a list of deposits
     */
    DepositHistory getDepositHistory(String asset);
    
    /**
     * Fetch account withdraw history.
     *
     * @return withdraw history, containing a list of withdrawals
     */
    WithdrawHistory getWithdrawHistory(String asset);
    
    /**
     * Fetch deposit address.
     *
     * @return deposit address for a given asset.
     */
    DepositAddress getDepositAddress(String asset);
    
    // User stream endpoints
    /**
     * Start a new user data stream.
     *
     * @return a listen key that can be used with data streams
     */
    String startUserDataStream();
    
    /**
     * PING a user data stream to prevent a time out.
     *
     * @param listenKey listen key that identifies a data stream
     */
    void keepAliveUserDataStream(String listenKey);
    
    /**
     * Close out a new user data stream.
     *
     * @param listenKey listen key that identifies a data stream
     */
    void closeUserDataStream(String listenKey);
    
    /**
     *  获取币币杠杆资产
     * Get margin account
     */
    MarginAccount getMarginAccount(Long recvWindow, Long timestamp);
    
    /**
     * Start a new user data stream.
     *
     * @return a listen key that can be used with data streams
     */
    String startMarginUserDataStream();
    
    /**
     * PING a user data stream to prevent a time out.
     *
     * @param listenKey listen key that identifies a data stream
     */
    void keepMarginAliveUserDataStream(String listenKey);
    
    /**
     * Close out a new user data stream.
     *
     * @param listenKey listen key that identifies a data stream
     */
    void closeMarginUserDataStream(String listenKey);

    /**
     * 获取现货交易手续费率
     * @param symbol
     * @return
     */
    JSONObject getSpotTradeFee(String symbol);

    /**
     * 资产划转接口
     * @param asset
     * @param amt
     * @param type
     * @return
     */
    JSONObject transfer(String asset, BigDecimal amt, Integer type);

    /**
     * 币本位交割合约下单接口
     */
    JSONObject postOrder(String symbol, OrderSide side, PositionSide positionSide, OrderType orderType, TimeInForce timeInForce, String quantity, String price,
            String reduceOnly, String newClientOrderId, String stopPrice, WorkingType workingType, NewOrderRespType newOrderRespType);

    /**
     * 获取币本位交割合约订单信息接口
     */
    JSONObject getOrder(String symbol, Long orderId, String origClientOrderId);

    /**
     * 币本位交割合约撤单接口
     * @param symbol
     * @return
     */
    JSONObject cancelAllOpenOrder(String symbol);

    /**
     * 获取币本位交割合约最有挂单，示例：
     * [
     *     {
     *         "symbol": "BTCUSD_200626",  // 交易对
     *         "pair": "BTCUSD",           // 标的交易对
     *         "bidPrice": "9650.1",       //最优买单价
     *         "bidQty": "16",             //最优买单挂单量
     *         "askPrice": "9650.3",       //最优卖单价
     *         "askQty": "7",              //最优卖单挂单量
     *         "time": 1591257300345
     *     }
     * ]
     * @param symbol
     * @return
     */
    JSONArray getSymbolOrderBookTicker(String symbol);

    /**
     * 获取币本位最新成交价
     * @param symbol
     * @return
     */
    JSONArray getSymbolPriceTicker(String symbol);
}
