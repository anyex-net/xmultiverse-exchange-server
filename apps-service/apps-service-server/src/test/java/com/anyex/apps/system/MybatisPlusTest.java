//package com.anyex.apps.system;
//
//import com.anyex.apps.page.Condition;
//import com.anyex.apps.page.Query;
//import com.anyex.apps.utils.JSONUtils;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.common.entity.SysUnit;
//import com.anyex.apps.common.service.SysUnitService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
///**
// * @Author
// * @Date 2023/4/25 15:48
// */
//@Slf4j
//public class MybatisPlusTest extends BaseServiceImplTest {
//
//    @Autowired
//    private SysUnitService sysUnitService;
//
//    /**
//     * 测试查询列表
//     * 条件为名称
//     */
//    @Test
//    public void testSelectList() {
//        //直接用lambda测试
//        LambdaQueryWrapper<SysUnit> lambdaWrapper = new LambdaQueryWrapper<>();
//        lambdaWrapper.like(SysUnit::getName, "测试");
//        List<SysUnit> lambdaWrapperSysUnits = sysUnitService.getBaseMapper().selectList(lambdaWrapper);
//        log.info("直接用lambda测试测试用户列表:{}", JSONUtils.beanToJson(lambdaWrapperSysUnits));
//        //wrapper转Lambda测试
//        LambdaQueryWrapper<SysUnit> wrapperLambda = new QueryWrapper<SysUnit>().lambda();
//        List<SysUnit> sysUnits = sysUnitService.getBaseMapper().selectList(wrapperLambda);
//        log.info("wrapper转Lambda测试用户列表:{}", JSONUtils.beanToJson(sysUnits));
//        //wrapper测试
//        QueryWrapper<SysUnit> wrapper = new QueryWrapper<>();
//        wrapper.eq("name", "111");
//        List<SysUnit> wrapperSysUnits = sysUnitService.getBaseMapper().selectList(wrapper);
//        log.info("wrapper测试用户列表:{}", JSONUtils.beanToJson(wrapperSysUnits));
//    }
//
//    /**
//     * 测试分页
//     */
//    @Test
//    public void testSelectPage() {
//        Query query = new Query();
//        Page<SysUnit> page = sysUnitService.page(Condition.getPage(query), null);
//        log.info("分页列表:{}", JSONUtils.beanToJson(page));
//    }
//
//    /**
//     * 测试新增
//     */
//    @Test
//    public void testSave() throws RuntimeException {
//        SysUnit sysUnit = new SysUnit();
//        sysUnit.setName("999999");
//        sysUnitService.save(sysUnit);
//    }
//
//    /**
//     * 测试修改
//     */
//    @Test
//    public void testUpdate() {
//        //查询测试id
//        SysUnit unit = sysUnitService.getById("1650821481425215490");
//        //测试修改
//        unit.setName(UUID.randomUUID().toString());
//        sysUnitService.updateById(unit);
//    }
//
//    /**
//     * 批量新增
//     */
//    @Test
//    public void batchAddition() {
//        //获取批量数据
//        List<SysUnit> sysUnits = initDate();
//        //新增或者修改列表
//        sysUnitService.saveOrUpdateBatch(sysUnits);
//    }
//
//    /**
//     * 测试事务
//     */
//    @Test
//    public void testTransaction(){
//        sysUnitService.testTransactionRollback();
//    }
//
//    /**
//     * 初始化批量数据
//     */
//    public List<SysUnit> initDate() {
//        ArrayList<SysUnit> list = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            SysUnit sysUnit = new SysUnit();
//            sysUnit.setName("测试" + i);
//            list.add(sysUnit);
//        }
//        return list;
//    }
//}
