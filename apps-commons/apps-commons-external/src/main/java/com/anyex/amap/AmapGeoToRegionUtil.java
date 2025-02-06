package com.anyex.amap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.amap.model.AmapAreaModel;
import com.anyex.apps.exception.BusinessException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AmapGeoToRegionUtil {
    static {}
    private static final String API_KEY = "372830f519574d877be161e0ef9cc47a";
    private static final String REVERSE_GEO_API = "http://restapi.amap.com/v3/geocode/regeo?key=%s&location=%s";

    public static void main(String[] args) throws Exception {
        System.out.println(getRegion("116.397428,39.90923"));
    }
    // 测试坐标，比如：116.397428,39.90923
    public static AmapAreaModel getRegion(String location) throws Exception {
        String urlString = String.format(REVERSE_GEO_API, API_KEY, location);
        URL url = new URL(urlString);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject jsonObject = JSON.parseObject(content.toString());
        if ("1".equals(jsonObject.getString("status"))) {
            JSONObject regeocode = jsonObject.getJSONObject("regeocode");
            JSONObject addressComponent = regeocode.getJSONObject("addressComponent");
            /*System.out.println(addressComponent.toString());
            String province = addressComponent.getString("province");
            String city = addressComponent.getString("city");
            String district = addressComponent.getString("district");
            String township = addressComponent.getString("township"); // 乡镇信息可能不一定返回
            System.out.println("省：" + province);
            System.out.println("市：" + city);
            System.out.println("区：" + district);
            System.out.println("镇：" + township);*/
            AmapAreaModel model = JSONObject.toJavaObject(addressComponent, AmapAreaModel.class);
            return model;
        } else {
            throw new BusinessException("坐标转地址失败，错误码：" + jsonObject.getString("info"));
        }
    }
}