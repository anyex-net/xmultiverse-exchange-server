package com.anyex.apps.project.domain;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.anyex.apps.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

/**
 * 代码生成业务字段表 gen_table_column
 *
 * @author ruoyi
 * @date 2022/3/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GenTableColumn extends BaseEntity {
    public static final String REGEX = " ";
    public static final int TWO = 2;
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    @TableId(value = "column_id")
    private Long columnId;

    /**
     * 归属表编号
     */
    private Long tableId;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 列描述
     */
    private String columnComment;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * JAVA类型(字典:code_java_type)
     */
    private String javaType;

    /**
     * JAVA字段名
     */
    @NotBlank(message = "Java属性不能为空")
    private String javaField;

    /**
     * 是否主键(0=是,1=否 字典:sys_yes_no)
     */
    private Integer isPk;

    /**
     * 是否自增(0=是,1=否 字典:sys_yes_no)
     */
    private Integer isIncrement;

    /**
     * 是否必填(0=是,1=否 字典:sys_yes_no)
     */
    private Integer isRequired;

    /**
     * 是否为插入字段(0=是,1=否 字典:sys_yes_no)
     */
    private Integer isInsert;

    /**
     * 是否编辑字段(0=是,1=否 字典:sys_yes_no)
     */
    private Integer isEdit;

    /**
     * 是否列表字段(0=是,1=否 字典:sys_yes_no)
     */
    private Integer isList;

    /**
     * 是否查询字段(0=是,1=否 字典:sys_yes_no)
     */
    private Integer isQuery;

    /**
     * 查询方式(EQ=等于,NE=不等于,GT=大于,GTE=大于等于,LT=小于,LTE=小于等于,LIKE=模糊,BETWEEN=范围 字典:code_query_type)
     */
    private String queryType;

    /**
     * 显示类型(input=文本框,textarea=文本域,select=下拉框,checkbox=复选框,radio=单选框,datetime=日期控件,image=图片上传控件,upload=文件上传控件,editor=富文本控件 字典:code_html_type)
     */
    private String htmlType;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 排序
     */
    private Integer sort;

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField,
                // CommonEntity
                "createBy", "createTime", "updateBy", "updateTime", "deleteFlag", "remark",
                // TreeEntity
                "parentId", "orderNum", "ancestors");
    }

    public static boolean isUsableColumn(String javaField) {
        // isSuperColumn()中的名单用于避免生成多余Domain属性，若某些属性在生成页面时需要用到不能忽略，则放在此处白名单
        return StringUtils.equalsAnyIgnoreCase(javaField, "parentId", "orderNum", "remark");
    }

    public String getCapJavaField() {
        return StringUtils.capitalize(javaField);
    }

    public boolean isPk() {
        return isPk(this.isPk);
    }

    public boolean isPk(Integer isPk) {
        return isPk != null && isPk == 0;
    }

    public boolean isIncrement() {
        return isIncrement(this.isIncrement);
    }

    public boolean isIncrement(Integer isIncrement) {
        return isIncrement != null && isIncrement == 0;
    }

    public boolean isRequired() {
        return isRequired(this.isRequired);
    }

    public boolean isRequired(Integer isRequired) {
        return isRequired != null && isRequired == 0;
    }

    public boolean isInsert() {
        return isInsert(this.isInsert);
    }

    public boolean isInsert(Integer isInsert) {
        return isInsert != null && isInsert == 0;
    }

    public boolean isEdit() {
        return isInsert(this.isEdit);
    }

    public boolean isEdit(Integer isEdit) {
        return isEdit != null && isEdit == 0;
    }

    public boolean isList() {
        return isList(this.isList);
    }

    public boolean isList(Integer isList) {
        return isList != null && isList == 0;
    }

    public boolean isQuery() {
        return isQuery(this.isQuery);
    }

    public boolean isQuery(Integer isQuery) {
        return isQuery != null && isQuery == 0;
    }

    public boolean notSuperColumn() {
        return !isSuperColumn(this.javaField);
    }

    public boolean isUsableColumn() {
        return isUsableColumn(javaField);
    }

    /**
     * 获取数据库字段描述替换 格式：([key]=[value],[key]=[value])
     *
     * @return 替换字段
     */
    public String readConverterExp() {
        String remarks = StringUtils.substringBetween(this.columnComment, "(", ")");
        if (StringUtils.isNotEmpty(remarks)) {
            String[] values = remarks.split(REGEX);
            if (CollectionUtil.isEmpty(Arrays.asList(values))) {
                return this.columnComment;
            }
            return values[0];
        } else {
            return this.columnComment;
        }
    }

    /**
     * 获取数据库字段替换字典类型 格式：([key]=[value],[key]=[value] 字典:[dictType])
     *
     * @return 字典类型
     */
    public String readDictType() {
        String remarks = StringUtils.substringBetween(this.columnComment, "(", ")");
        if (StringUtils.isNotEmpty(remarks)) {
            String[] values = remarks.split(REGEX);
            if (CollectionUtil.isEmpty(Arrays.asList(values))) {
                return "";
            } else if (values.length >= TWO) {
                return " " + values[1];
            } else {
                return "";
            }
        } else {
            return "";
        }
    }


}