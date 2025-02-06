<#include "/macro.include"/>
<#include "/copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${subpackage}.entity;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${table.sqlRemark} 实体请求对象
 * <p>File：Req${className}.java</p>
 * <p>Title: Req${className}</p>
 * <p>Description:Req${className}</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "${table.sqlRemark}请求对象")
public class Req${className} extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	<@generateFields/>

}

<#macro generateFields>
<#if table.compositeId>
	private ${className}Id id;
	<#list table.columns as column>
    <#if !column.pk >
	private ${column.javaType} ${column.columnNameLower};
		</#if>
	</#list>
<#else>
	<#list table.columns as column>
	<#if !column.pk && !column.fk && column.columnNameLower != "sign"
        && column.columnNameLower != "randomKey" && column.columnNameLower != "delFlag">
	/**${column.remark}*/
	<#if !column.nullable>
<#if column.javaType="java.lang.String">
	@NotEmpty(message = "${column.remark}不可为空")
<#else>
	@NotNull(message = "${column.remark}不可为空")
</#if>
	</#if>
	@ApiModelProperty(value = "${column.remark}", position = ${column.sort}<#if !column.nullable>, required = true</#if>)
	private ${column.javaType} ${column.columnNameLower};

	</#if>
	</#list>
</#if>
</#macro>
