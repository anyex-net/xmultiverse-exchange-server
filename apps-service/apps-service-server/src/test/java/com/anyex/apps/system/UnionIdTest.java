//package com.anyex.apps.system;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.anyex.apps.config.ChronosConfig;
//import com.anyex.apps.utils.Gps;
//import com.anyex.apps.utils.PositionUtil;
//import com.anyex.apps.utils.SerialnoUtils;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.YzCockpit.service.YzCockpitService;
//import com.anyex.apps.chronos.entity.SkyBuildEntity;
//import com.anyex.apps.chronos.entity.SkyCommunityResourceEntity;
//import com.anyex.apps.chronos.service.SkyBuildService;
//import com.anyex.apps.chronos.service.SkyCommunityResourceService;
//import com.anyex.apps.common.entity.SysCameraResourcesEntity;
//import com.anyex.apps.common.service.SysCameraResourcesService;
//import com.anyex.apps.shhzfk.entity.ReceiveCompanyEntity;
//import com.anyex.apps.shhzfk.entity.ReceiveDeviceEntity;
//import com.anyex.apps.shhzfk.entity.ReceiveDeviceStateEntity;
//import com.anyex.apps.shhzfk.service.ReceiveCompanyService;
//import com.anyex.apps.shhzfk.service.ReceiveDeviceService;
//import com.anyex.apps.shhzfk.service.ReceiveDeviceStateService;
//import com.anyex.apps.shhzfk.service.ReceiveFaultService;
//import com.anyex.apps.shhzfk.vo.Group;
//import com.anyex.apps.xfxypj.service.XfxypjCreditEvaluationService;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.apache.commons.lang3.RegExUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//import org.springframework.util.StopWatch;
//
//import javax.annotation.Resource;
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import javax.validation.groups.Default;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author
// * @Date 2023/4/26 9:17
// * 联合主键测试
// */
//@Slf4j
//public class UnionIdTest extends BaseServiceImplTest {
//
//    @Resource
//    private ReceiveDeviceService receiveDeviceService;
//
//    @Resource
//    private SysCameraResourcesService sysCameraResourcesService;
//
//    @Resource
//    private SkyBuildService skyBuildService;
//
//    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
//
//    @Resource
//    private ChronosConfig chronosConfig;
//
//    @Resource
//    private ReceiveFaultService receiveFaultService;
//
//
//    @Resource
//    private ReceiveCompanyService companyService;
//
//    @Resource
//    private ReceiveDeviceStateService receiveDeviceStateService;
//
//    @Resource
//    private SkyCommunityResourceService skyCommunityResourceService;
//
//    @Resource
//    private XfxypjCreditEvaluationService xfxypjCreditEvaluationService;
//
//    @Resource
//    private YzCockpitService yzCockpitService;
//
//    /**
//     * 查询测试联合主键查询
//     */
//    @Test
//    public void queryList() {
//        ReceiveDeviceEntity device = new ReceiveDeviceEntity();
//        device.setDeviceId("11");
//        device.setParentId("1");
////        ReceiveDeviceEntity receiveDevicesEntity = receiveDeviceService.getBaseMapper().selectByMultiId(device);
////        log.info("联合主键查询结果：{}", receiveDevicesEntity);
////        //修改
////        receiveDevicesEntity.setDeviceName("修改");
////        receiveDeviceService.saveOrUpdateByMultiId(receiveDevicesEntity);
//    }
//
//    /**
//     * 查询并保存测试
//     */
//    @Test
//    public void queryAndSave() {
//        ReceiveDeviceEntity device = new ReceiveDeviceEntity();
//        device.setDeviceId("11");
//        device.setParentId("1");
//        receiveDeviceService.list(device);
//        device = receiveDeviceService.getById(11);
//        System.out.println("getById device:" + device.toString());
//        //
//        device.setDeviceName("queryAndSave");
//        receiveDeviceService.saveOrUpdate(device);
//        System.out.println("saveOrUpdate device:" + device.toString());
//        //
//        device.setDeviceName("saveOrUpdateBat");
//        List<ReceiveDeviceEntity> listReceiveDeviceEntity = new ArrayList<>();
//        listReceiveDeviceEntity.add(device);
//        receiveDeviceService.saveOrUpdateBatch(listReceiveDeviceEntity);
//        //receiveDeviceService.saveBatch(listReceiveDeviceEntity);
//        //receiveDeviceService.insertOrUpdateBatchList(listReceiveDeviceEntity);
//        System.out.println("saveOrUpdateBatch device:" + device.toString());
//
////        ReceiveDeviceEntity receiveDevicesEntity = receiveDeviceService.getBaseMapper().selectByMultiId(device);
////        log.info("联合主键查询结果：{}", receiveDevicesEntity);
////        //修改
////        receiveDevicesEntity.setDeviceName("修改");
////        receiveDeviceService.saveOrUpdateByMultiId(receiveDevicesEntity);
//    }
//
//    /**
//     * 数据校验
//     */
//    @Test
//    public void dataVerification() {
//        String data = "{\n" +
//                "            \"deviceId\":\"181\",\n" +
//                "            \"deviceName\":\"测试10\",\n" +
//                "            \"parentId\":\"81\",\n" +
//                "            \"deviceCode\":\"11\",\n" +
//                "            \"location\":\"01\",\n" +
//                "            \"deviceManufactory\":\"测试9\",\n" +
//                "            \"notifyPhone\":\"15729446523\",\n" +
//                "            \"deviceType\":\"01\",\n" +
//                "            \"code3c\":\"15729446523\",\n" +
//                "            \"qualifiedCode\":1,\n" +
//                "            \"relationType\":\"01\",\n" +
//                "            \"address\":\"sss\",\n" +
//                "            \"addressCode\":\"11111111\",\n" +
//                "            \"regionCode\":\"GBSWE1451111\",\n" +
//                "            \"buildingType\":\"01\",\n" +
//                "            \"relationId\":\"111\",\n" +
//                "            \"managerCompany\":\"1111\",\n" +
//                "            \"ownerCompany\":\"1111\",\n" +
//                "            \"useCompanys\":\"2222\",\n" +
//                "            \"parentCompanyName\":\"11\",\n" +
//                "            \"firemanagement\":\"11\",\n" +
//                "            \"economicownership\":\"111\",\n" +
//                "            \"fixedassets\":\"1.44\",\n" +
//                "            \"occaupyArea\":\"11\",\n" +
//                "            \"buildArea\":\"11\",\n" +
//                "            \"produceDate\":\"2023-04-05 12:00:00\",\n" +
//                "            \"installDate\":\"2023-04-05 12:00:00\",\n" +
//                "             \"expireDate\":\"2023-04-05 12:00:00\",\n" +
//                "            \"partsNum\":\"1\",\n" +
//                "            \"fireelevatorNum\":\"1\",\n" +
//                "            \"shelterfloorNum\":\"1\",\n" +
//                "            \"mapType\":\"1\",\n" +
//                "            \"lng\":\"39.9165270\",\n" +
//                "            \"lat\":\"29.3971281\",\n" +
//                "            \"createTime\":\"2023-04-05 12:00:00\",\n" +
//                "            \"updateTime\":\"2023-04-05 12:00:00\",\n" +
//                "            \"exitNum\":1\n" +
//                "        }";
//        ReceiveDeviceEntity deviceEntity = JSONObject.parseObject(data, ReceiveDeviceEntity.class);
////        ReceiveDeviceEntity receiveDeviceEntity = new ReceiveDeviceEntity();
////        receiveDeviceEntity.setDeviceName("444444");
//        deviceEntity.setId(SerialnoUtils.buildPrimaryKey());
//        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
//        Validator validator = vf.getValidator();
//        Set<ConstraintViolation<ReceiveDeviceEntity>> set = new HashSet<>();
//        set = validator.validate(deviceEntity, Default.class, Group.AddUpdateGroup.class);
//        StringBuilder errorMessage = new StringBuilder();
//        for (ConstraintViolation<ReceiveDeviceEntity> constraintViolation : set) {
//            errorMessage.append(constraintViolation.getMessage()).append(",");
//        }
//        List<ReceiveDeviceEntity> objects = new ArrayList<>();
//        objects.add(deviceEntity);
//        String data2 = "{\n" +
//                "            \"deviceId\":\"1113\",\n" +
//                "            \"deviceName\":\"测试10\",\n" +
//                "            \"parentId\":\"1200\",\n" +
//                "            \"deviceCode\":\"11\",\n" +
//                "            \"location\":\"01\",\n" +
//                "            \"deviceManufactory\":\"测试9\",\n" +
//                "            \"notifyPhone\":\"15729446523\",\n" +
//                "            \"deviceType\":\"01\",\n" +
//                "            \"code3c\":\"15729446523\",\n" +
//                "            \"qualifiedCode\":1,\n" +
//                "            \"relationType\":\"01\",\n" +
//                "            \"address\":\"sss\",\n" +
//                "            \"addressCode\":\"11111111\",\n" +
//                "            \"regionCode\":\"GBSWE1451111\",\n" +
//                "            \"buildingType\":\"01\",\n" +
//                "            \"relationId\":\"111\",\n" +
//                "            \"managerCompany\":\"1111\",\n" +
//                "            \"ownerCompany\":\"1111\",\n" +
//                "            \"useCompanys\":\"2222\",\n" +
//                "            \"parentCompanyName\":\"11\",\n" +
//                "            \"firemanagement\":\"11\",\n" +
//                "            \"economicownership\":\"111\",\n" +
//                "            \"fixedassets\":\"1.44\",\n" +
//                "            \"occaupyArea\":\"11\",\n" +
//                "            \"buildArea\":\"11\",\n" +
//                "            \"produceDate\":\"2023-04-05 12:00:00\",\n" +
//                "            \"installDate\":\"2023-04-05 12:00:00\",\n" +
//                "             \"expireDate\":\"2023-04-05 12:00:00\",\n" +
//                "            \"partsNum\":\"1\",\n" +
//                "            \"fireelevatorNum\":\"1\",\n" +
//                "            \"shelterfloorNum\":\"1\",\n" +
//                "            \"mapType\":\"1\",\n" +
//                "            \"lng\":\"39.9165270\",\n" +
//                "            \"lat\":\"29.3971281\",\n" +
//                "            \"createTime\":\"2023-04-05 12:00:00\",\n" +
//                "            \"updateTime\":\"2023-04-05 12:00:00\",\n" +
//                "            \"exitNum\":1\n" +
//                "        }";
//        ReceiveDeviceEntity deviceEntity2 = JSONObject.parseObject(data2, ReceiveDeviceEntity.class);
//        deviceEntity2.setId(SerialnoUtils.buildPrimaryKey());
//        objects.add(deviceEntity2);
////        receiveDeviceMapper.insertOrUpdateBatchList(objects);
//        receiveDeviceService.saveBatch(objects);
//        log.info("校验结果：{}", errorMessage);
//    }
//
//    /**
//     * 坐标系转换
//     */
//    @Test
//    public void coordinateSystemConversion() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        LambdaQueryWrapper<SysCameraResourcesEntity> wrapper = new LambdaQueryWrapper<SysCameraResourcesEntity>();
//        List<SysCameraResourcesEntity> selected = sysCameraResourcesService.getBaseMapper()
//                .selectList(
//                        wrapper.isNotNull(SysCameraResourcesEntity::getLatitude)
//                                .isNotNull(SysCameraResourcesEntity::getLongitude)
//                                .ne(SysCameraResourcesEntity::getLatitude, "")
//                                .ne(SysCameraResourcesEntity::getLongitude, "")
//                );
//        selected.stream().forEach(date -> {
//            log.info("lat:{}  lng:{}", date.getLatitude(), date.getLongitude());
//            Gps toGps84 = PositionUtil.gcj02_To_Gps84(Double.valueOf(date.getLatitude()), Double.valueOf(date.getLongitude()));
//            date.setLongitude(String.valueOf(toGps84.getWgLon()));
//            date.setLatitude(String.valueOf(toGps84.getWgLat()));
//        });
//        stopWatch.stop();
//        System.out.println("时间：" + stopWatch.prettyPrint());
//    }
//
//    /**
//     * 时空云建筑数据存入
//     */
//    @SneakyThrows
//    @Test
//    public void getCloudBuildingData() {
//        //CD5F964C42ADEC2C    197   //65213EE57DC61CD6   103
//        //ED6C0FF6EFC7D1E2    169   //
//        BaseMapper<SkyCommunityResourceEntity> baseMapper = skyCommunityResourceService.getBaseMapper();
//        List<SkyCommunityResourceEntity> selected = baseMapper.selectList(new LambdaQueryWrapper<SkyCommunityResourceEntity>().eq(SkyCommunityResourceEntity::getResourceType, "分层分户"));
//        for (SkyCommunityResourceEntity data : selected) {
//            Map<String, Object> treeMap = new TreeMap<>();
//            treeMap.put("resourceCode", data.getResourceCode());
//            treeMap.put("pageNum", "1");
//            treeMap.put("pageSize", "100");
//            while (true) {
//                Integer pageNum = Integer.valueOf(treeMap.get("pageNum").toString());
//                TimeUnit.SECONDS.sleep(4L);
//                JSONObject jsonObject = getRequestData(chronosConfig.getBuildUrl(), treeMap);
//                log.info("pageNum:{}-----------pageSize:{}-------total:{}---------code:{}", treeMap.get("pageNum"), treeMap.get("pageSize"), jsonObject.get("total"), data.getResourceCode());
//                List<SkyBuildEntity> entities = JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("rows")), SkyBuildEntity.class);
//                entities.forEach(entity -> entity.setRemark(data.getResourceCode()));
//                skyBuildService.saveOrUpdateBatch(entities);
//                if ((pageNum * Integer.valueOf(treeMap.get("pageSize").toString())) >= Integer.valueOf(jsonObject.get("total").toString())) {
//                    break;
//                }
//                pageNum++;
//                treeMap.put("pageNum", String.valueOf(pageNum));
//            }
//        }
//    }
//
//    /**
//     * 通过地址去请求数据
//     */
//    public JSONObject getRequestData(String url, Map<String, Object> map) {
//        Long timeStamp = System.currentTimeMillis() / 1000;
//        String requestParameters = "";
//        if (map != null && !map.isEmpty()) {
//            for (Map.Entry<String, Object> kv : map.entrySet()) {
//                requestParameters += "&" + kv.getKey() + "=" + kv.getValue();
//            }
//        }
//        if (StringUtils.isNoneBlank(requestParameters)) {
//            requestParameters = RegExUtils.replaceFirst(requestParameters, "&", "");
//        }
//        String sha1Base64 = genHMAC(chronosConfig.getAk() + "/" + requestParameters + "/" + timeStamp, chronosConfig.getSk());
//        return getRequestResults(url + "?" + requestParameters, chronosConfig.getAk(), String.valueOf(timeStamp), sha1Base64);
//    }
//
//    /**
//     * 获取结果请求
//     */
//    @SneakyThrows
//    public JSONObject getRequestResults(String url, String ak, String timeStamp, String sk) {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .method("GET", null)
//                .addHeader("pop-AppKey", ak)
//                .addHeader("pop-TimeStamp", timeStamp)
//                .addHeader("pop-Signature", sk)
//                .build();
//        Response response = client.newCall(request).execute();
//        return JSONObject.parseObject(response.body().string());
//    }
//
//    @SneakyThrows
//    public String genHMAC(String data, String key) {
//        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
//        SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
//        //生成一个指定 Mac 算法 的 Mac 对象
//        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
//        //用给定密钥初始化 Mac 对象
//        mac.init(signinKey);
//        //完成 Mac 操作
//        byte[] rawHmac = mac.doFinal(data.getBytes());
//        return Base64.getEncoder().encodeToString(rawHmac);
//    }
//
//
//    /**
//     * 错误数据新增
//     */
//    @Test
//    public void saveErrorDataAddition() {
//        String a = "{\"lists\":[{\"companyCode\":\"12330227MB1720245K\",\"address\":\"浙江省宁波市鄞州区下应街道宁波市鄞州蓝青小学\",\"lng\":\"121.5834170\",\"companyType\":\"1\",\"companyName\":\"宁波市鄞州蓝青小学\",\"fireManager\":\" 殷洪文\",\"companyCategory\":\"01\",\"fireLiableTel\":\"13605740688\",\"mapType\":1,\"updateTime\":\"2023-07-10 17:36:05\",\"parentId\":\"913302826747269759\",\"companyId\":\"2514\",\"regionCode\":\"330212006000\",\"industryType\":\"P83\",\"createTime\":\"2023-07-10 17:36:05\",\"fireLiable\":\"柳成亚\",\"fireManagerTel\":\"15314596136\",\"lat\":\"29.8067000\"}],\"optType\":\"0\"}";
//        JSONObject jsonObject = (JSONObject) JSONObject.parse(a);
//        List<ReceiveCompanyEntity> faultList = JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("lists")), ReceiveCompanyEntity.class);
//        companyService.insertOrUpdateBatchList(faultList);
//        System.out.println("11");
//    }
//
//    /**
//     * 错误新增
//     */
//    @Test
//    public void deviceStateAdd() {
//        String a = "{\"lists\":[{\"workStatus\":1,\"eventId\":\"1678312371770298370-xks\",\"deviceCategory\":2,\"onlineStatus\":1,\"eventTime\":\"2023-07-10 15:57:05\",\"deviceId\":\"1673507994127749121-xks\",\"parentId\":\"913302826747269759\"}],\"optType\":\"0\"}";
//        JSONObject jsonObject = (JSONObject) JSONObject.parse(a);
//        List<ReceiveDeviceStateEntity> faultList = JSON.parseArray(JSON.toJSONString(jsonObject.get("lists")), ReceiveDeviceStateEntity.class);
//        receiveDeviceStateService.saveBatch(faultList);
//    }
//
//    @Test
//    public void newEvaluationScore() {
//        xfxypjCreditEvaluationService.newEvaluationScore();
//    }
//
//    @Test
//    public void testYZCockpit() {
//        yzCockpitService.overallSituation();
//    }
//}
