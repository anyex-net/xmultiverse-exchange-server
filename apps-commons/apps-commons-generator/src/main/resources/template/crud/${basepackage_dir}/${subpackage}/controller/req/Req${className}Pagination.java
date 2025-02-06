<#include "/macro.include"/>
<#include "/copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${subpackage}.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${table.sqlRemark} 分页请求对象
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
@ApiModel(description = "${table.sqlRemark}分页请求对象")
public class Req${className}Pagination extends Pagination
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
	</#if>
	@ApiModelProperty(value = "${column.remark}", position = ${column.sort})
	private ${column.javaType} ${column.columnNameLower};

	</#if>
	</#list>
</#if>
</#macro>
