package com.anyex.apps.system;

import com.anyex.apps.utils.JSONUtils;
import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.system.entity.SysUserInfo;
import com.anyex.apps.system.service.SysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户服务测试类
 * note: 1.记录变更 by WangXiao
 *
 * @author WangXiao
 * @date 2023/03/24 00:02
 **/
@Slf4j
public class UserInfoServiceTest extends BaseServiceImplTest {
    @Autowired
    private SysUserInfoService userInfoService;

//    @Test
//    public void testSelectUserQuery(){
//        String roleCode="ROLE_ADMIN";
//        Long orgId = null;
//        Integer active = null;
//        List<SysUserInfo> userInfos = userInfoService.selectUserQuery(roleCode, orgId, active);
//        log.info("用户列表:{}", JSONUtils.beanToJson(userInfos));
//    }
}
