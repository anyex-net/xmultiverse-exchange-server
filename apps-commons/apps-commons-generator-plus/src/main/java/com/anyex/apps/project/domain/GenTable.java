package com.anyex.apps.project.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.anyex.apps.common.constant.GenConstants;
import com.anyex.apps.common.utils.StringUtils;
import com.anyex.apps.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 业务表 gen_table
 *
 * @author ruoyi
 * @date 2022/3/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GenTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 数据源主键
     */
    private Long dataSourceId;

    /**
     * 数据源类型
     */
    private String dbType;

    /**
     * 编号
     */
    @TableId(value = "table_id")
    private Long tableId;

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    private String tableComment;

    /**
     * 实体类名称
     */
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    private String classNameLower;

    public String getClassNameLower() {
        return StringUtils.capitalize(className);
    }

    /**
     * 使用的模板(crud=单表操作,tree=树表操作 字典:code_template_category)
     */
    private String tplCategory;

    /**
     * 是否区分出入参(0=是,1=否 字典:sys_yes_no)
     */
    private Integer isSplit;

    /**
     * 是否需要前端(0=是,1=否)
     */
    private Integer needFront;

    /**
     * 前端类型(0=html,1=vue2,2=vue3)
     */
    private Integer frontType;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 生成模块名
     */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    /**
     * 生成业务名
     */
    @NotBlank(message = "生成业务名不能为空")
    private String businessName;

    /**
     * 生成功能名
     */
    @NotBlank(message = "生成功能名不能为空")
    private String functionName;

    /**
     * 生成作者
     */
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /**
     * 生成代码方式(0=zip压缩包,1=自定义路径 字典:sys_generator_type)
     */
    private Integer genType;

    /**
     * 生成路径(不填默认项目路径)
     */
    private String genPath;

    /**
     * 主键信息
     */
    @TableField(exist = false)
    private GenTableColumn pkColumn;

    /**
     * 表列信息
     */
    @Valid
    @TableField(exist = false)
    private List<GenTableColumn> columns;

    /**
     * 其它生成选项
     */
    private String options;

    /**
     * 树编码字段
     */
    @TableField(exist = false)
    private String treeCode;

    /**
     * 树父编码字段
     */
    @TableField(exist = false)
    private String treeParentCode;

    /**
     * 树名称字段
     */
    @TableField(exist = false)
    private String treeName;

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

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public static boolean isSplit(Integer isSplit) {
        return isSplit != null && isSplit == 0;
    }

    public static boolean needFront(Integer needFront) {
        return needFront != null && needFront == 0;
    }

    public static boolean isNeedTableName(String tableName, String className) {
        if (tableName != null && className != null) {
            return !StringUtils.convertToCamelCase(tableName).equals(className);
        }
        return true;
    }

    public static boolean isSuperColumn(String tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.TREE_ENTITY);
        } else {
            return false;
        }
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public boolean isSplit() {
        return isSplit(this.isSplit);
    }

    public boolean needFront() {
        return needFront(this.needFront);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public boolean isNeedTableName() {
        return isNeedTableName(this.tableName, this.className);
    }
}