package com.anyex.apps.project.domain;

import com.anyex.apps.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置表 sys_config
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 作者
     */
    public String author;
    /**
     * 生成模块路径
     */
    public String moduleName;
    /**
     * 生成包路径
     */
    public String packageName;
    /**
     * 自动去除表前缀，默认是false
     */
    public boolean autoRemovePre;
    /**
     * 表前缀(类名不会包含表前缀)
     */
    public String tablePrefix;
    /**
     * 配置主键
     */
    private Long configId;
}