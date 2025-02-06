//package com.anyex.apps.system;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.common.entity.SysUnit;
//import com.anyex.apps.common.service.SysUnitService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
///**
// * @Author
// * @Date 2023/4/27 14:08
// */
//@Slf4j
//public class MybatisPlusTestExists extends BaseServiceImplTest {
//
//    @Autowired
//    private SysUnitService sysUnitService;
//
//
//    @Test
//    public void testExists() throws Exception {
//        //未设置表别名以表名为准
//        LambdaQueryWrapper<SysUnit> wrapper = new QueryWrapper<SysUnit>().lambda();
//        wrapper.apply("1=1");
//        wrapper.exists("select 1 from receive_device r where r.device_id=SYS_UNIT.id");
//        List<SysUnit> sysUnits = sysUnitService.getBaseMapper().selectList(wrapper);
//        //生成sql结果
//        //SELECT  id,name,unit_type,address,area_id,lng,lat,status,create_time,create_user,update_time,update_user,parent_id,parent_ids,is_operate,is_online,is_exam,operate_unit_id,exam_unit_id,unified_code,industry_classification,person_liable_name,person_liable_phone,administrator_name,administrator_phone,juridical_name,juridical_phone,juridical_card,unit_category,place_type,type  FROM SYS_UNIT
//        // WHERE (1=1 AND EXISTS (select 1 from receive_device r where r.device_id=SYS_UNIT.id))
//    }
//
//}
