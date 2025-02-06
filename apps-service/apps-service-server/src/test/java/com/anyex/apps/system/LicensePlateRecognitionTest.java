package com.anyex.apps.system;

import com.aliyun.ocr20191230.Client;
import com.aliyun.teaopenapi.models.Config;

/**
 * @Author
 * @Date 2023/8/7 9:22
 * 车牌识别
 */
public class LicensePlateRecognitionTest {
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        /*
          初始化配置对象com.aliyun.teaopenapi.models.Config
          Config对象存放 AccessKeyId、AccessKeySecret、endpoint等配置
         */
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "ocr.cn-shanghai.aliyuncs.com";
        return new Client(config);
    }

//    public static void main(String[] args) throws Exception {
//        // 创建AccessKey ID和AccessKey Secret，请参见：https://help.aliyun.com/document_detail/175144.html
//        // 如果您使用的是RAM用户的AccessKey，还需要为子账号授予权限AliyunVIAPIFullAccess，请参见：https://help.aliyun.com/document_detail/145025.html
//        // 从环境变量读取配置的AccessKey ID和AccessKey Secret。运行代码示例前必须先配置环境变量。
//        String accessKeyId = "LTAI5tGm1pXyS8UbzGNwbRi5";
//        String accessKeySecret = "9ryMEI2HUnhzkUcqOuaaAHOML9yR0h";
//        Client client = createClient(accessKeyId, accessKeySecret);
//        // 场景一，使用本地文件
//        // InputStream inputStream = new FileInputStream(new File("/tmp/RecognizeLicensePlate1.jpg"));
//        // 场景二，使用任意可访问的url
//        URL url = new URL("https://viapi-test-bj.oss-cn-beijing.aliyuncs.com/viapi-3.0domepic/ocr/RecognizeLicensePlate/cpsb1.jpg");
//        InputStream inputStream = url.openConnection().getInputStream();
//        RecognizeLicensePlateAdvanceRequest recognizeLicensePlateAdvanceRequest = new RecognizeLicensePlateAdvanceRequest()
//                .setImageURLObject(inputStream);
//        RuntimeOptions runtime = new RuntimeOptions();
//        try {
//            // 复制代码运行请自行打印 API 的返回值
//            RecognizeLicensePlateResponse response = client.recognizeLicensePlateAdvance(recognizeLicensePlateAdvanceRequest, runtime);
//            JSONObject parsed = JSONObject.parseObject(JSONObject.toJSONString(response.body.getData()));
//            JSONArray plates = JSONArray.parseArray(parsed.getOrDefault("plates", "").toString());
//            if (CollectionUtils.isNotEmpty(plates)){
//                JSONObject plate = JSONObject.parseObject(plates.getString(0));
//                System.out.println("车牌号输出："+plate.getString("plateNumber"));
//            }
//        } catch (TeaException error) {
//            // 获取整体报错信息
//            System.out.println(com.aliyun.teautil.Common.toJSONString(error));
//            // 获取单个字段
//            System.out.println(error.getCode());
//        }
//    }

//    public static void main(String[] args) {
//        String plateNumberUtils = LicensePlateRecognitionUtils.getPlateNumberUtils("http://10.34.130.253:6501/pic?0E0700990A1048037BA1*hcs00fd066719db45a992021/789/24659;16928931054621378851?pic*701157761*300418*2069*3A0B70980B30DF0EEC17-2*1692919691");
//        System.out.println(plateNumberUtils);
//    }
}
