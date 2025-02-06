package com.anyex.apps.project.domain;

import com.anyex.apps.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 系统数据源配置表 sys_config
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDataSource extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 数据源主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * oracle连接方式
     */
    private String oracleConnMode;

    /**
     * oracle连接服务名或SID
     */
    private String serviceNameOrSid;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 模式
     */
    private String schemaName = "public";

    /**
     * 状态(0正常 1停用)
     */
    private String status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 备注
     */
    private String remark;
}