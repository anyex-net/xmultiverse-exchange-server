//package com.anyex.apps.system;
//
//import com.alibaba.fastjson.JSONObject;
//import com.anyex.apps.model.JsonMessage;
//import com.anyex.apps.page.Query;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.chronos.entity.SkyCommunityResourceEntity;
//import com.anyex.apps.chronos.service.SkyCommunityResourceService;
//import com.anyex.apps.common.entity.SysCameraResourcesEntity;
//import com.anyex.apps.common.service.SysCameraResourcesService;
//import com.anyex.apps.xftdzl.service.HikVisionService;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.junit.Test;
//import org.springframework.util.StopWatch;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author
// * @Date 2023/6/15 13:58
// */
//@Slf4j
//public class SysCameraResourcesTest extends BaseServiceImplTest {
//    @Resource
//    private HikVisionService hikVisionService;
//
//    @Resource
//    private SysCameraResourcesService sysCameraResourcesService;
//
//    @Resource
//    private SkyCommunityResourceService skyCommunityResourceService;
//
//    @Test
//    public void sysCameraResourcesTest() {
//        StopWatch sw = new StopWatch("time");
//        Query query = new Query();
//        query.setPageSize(5000);
//        sw.start();
//        for (int i = 1; i <= 7; i++) {
//            query.setPageNo(i);
//            JsonMessage videoResources = hikVisionService.getVideoResources(query);
//            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(videoResources.getData()));
//            JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("data")));
//            List<SysCameraResourcesEntity> list = JSONObject.parseArray(JSONObject.toJSONString(jsonObject1.get("list")), SysCameraResourcesEntity.class);
//            sysCameraResourcesService.saveBatch(list);
//        }
//        sw.stop();
//        System.out.println(sw.prettyPrint());
//
//    }
//
//    /**
//     * 插入资源码
//     */
//    @Test
//    public void setResourceKey(){
//        BaseMapper<SkyCommunityResourceEntity> baseMapper = skyCommunityResourceService.getBaseMapper();
//        List<SkyCommunityResourceEntity> selected = baseMapper.selectList(null);
//        selected.forEach(data->{
//            try {
//                TimeUnit.SECONDS.sleep(2L);
//                String requestResourceKey = getRequestResourceKey(data.getResourceKey());
//                JSONObject jsonObject = JSONObject.parseObject(requestResourceKey);
//                data.setResourceCode(jsonObject.getString("data"));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        System.out.println("111");
//        skyCommunityResourceService.updateBatchById(selected);
//    }
//
//    @SneakyThrows
//    public String getRequestResourceKey(String resourceKey){
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url("http://10.34.130.149:9017/api/resource/getResourceCode?resourceKey="+resourceKey)
//                .method("GET", null)
//                .addHeader("Token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvckFkbWluIjowLCJuYW1lIjoi5bKR6Zuq5r-bIiwiaWQiOjE1NzMsImV4cCI6MTY4OTc2Mjg4MCwiYWNjb3VudCI6ImNlbnh1ZW1lbmciLCJvcmdJZCI6NjUyfQ.exoP-_XLQP4H5CpaH1dE-rHuh2MRcD7xhq8KHEZLORo")
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }
//}
