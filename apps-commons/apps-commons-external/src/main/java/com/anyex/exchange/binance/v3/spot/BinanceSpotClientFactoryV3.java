package com.anyex.exchange.binance.v3.spot;

import com.anyex.exchange.binance.v3.future.BinanceFutureClient;
import com.anyex.exchange.binance.v3.future.BinanceFutureClientImpl;

/**
 * A factory for creating BinanceApi client objects.
 */
public class BinanceSpotClientFactoryV3
{
    /**
     * API Key
     */
    private String apiKey;
    
    /**
     * Secret.
     */
    private String secret;
    
    /**
     * Instantiates a new binance api client factory.
     *
     * @param apiKey the API key
     * @param secret the Secret
     */
    private BinanceSpotClientFactoryV3(String apiKey, String secret)
    {
        this.apiKey = apiKey;
        this.secret = secret;
    }
    
    /**
     * New instance.
     *
     * @param apiKey the API key
     * @param secret the Secret
     *
     * @return the binance api client factory
     */
    public static BinanceSpotClientFactoryV3 newInstance(String apiKey, String secret)
    {
        return new BinanceSpotClientFactoryV3(apiKey, secret);
    }
    
    /**
     * New instance without authentication.
     *
     * @return the binance api client factory
     */
    public static BinanceSpotClientFactoryV3 newInstance()
    {
        return new BinanceSpotClientFactoryV3(null, null);
    }
    
    /**
     * Creates a new synchronous/blocking REST client.
     */
    public BinanceSpotClientV3 newSpotClient()
    {
        return new BinanceSpotClientV3Impl(apiKey, secret);
    }
    
    public BinanceFutureClient newFutureClient()
    {
        return new BinanceFutureClientImpl(apiKey, secret);
    }
}
