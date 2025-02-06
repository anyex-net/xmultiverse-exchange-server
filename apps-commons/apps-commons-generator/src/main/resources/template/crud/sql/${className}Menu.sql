<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>


INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (请自行修改id, 父id, '${subpackage}:${classNameLower}:index', '${table.sqlRemark}', null, false, 'fi-results-demographics', 6, '${classNameLower}', '/${subpackage}/${classNameLower}', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (请自行修改id+1, 请自行修改id, '${subpackage}:${classNameLower}:operator', '操作权限', null, true, null, null, '${classNameLower}', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (请自行修改id+2, 请自行修改id, '${subpackage}:${classNameLower}:data', '查询权限', null, true, null, null, '${classNameLower}', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
