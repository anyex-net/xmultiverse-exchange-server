package com.anyex.exchange.binance.v3.future;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.InetSocketAddress;
import java.net.Proxy;

import org.apache.commons.lang3.StringUtils;

import com.anyex.exchange.binance.config.BinanceApiException;
import com.anyex.exchange.binance.config.BinanceParameterConfig;
import com.anyex.exchange.binance.security.AuthenticationInterceptor;
import com.anyex.exchange.binance.service.BinanceApiError;
import com.anyex.exchange.binance.service.impl.BinanceApiService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Generates a Binance API implementation based on @see {@link BinanceApiService}.
 */
public class BinanceFutureServiceGenerator {

    static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
    //不同服务唯一的区别在于基础url不同
    private static Retrofit.Builder builder =
        new Retrofit.Builder()
            .baseUrl("https://dapi.binance.com")
            .addConverterFactory(JacksonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String apiKey, String secret) {
        if (!StringUtils.isEmpty(apiKey) && !StringUtils.isEmpty(secret)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(apiKey, secret);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                if(BinanceParameterConfig.binanceURL.PROXY_IS_NEED) {
                    httpClient.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(BinanceParameterConfig.binanceURL.PROXY_HOST, BinanceParameterConfig.binanceURL.PROXY_PORT)));
                }
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                BinanceApiError apiError = getBinanceApiError(response);
                throw new BinanceApiException(apiError);
            }
        } catch (IOException e) {
            throw new BinanceApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public static BinanceApiError getBinanceApiError(Response<?> response) throws IOException, BinanceApiException {
        return (BinanceApiError)retrofit.responseBodyConverter(BinanceApiError.class, new Annotation[0])
            .convert(response.errorBody());
    }
}