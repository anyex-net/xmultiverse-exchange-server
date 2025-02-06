<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
import { ref, reactive, toRefs, getCurrentInstance, onMounted } from "vue";
// prettier-ignore
import { list${className}, get${className}, del${className}, add${className} } from "@/api/${subpackage}/${classNameLower}/api";
import { ElForm, ElTable } from "element-plus";
import { isStrings } from "@/utils/validate";

export default () => {
    const { proxy } = getCurrentInstance() as any;
    const state = reactive({
        // 查询参数
        queryParams: {
            current: 1,
            size: 50,
            <@generateFields/>
        },
        // 遮罩层
        loading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 参数表格数据
        configList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 表单参数
        form: {
            <@generateFields/>
        },
        // 表单校验
        rules: {
            <@generateFields2/>
        },
    });
    const queryFormRef = ref<InstanceType<typeof ElForm>>();
    const formRef = ref<InstanceType<typeof ElForm>>();
    const pageTableRef = ref<InstanceType<typeof ElTable>>();
    const isShowTooltip = ref<boolean>(true);

    const {
        queryParams,
        loading,
        ids,
        single,
        multiple,
        showSearch,
        total,
        configList,
        title,
        open,
        form,
        rules,
    } = toRefs(state);

    /** 查询列表 */
    const getList = () => {
        for (let key in queryParams.value) {
            if (queryParams.value.hasOwnProperty(key)) {
                if (queryParams.value[key] === "") {
                    queryParams.value[key] = null;
                }
            }
        }
        loading.value = true;
        // prettier-ignore
        list${className}(queryParams.value).then((response) => {
            if (response.code === 200) {
                configList.value = response.data.records;
                total.value = response.data.total;
                loading.value = false;
            }

        });
    };
    const cleanSelect = () => {
        pageTableRef.value?.clearSelection();
    };
    // 取消按钮
    const cancel = () => {
        open.value = false;
        reset();
        cleanSelect();
    };
    // 表单重置
    const reset = () => {
        form.value = {
            <@generateFields/>
        };
        proxy.resetForm(formRef);
    };
    /** 搜索按钮操作 */
    const handleQuery = () => {
        queryParams.value.current = 1;
        getList();
    };
    /** 重置按钮操作 */
    const resetQuery = () => {
        proxy.resetForm(queryFormRef);
        handleQuery();
    };
    /** 新增按钮操作 */
    const handleAdd = () => {
        reset();
        title.value = "添加";
        open.value = true;
    };
    // 多选框选中数据
    const handleSelectionChange = (selection: any) => {
        ids.value = selection.map((item: any) => item.id);
        single.value = selection.length != 1;
        multiple.value = !selection.length;
    };
    /** 修改按钮操作 */
    const handleUpdate = (row: any) => {
        reset();
        const configId = row.id || ids.value;
        get${className}(configId).then((response) => {
            form.value = response.data;
            open.value = true;
            title.value = "修改";
            proxy.setTableRowSelected(pageTableRef, row, true);
        });
    };
    /** 提交按钮 */
    const submitForm = async () => {
        await formRef.value?.validate((valid: boolean) => {
            if (valid) {
                if (form.value.id != "") {
                    add${className}(form.value).then((response) => {
                        if (response.code === 200) {
                            proxy.$modal.msgSuccess("修改成功");
                            open.value = false;
                            getList();
                        }
                    });
                } else {
                    add${className}(form.value).then((response) => {
                        if (response.code === 200) {
                            proxy.$modal.msgSuccess("新增成功");
                            open.value = false;
                            getList();
                        }
                    });
                }
            }
        });
    };
    /** 删除按钮操作 */
    const handleDelete = (row: any) => {
        const configIds = row.id || ids.value;
        const query = {} as any;
        if (row.id == undefined) {
            query.value = {
                ids: isStrings(configIds),
            };
        } else {
            query.value = {
                ids: configIds,
            };
        }
        proxy.setTableRowSelected(pageTableRef, row, true);
        // prettier-ignore
        proxy.$modal.confirm('是否确认删除编号为"' + configIds + '"的数据项?', "警告")
            .then(() => {
                return del${className}(query.value);
            })
            .then((response: any) => {
                if (response.code === 200) {
                    getList();
                    proxy.$modal.msgSuccess("删除成功");
                }
            })
            .catch(() => {
                cleanSelect();
            });
    };
    const onMouseOver = (target: any) => {
        // 判断是否开启tooltip功能
        if (target.scrollWidth > target.clientWidth) {
            isShowTooltip.value = false;
        } else {
            isShowTooltip.value = true;
        }
    };
    onMounted(() => {
        getList();
    });

    // prettier-ignore
    return {
        loading, single, multiple, open, showSearch, total, configList, title, queryParams, queryFormRef, form, formRef, rules,
        getList, cancel, reset, handleQuery, resetQuery, handleAdd, handleSelectionChange,handleUpdate, submitForm, handleDelete, pageTableRef, cleanSelect,isShowTooltip, onMouseOver,
    };
};
<#macro generateFields>
<#list table.columns as column>
${column.columnNameLower}: "",  // ${column.remark}
</#list>
</#macro>

<#macro generateFields2>
<#list table.columns as column>
<#if !column.nullable>
${column.columnNameLower}: [
    {
        required: true,
        message: "${column.remark}不能为空",
        trigger: "change",
    },
],
</#if>
</#list>
</#macro>


