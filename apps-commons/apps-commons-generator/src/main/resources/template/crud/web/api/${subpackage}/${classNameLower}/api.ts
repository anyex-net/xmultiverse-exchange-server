<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
import request from "@/utils/request";

/**
 * 查询${table.sqlRemark}
 *
 * @param {object} query 参数Obj
 * @returns
 */
export const list${className} = async (query: any) => {
    return await request({
        url: "/api/${subpackage}/${classNameLower}/data",
        method: "post",
        data: query,
    });
};

/**
 * 查询${table.sqlRemark}详细
 *
 * @param {number} id 参数ID
 * @returns
 */
export const get${className} = async (id: number) => {
    return await request({
        url: "/api/${subpackage}/${classNameLower}/findBy?id=" + id,
        method: "get",
    });
};

/**
 * 新增修改${table.sqlRemark}
 *
 * @param {object} param 参数Obj
 * @returns
 */
export const add${className} = async (param: any) => {
    return await request({
        url: "/api/${subpackage}/${classNameLower}/save",
        method: "post",
        data: param,
    });
};

/**
 * 删除${table.sqlRemark}
 *
 * @param {string} data 参数ID
 * @returns
 */
export const del${className} = async (data:any) => {
    return await request({
        url: "/api/${subpackage}/${classNameLower}/del",
        method: "post",
        data:data
    });
};

/**
 * 所有下拉${table.sqlRemark}
 *
 * @param {object} query 参数Obj
 * @returns
 */
export const all${className} = async (query: any) => {
    return await request({
        url: "/api/${subpackage}/${classNameLower}/all",
        method: "post",
        data: query,
    });
};

