//package com.anyex.apps.system;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.location.entity.SysVehicleEntity;
//import com.anyex.apps.location.entity.VideoAreaEntity;
//import com.anyex.apps.location.entity.VideoAreaYzddEntity;
//import com.anyex.apps.location.mapper.VideoAreaMapper;
//import com.anyex.apps.location.service.SysVehicleService;
//import com.anyex.apps.location.service.VideoAreaYzddService;
//import com.anyex.apps.xftdzl.utils.VideoArtemisHttpUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @Author
// * @Date 2023/8/7 14:12
// */
//@Slf4j
//public class YzddGetCarAll extends BaseServiceImplTest {
//
//    @Resource
//    private VideoAreaMapper videoAreaMapper;
//    @Resource
//    private VideoAreaYzddService videoAreaYzddService;
//
//    @Resource
//    private SysVehicleService sysVehicleService;
//
//    /**
//     * 鄞州支队获取车辆列表
//     */
//    @Test
//    public void getYzddCarAll() {
//        List<String> indexCodes = videoAreaYzddService.getBaseMapper().selectList(null).stream().map(VideoAreaYzddEntity::getIndexCode).collect(Collectors.toList());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("pageNo", 1);
//        jsonObject.put("pageSize", 5000);
//        jsonObject.put("indexCodes", indexCodes);
//        JSONObject carList = VideoArtemisHttpUtil.getCarList(jsonObject);
//        System.out.println("111111");
//        JSONObject jsonObject2 = JSONObject.parseObject(carList.getString("data"));
//        List<SysVehicleEntity> jsonArray = JSONArray.parseArray(jsonObject2.getString("list"), SysVehicleEntity.class);
//        sysVehicleService.saveBatch(jsonArray);
//    }
//
//    /**
//     * 获取区域树
//     */
//    @Test
//    public void getAreaTree() {
//        List<VideoAreaYzddEntity> childArea = getChildArea("cb890ea96deb4b829015be24c017a3d7", new ArrayList<VideoAreaYzddEntity>());
//        videoAreaYzddService.saveBatch(childArea);
//    }
//
//    public List<VideoAreaYzddEntity> getChildArea(String indexCode, List<VideoAreaYzddEntity> list) {
//        JSONObject jsonObject1 = new JSONObject();
////        jsonObject1.put("pageNo",1);
////        jsonObject1.put("pageSize",5000);
//        jsonObject1.put("treeCode", 0);
//        jsonObject1.put("parentIndexCode", indexCode);
//        JSONObject jsonObject = VideoArtemisHttpUtil.publicHkInterface(jsonObject1, "/api/resource/v1/regions/subRegions");
//        JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.getString("data"));
//        List<VideoAreaYzddEntity> jsonArray = JSONArray.parseArray(jsonObject2.getString("list"), VideoAreaYzddEntity.class);
//        log.info("结果:{}", JSONObject.toJSONString(jsonArray));
////        if (CollectionUtils.isNotEmpty(jsonArray)){
////            jsonArray.forEach(data->{
////                getChildArea(data.getIndexCode(),list);
////            });
////            list.addAll(jsonArray);
////        }
//        list.addAll(jsonArray);
//        return list;
//    }
//
//
//    /**
//     * 鄞州区区域设备
//     */
//    @Test
//    public void regionalEquipmentInYinzhouDistrict() {
//        List<VideoAreaEntity> videoAreaEntities = videoAreaMapper.selectList(null);
//        VideoAreaEntity videoAreaEntity = videoAreaMapper.selectById(1674007258621431809L);
//        getChildDistrict(videoAreaEntity,videoAreaEntities);
//        videoAreaEntities.forEach(data->{
//            videoAreaMapper.updateById(data);
//        });
//    }
//
//    public void getChildDistrict(VideoAreaEntity entity, List<VideoAreaEntity> entities) {
//        List<VideoAreaEntity> collect = entities.stream().filter(a -> a.getParentIndexCode().equals(entity.getIndexCode())).collect(Collectors.toList());
//        collect.forEach(date -> {
//                    date.setParentNames(entity.getParentNames() + "/" + date.getName());
//                    getChildDistrict(date, entities);
//       });
//    }
//
//}
