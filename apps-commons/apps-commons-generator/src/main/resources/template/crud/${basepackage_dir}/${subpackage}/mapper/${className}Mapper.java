<#include "/macro.include"/>
<#include "/copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${subpackage}.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import ${basepackage}.${subpackage}.entity.${className};

/**
 * ${table.sqlRemark} 持久层接口
 * <p>File：${className}Mapper.java </p>
 * <p>Title: ${className}Mapper </p>
 * <p>Description:${className}Mapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ${className}Mapper extends GenericMapper<${className}>
{

}
