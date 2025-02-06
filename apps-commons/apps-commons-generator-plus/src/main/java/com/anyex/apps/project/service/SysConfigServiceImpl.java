package com.anyex.apps.project.service;

import com.anyex.apps.project.domain.SysConfig;
import com.anyex.apps.project.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统配置 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    @Autowired
    private SysConfigMapper mapper;

    /**
     * 查询系统配置信息
     *
     * @return 系统配置
     */
    @Override
    public SysConfig selectSysConfig() {
        return mapper.selectSysConfig();
    }

    /**
     * 更新系统配置信息
     *
     * @param sysConfig 系统配置信息
     * @return 结果
     */
    @Override
    public int updateSysConfig(SysConfig sysConfig) {
        return mapper.updateSysConfig(sysConfig);
    }
}
