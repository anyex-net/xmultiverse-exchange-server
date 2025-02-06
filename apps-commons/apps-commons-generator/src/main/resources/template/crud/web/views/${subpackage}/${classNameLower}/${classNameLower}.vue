<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<template>
  <div class="app-container">
    <el-form
        size="small"
        :model="queryParams"
        ref="queryFormRef"
        :inline="true"
        v-show="showSearch"
        label-width="70px"
    >
      <@generateFieldsSearch/>
      <form-search @reset="resetQuery()" @search="handleQuery()" />
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            size="small"
            @click="handleAdd"
            v-hasPermi="['${subpackage}:${classNameLower}:operator']"
        >新增</el-button
        >
      </el-col>

      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            size="small"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['${subpackage}:${classNameLower}:operator']"
        >删除</el-button
        >
      </el-col>
      <!-- prettier-ignore -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList()" />
    </el-row>
    <div class="self-table">
      <el-table
          size="small"
          stripe
          v-loading="loading"
          ref="pageTableRef"
          :data="configList"
          @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <@generateFieldsColumn/>
        <el-table-column
            label="操作"
            min-width="120px"
            fixed="right"
            class-name="small-padding fixed-width"
        >
          <template #default="scope">
            <el-link
                class="table_link_btn"
                :underline="false"
                type="primary"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['${subpackage}:${classNameLower}:operator']"
            ><span class="table_link_text">修改</span></el-link
            >
            <el-link
                class="table_link_btn"
                :underline="false"
                size="small"
                type="primary"
                @click="handleDelete(scope.row)"
                v-hasPermi="['${subpackage}:${classNameLower}:operator']"
            ><span class="table_link_text">删除</span></el-link
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.current"
        v-model:limit="queryParams.size"
        @pagination="getList()"
    />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog
        :title="title"
        v-model="open"
        width="500px"
        append-to-body
        @close="cleanSelect()"
    >
      <el-form
          size="small"
          ref="formRef"
          :model="form"
          :rules="rules"
          label-width="120px"
      >
        <@generateFieldsForm/>

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <!-- prettier-ignore -->
          <el-button size="small" type="primary" @click="submitForm">确 定</el-button>
          <el-button size="small" @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" name="${classNameLower}" setup>
import ${classNameLower} from "@/api/request/${subpackage}/${classNameLower}/index";
import stacky from "../../../utils/table-sticky";

const {
  getContainer,
  clearListener,
  initFixedHeader,
  updateFixedRight,
  resizeChange,
  getFixedDom,
  setFixedStyle,
  clearFixedStyle,
  headerDragend,
  scrollEvent,
  getTableXy,
  getDom,
  updateHeaderHeight,
  tablexy,
  fixedRightDom,
  fixedLeftDom,
  scrollDom,
  parentDom,
  tableWidth,
  timerList,
  tableDom,
  containerDom,
  __opened,
  parent,
  setScrollXWidth,
} = stacky();
// prettier-ignore
const {
  loading, single, multiple, open, showSearch, total, configList, title, queryParams, queryFormRef, form, formRef, rules,
  getList, cancel,handleQuery, resetQuery, handleAdd, handleSelectionChange,handleUpdate, submitForm, handleDelete, pageTableRef, cleanSelect, isShowTooltip, onMouseOver,
} = ${classNameLower}();
</script>

<#macro generateFieldsSearch>
<#list table.columns as column>
<el-form-item label="${column.remark}" prop="${column.columnNameLower}">
<el-input
    v-model="queryParams.${column.columnNameLower}"
    placeholder="请输入"
    clearable
    style="width: 240px"
    @keyup.enter.native="handleQuery()"
    @change="handleQuery()"
/>
</el-form-item>
</#list>
</#macro>

<#macro generateFieldsColumn>
<#list table.columns as column>
<el-table-column label="${column.remark}" prop="${column.columnNameLower}" min-width="150px"
><template #default="scope">
  <span>{{ scope.row.${column.columnNameLower} }}</span>
</template>
</#list>
</#macro>

<#macro generateFieldsForm>
<#list table.columns as column>
<el-table-column
    label="${column.remark}"
    prop="${column.columnNameLower}"
    min-width="120"
    fixed
/>
</#list>
</#macro>