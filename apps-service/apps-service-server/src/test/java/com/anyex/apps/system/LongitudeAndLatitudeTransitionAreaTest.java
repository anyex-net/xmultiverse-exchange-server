//package com.anyex.apps.system;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.anyex.apps.utils.Gps;
//import com.anyex.apps.utils.PositionUtil;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.common.entity.SysCameraResourcesEntity;
//import com.anyex.apps.common.entity.SysFireStationEntity;
//import com.anyex.apps.common.entity.SysHeadWaterEntity;
//import com.anyex.apps.common.service.SysCameraResourcesService;
//import com.anyex.apps.common.service.SysFireStationService;
//import com.anyex.apps.common.service.SysHeadWaterService;
//import com.anyex.apps.shhzfk.entity.ReceiveDeviceEntity;
//import com.anyex.apps.shhzfk.service.ReceiveDeviceService;
//import lombok.SneakyThrows;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @Author
// * @Date 2023/8/29 9:40
// */
//public class LongitudeAndLatitudeTransitionAreaTest extends BaseServiceImplTest {
//
//    @Resource
//    private SysHeadWaterService sysHeadWaterService;
//
//    @Resource
//    private SysCameraResourcesService sysCameraResourcesService;
//
//    @Resource
//    private SysFireStationService sysFireStationService;
//
//
//    /**
//     * 微型消防站转换
//     *
//     * @param
//     * @return
//     */
//    @Test
//    public void fireStationConversionStreet() {
//        List<SysHeadWaterEntity> sysFireStationEntities = sysHeadWaterService.getBaseMapper().selectList(null);
//        for (SysHeadWaterEntity entity : sysFireStationEntities) {
//            String position = getPosition(entity.getLng() + "," + entity.getLat());
//            entity.setRemark(position);
//        }
//        sysHeadWaterService.saveOrUpdateBatch(sysFireStationEntities);
//    }
//
//    /**
//     * 摄像头转换
//     *
//     * @param
//     * @return
//     */
//    @Test
//    public void cameraConversionStreet() throws InterruptedException {
//        List<SysCameraResourcesEntity> sysCameraResourcesEntities = sysCameraResourcesService.getBaseMapper().selectList(
//                new LambdaQueryWrapper<SysCameraResourcesEntity>()
//                        .isNull(SysCameraResourcesEntity::getAreaId)
//                        .isNotNull(SysCameraResourcesEntity::getLongitude)
//                        .isNotNull(SysCameraResourcesEntity::getLatitude)
//                        .isNull(SysCameraResourcesEntity::getRemark)
//        );
//        for (SysCameraResourcesEntity entity : sysCameraResourcesEntities) {
//            if (StringUtils.isNotBlank(entity.getLatitude()) && StringUtils.isNotBlank(entity.getLongitude())) {
//                Gps gps = PositionUtil.gps84_To_Gcj02(Double.valueOf(entity.getLatitude()), Double.valueOf(entity.getLongitude()));
//                if (gps != null) {
//                    String position = getPosition(gps.getWgLon() + "," + gps.getWgLat());
//                    entity.setRemark(position);
//                }
//                sysCameraResourcesService.updateById(entity);
//            }
//        }
//
//    }
//
//    /**
//     * 微型消防站
//     * @return
//     */
//    @Test
//    public void miniFireStation(){
//        List<SysFireStationEntity> sysFireStationEntities = sysFireStationService.getBaseMapper().selectList(
//                null);
//        for (SysFireStationEntity entity : sysFireStationEntities) {
//            String lngLat = getLngLat(entity.getAddress());
//            if (StringUtils.isNotBlank(lngLat)){
//                String[] split = lngLat.split(",");
//                entity.setLng(Double.parseDouble(split[0]));
//                entity.setLat(Double.parseDouble(split[1]));
//                System.out.println("111");
//            }
//        }
//        sysFireStationService.saveOrUpdateBatch(sysFireStationEntities);
//    }
//
//    @SneakyThrows
//    public String getPosition(String location) {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url("https://restapi.amap.com/v3/geocode/regeo?key=97d49071dc68eb78f8486b3548c12a36&location=" + location)
//                .method("GET", null)
//                .build();
//        Response response = client.newCall(request).execute();
//        JSONObject jsonObject = JSONObject.parseObject(response.body().string());
//        try {
//            if (jsonObject.getString("status").equals("1")) {
//                JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.getString("regeocode"));
//                JSONObject jsonObject2 = JSONObject.parseObject(jsonObject1.getString("addressComponent"));
//                return jsonObject2.getString("township");
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }
//
//    @SneakyThrows
//    public String getLngLat(String address){
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url("https://restapi.amap.com/v3/geocode/geo?key=97d49071dc68eb78f8486b3548c12a36&city=宁波&address="+address)
//                .method("GET", null)
//                .build();
//        Response response = client.newCall(request).execute();
//        JSONObject jsonObject = JSONObject.parseObject(response.body().string());
//        try {
//            if (jsonObject.getString("status").equals("1")) {
//                JSONArray jsonObject1 = JSONArray.parseArray(jsonObject.getString("geocodes"));
//                JSONObject jsonObject2 = JSONObject.parseObject(JSONObject.toJSONString(jsonObject1.get(0)));
//                return jsonObject2.getString("location");
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }
//
//    @Resource
//    ReceiveDeviceService receiveDeviceService;
//
//    @Test
//    public void tesReceiveDevice(){
//        String aa="{\"lists\":[{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5344980\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206062753739\",\"deviceId\":\"1679465219593768961-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 12:00:14\",\"location\":\"仪门路45一1\",\"lat\":\"29.7676120\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5337980\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060383943\",\"deviceId\":\"1679615312829517826-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 21:56:38\",\"location\":\"花墙门2\",\"lat\":\"29.7692150\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5334490\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060384156\",\"deviceId\":\"1679618152251691010-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 22:07:55\",\"location\":\"五房弄29\",\"lat\":\"29.7697730\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5323990\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060384107\",\"deviceId\":\"1679620197360766978-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 22:16:03\",\"location\":\"高塘田111一5\",\"lat\":\"29.7706410\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5336120\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060384271\",\"deviceId\":\"1679624306088382466-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 22:32:22\",\"location\":\"里下新屋9一3\",\"lat\":\"29.7698880\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5339310\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382697\",\"deviceId\":\"1679626553438408705-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 22:41:18\",\"location\":\"里下新屋8\",\"lat\":\"29.7700350\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"大华\",\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"浙江省宁波市鄞州区白鹤街道向阳渔港(彩江店)彩江大厦\",\"relationId\":\"2254\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"20035\",\"deviceId\":\"1635823159859105793-xks\",\"deviceName\":\"飞歌汇展酒店\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5342700\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206062755189\",\"deviceId\":\"1679634086701993986-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 23:11:14\",\"location\":\"外下新屋24\",\"lat\":\"29.7702000\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5343260\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382648\",\"deviceId\":\"1679635297442369537-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 23:16:03\",\"location\":\"外下新屋24\",\"lat\":\"29.7701810\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"蓝天\",\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"浙江省宁波市鄞州区下应街道宁波市鄞州蓝青小学\",\"relationId\":\"2514\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"20125\",\"deviceId\":\"1635823179568140290-xks\",\"deviceName\":\"蓝青小学\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5344600\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382531\",\"deviceId\":\"1679637099533471746-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 23:23:13\",\"location\":\"外下新屋30号\",\"lat\":\"29.7700560\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5344600\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060381582\",\"deviceId\":\"1679637917477277697-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-13 23:26:28\",\"location\":\"外下新屋30号2\",\"lat\":\"29.7700560\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5326370\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382424\",\"deviceId\":\"1679647381743898625-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-14 00:04:04\",\"location\":\"老六房6\",\"lat\":\"29.7683620\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"蓝天\",\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"浙江省宁波市鄞州区下应街道启华未来青少年活动中心下应悦邻汇\",\"relationId\":\"2652\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"20177\",\"deviceId\":\"1635823221997719554-xks\",\"deviceName\":\"下应悦邻汇\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5339190\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382101\",\"deviceId\":\"1680336787735744513-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 21:43:29\",\"location\":\"里下新屋10一2\",\"lat\":\"29.7700150\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5337060\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060383794\",\"deviceId\":\"1680338080688672769-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 21:48:38\",\"location\":\"里下新屋1\",\"lat\":\"29.7698790\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5336020\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060383778\",\"deviceId\":\"1680339252401049601-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 21:53:17\",\"location\":\"里下新屋3\",\"lat\":\"29.7698590\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5345520\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382002\",\"deviceId\":\"1680341621889208322-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 22:02:42\",\"location\":\"外下新屋36号\",\"lat\":\"29.7700370\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5345400\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206062755122\",\"deviceId\":\"1680343586908049409-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 22:10:30\",\"location\":\"外下新屋44号旁边\",\"lat\":\"29.7697710\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5346880\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206063081668\",\"deviceId\":\"1680345072807350274-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 22:16:25\",\"location\":\"水沧路21一1\",\"lat\":\"29.7697710\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"蓝天\",\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"宁波市鄞州区宁南北路与嵩江中路交叉口50米\",\"relationId\":\"2713\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"20211\",\"deviceId\":\"1635823228146569217-xks\",\"deviceName\":\"铂宸府\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5346410\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206062755346\",\"deviceId\":\"1680347961856528385-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 22:27:54\",\"location\":\"水沧路21\",\"lat\":\"29.7697070\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5346780\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060381558\",\"deviceId\":\"1680349992923074561-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 22:35:58\",\"location\":\"下新屋44号旁边一1\",\"lat\":\"29.7697230\"},{\"deviceType\":14,\"relationType\":1,\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"上海市闵行区华漕镇万博家园\",\"relationId\":\"1638102060882944002\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceId\":\"1693883450697842689-water\",\"deviceName\":\"测试液位\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5343840\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382416\",\"deviceId\":\"1680352048576630786-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 22:44:08\",\"location\":\"水沧路15\",\"lat\":\"29.7695090\"},{\"deviceType\":16,\"relationType\":1,\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"上海市闵行区华漕镇北华路244号万博家园\",\"relationId\":\"1638102060882944002\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceId\":\"1693906402176794626-water\",\"deviceName\":\"测试压力\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5346720\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206062756211\",\"deviceId\":\"1680353726918987778-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 22:50:48\",\"location\":\"外下新屋35\",\"lat\":\"29.7700780\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5348440\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382853\",\"deviceId\":\"1680356502336081921-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:01:50\",\"location\":\"老水沧41\",\"lat\":\"29.7690920\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5352010\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206062755668\",\"deviceId\":\"1680358628302950402-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:10:17\",\"location\":\"荷花桥7\",\"lat\":\"29.7690090\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5352010\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382721\",\"deviceId\":\"1680359447442132994-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:13:32\",\"location\":\"荷花桥7号2\",\"lat\":\"29.7690090\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5350740\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206062753788\",\"deviceId\":\"1680361547614031874-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:21:53\",\"location\":\"新庙弄34号\",\"lat\":\"29.7682200\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5350510\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060383968\",\"deviceId\":\"1680362399514923010-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:25:16\",\"location\":\"新庙弄34\",\"lat\":\"29.7682200\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5353560\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060381137\",\"deviceId\":\"1680364282996494338-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:32:45\",\"location\":\"大道头19号\",\"lat\":\"29.7682100\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5356370\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206062756260\",\"deviceId\":\"1680365444923559938-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:37:22\",\"location\":\"大道头11一4\",\"lat\":\"29.7680500\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5357960\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060383752\",\"deviceId\":\"1680366835956092929-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:42:53\",\"location\":\"大道头11一2\",\"lat\":\"29.7682900\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5357640\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060384297\",\"deviceId\":\"1680369082593087489-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:51:49\",\"location\":\"大道头11一3\",\"lat\":\"29.7682450\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5357130\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060381467\",\"deviceId\":\"1680370643197136897-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-15 23:58:01\",\"location\":\"大道头11一1\",\"lat\":\"29.7681350\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"蓝天\",\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"宁波市鄞州区中山东路2266号\",\"relationId\":\"588\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"42011\",\"deviceId\":\"1635823451493257217-xks\",\"deviceName\":\"东部银泰城1-3#主机\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5357130\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382390\",\"deviceId\":\"1680371486050914306-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-16 00:01:22\",\"location\":\"大道头11一5\",\"lat\":\"29.7680880\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"蓝天\",\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"宁波市鄞州区中山东路2266号\",\"relationId\":\"588\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"42012\",\"deviceId\":\"1635823563577643010-xks\",\"deviceName\":\"东部银泰城4-6#主机\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5357310\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382622\",\"deviceId\":\"1680372375218196481-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-16 00:04:54\",\"location\":\"大道头11一6\",\"lat\":\"29.7680790\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"蓝天\",\"createTime\":\"2023-08-29 14:58:16\",\"location\":\"宁波市鄞州区中山东路2266号\",\"relationId\":\"588\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"42013\",\"deviceId\":\"1635823655135105026-xks\",\"deviceName\":\"东部银泰城7-8#主机\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5356020\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382549\",\"deviceId\":\"1680373589183991810-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-16 00:09:44\",\"location\":\"大道头6组1号一9\",\"lat\":\"29.7680000\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5357120\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382515\",\"deviceId\":\"1680374951724617729-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-16 00:15:08\",\"location\":\"大道头6组1号\",\"lat\":\"29.7678790\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"海湾\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-03-20 08:29:52\",\"location\":\"翔威公寓\",\"relationId\":\"1125\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"12017\",\"deviceId\":\"1637737222017060865-xks\",\"deviceName\":\"翔威公寓\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5357120\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060382481\",\"deviceId\":\"1680376055271165953-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-16 00:19:31\",\"location\":\"大道头6组2\",\"lat\":\"29.7678790\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"蓝天\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-03-22 00:00:00\",\"location\":\"宁波智慧园1期\",\"relationId\":\"1638102060882944002\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"10000\",\"deviceId\":\"1638341147980124161-xks\",\"deviceName\":\"蓝天\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5356810\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060381418\",\"deviceId\":\"1680377504080236545-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-16 00:25:17\",\"location\":\"大道头6组3\",\"lat\":\"29.7678990\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"海湾\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-03-22 00:00:00\",\"location\":\"宁波智慧园1期\",\"relationId\":\"1638102060882944002\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"100007\",\"deviceId\":\"1638341997603508226-xks\",\"deviceName\":\"海湾\",\"parentId\":\"913302826747269759\"},{\"deviceType\":6,\"relationType\":1,\"deviceManufactory\":\"海康\",\"lng\":\"121.5356930\",\"mapType\":1,\"relationId\":\"1676755506498285569\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"869206060381277\",\"deviceId\":\"1680378508410523649-smoke\",\"deviceName\":\"智慧烟感\",\"parentId\":\"913302826747269759\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-07-16 00:29:16\",\"location\":\"大道头30一1\",\"lat\":\"29.7678850\"},{\"deviceType\":1,\"relationType\":1,\"deviceManufactory\":\"大华\",\"createTime\":\"2023-08-29 14:58:16\",\"installDate\":\"2023-03-22 00:00:00\",\"location\":\"宁波智慧园\",\"relationId\":\"1638102060882944002\",\"updateTime\":\"2023-08-29 14:58:16\",\"deviceCode\":\"10001\",\"deviceId\":\"1638342244228583426-xks\",\"deviceName\":\"大华\",\"parentId\":\"913302826747269759\"}],\"optType\":\"0\"}\n";
//        JSONObject jsonObject = JSONObject.parseObject(aa);
//        List<ReceiveDeviceEntity> entities = JSONArray.parseArray(jsonObject.getString("lists"), ReceiveDeviceEntity.class);
//        receiveDeviceService.insertOrUpdateBatchList(entities);
//    }
//}
