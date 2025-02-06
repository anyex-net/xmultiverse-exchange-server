package com.anyex.apps.project.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.common.constant.GenConstants;
import com.anyex.apps.common.utils.DateUtils;
import com.anyex.apps.common.utils.StringUtils;
import com.anyex.apps.project.domain.GenTable;
import com.anyex.apps.project.domain.GenTableColumn;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 模板处理工具类
 *
 * @author ruoyi
 * @date 2022/3/29
 */
public class VelocityUtils {
    public static final String DETAIL_DTO_JAVA_VM = "detailDto.java.vm";
    public static final String PAGE_DTO_JAVA_VM = "pageDto.java.vm";
    public static final String PARAMS_JAVA_VM = "params.java.vm";
    public static final String PAGE_PARAMS_JAVA_VM = "pageParams.java.vm";
    public static final String DOMAIN_JAVA_VM = "domain.java.vm";
    public static final String CONVERT_MAPPER_JAVA_VM = "convertMapper.java.vm";
    public static final String MAPPER_JAVA_VM = "mapper.java.vm";
    public static final String SERVICE_JAVA_VM = "service.java.vm";
    public static final String SERVICE_IMPL_JAVA_VM = "serviceImpl.java.vm";
    public static final String CONTROLLER_JAVA_VM = "controller.java.vm";
    public static final String MAPPER_XML_VM = "mapper.xml.vm";
    public static final String API_JS_VM = "api.js.vm";
    public static final String INDEX_VUE_VM = "index.vue.vm";
    public static final String INDEX_TREE_VUE_VM = "index-tree.vue.vm";
    public static final String LIST_HTML_VM = "list.html.vm";
    public static final String LIST_TREE_HTML_VM = "list-tree.html.vm";
    public static final String TREE_HTML_VM = "tree.html.vm";
    public static final String ADD_HTML_VM = "add.html.vm";
    public static final String EDIT_HTML_VM = "edit.html.vm";
    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";
    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable) {
        String moduleName = genTable.getModuleName();
        String businessName = genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getTplCategory();
        String functionName = genTable.getFunctionName();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getTplCategory());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("abbrName", getAbbrName(genTable.getTableName()));
        velocityContext.put("functionName", StrUtil.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", genTable.getBusinessName());
        velocityContext.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
        velocityContext.put("businessName", genTable.getBusinessName());
        velocityContext.put("businessPath", getBusinessPath(genTable.getBusinessName()));
        velocityContext.put("moduleName", genTable.getModuleName());
        velocityContext.put("packageNameNoSmybol", StrUtil.isNotBlank(packageName) ? packageName : "");
        velocityContext.put("packageName", StrUtil.isNotBlank(packageName) ? "." + packageName : "");
        velocityContext.put("packageAfter", getPackageAfter(packageName, moduleName));
        velocityContext.put("packagePath", StrUtil.isNotBlank(packageName) ? "/" + StringUtils.replace(packageName, ".", "/") : "");
        velocityContext.put("baseModule", getModulePrefix(moduleName));
        velocityContext.put("moduleAfter", getModuleAfter(moduleName));
        velocityContext.put("author", genTable.getFunctionAuthor());
        velocityContext.put("datetime", DateUtils.getDate());
        velocityContext.put("pkColumn", genTable.getPkColumn());
        velocityContext.put("importList", getImportList(genTable));
        velocityContext.put("permissionPrefix", getPermissionPrefix(getPackageAfter(packageName, moduleName), businessName));
        velocityContext.put("columns", genTable.getColumns());
        velocityContext.put("table", genTable);
        velocityContext.put("dictString", getDictString(genTable));
        if (GenConstants.TPL_TREE.equals(tplCategory)) {
            setTreeVelocityContext(velocityContext, genTable);
        }
        return velocityContext;
    }

    private static String getAbbrName(String tableName) {
        StringBuilder abbrName = new StringBuilder();
        char[] chars = tableName.toCharArray();
        abbrName.append(chars[0]);
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                abbrName.append(chars[i + 1]);
            }
        }
        return abbrName.toString();
    }

    private static String getBusinessPath(String businessName) {
        StringBuilder path = new StringBuilder();
        for (char i : businessName.toCharArray()) {
            String value = String.valueOf(i);
            if (StrUtil.isUpperCase(value)) {
                path.append("/").append(StrUtil.swapCase(value));
            } else {
                path.append(value);
            }
        }
        return path.toString();
    }

    public static void setTreeVelocityContext(VelocityContext context, GenTable genTable) {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeCode = getTreeCode(paramsObj);
        String treeParentCode = getTreeParentCode(paramsObj);
        String treeName = getTreeName(paramsObj);

        context.put("treeCode", treeCode);
        context.put("TreeCode", StringUtils.capitalize(treeCode));
        context.put("treeParentCode", treeParentCode);
        context.put("TreeParentCode", StringUtils.capitalize(treeParentCode));
        context.put("treeName", treeName);
        context.put("TreeName", StringUtils.capitalize(treeName));
        context.put("expandColumn", getExpandColumn(genTable));
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
            context.put("tree_parent_code", paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
            context.put("tree_name", paramsObj.getString(GenConstants.TREE_NAME));
        }
    }

    /**
     * 获取模板信息
     *
     * @param tplCategory 模板类型
     * @param genTable    生成模板数据
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory, GenTable genTable) {
        List<String> templates = new ArrayList<>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/req/page.java.vm");
        templates.add("vm/java/req/form.java.vm");
       /* if (genTable.isSplit()) {
            templates.add("vm/java/dto/detailDto.java.vm");
            templates.add("vm/java/dto/pageDto.java.vm");
            templates.add("vm/java/params/params.java.vm");
            templates.add("vm/java/params/pageParams.java.vm");
            templates.add("vm/java/convertMapper.java.vm");
        }*/
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
        if (genTable.needFront()) {
            switch (genTable.getFrontType()) {
                case 0:
                  /*  templates.add("vm/html/list.html.vm");
                    templates.add("vm/html/add.html.vm");
                    templates.add("vm/html/edit.html.vm");
                    break;*/
                case 1:
                  /*  templates.add("vm/vue/v2/index.vue.vm");
                    templates.add("vm/js/api.js.vm");
                    break;*/
                case 2:
                    templates.add("vm/vue/index.vue.vm");
                    //templates.add("vm/js/api.js.vm");
                    templates.add("vm/js/api.ts.vm");
                    templates.add("vm/js/request.ts.vm");
                    break;
                default:
                    break;
            }
            /*if (GenConstants.TPL_CRUD.equals(tplCategory)) {
                switch (genTable.getFrontType()) {
                    case 0:
                        templates.add("vm/html/list.html.vm");
                        templates.add("vm/html/add.html.vm");
                        templates.add("vm/html/edit.html.vm");
                        break;
                    case 1:
                        templates.add("vm/vue/v2/index.vue.vm");
                        templates.add("vm/js/api.js.vm");
                        break;
                    case 2:
                        templates.add("vm/vue/index.vue.vm");
                        templates.add("vm/js/api.js.vm");
                        break;
                    default:
                        break;
                }
            }
            else if (GenConstants.TPL_TREE.equals(tplCategory)) {
                switch (genTable.getFrontType()) {
                    case 0:
                        templates.add("vm/html/tree.html.vm");
                        templates.add("vm/html/list-tree.html.vm");
                        templates.add("vm/html/add.html.vm");
                        templates.add("vm/html/edit.html.vm");
                        break;
                    case 1:
                        templates.add("vm/vue/v2/index-tree.vue.vm");
                        templates.add("vm/js/api.js.vm");
                        break;
                    case 2:
                        templates.add("vm/vue/index-tree.vue.vm");
                        templates.add("vm/js/api.js.vm");
                        break;
                    default:
                        break;
                }
            }*/
        }
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenTable genTable) {
        // 文件名称
        String fileName = "";
        // 模块路径
        String moduleName = genTable.getModuleName();
        String baseModulePath = StringUtils.replace(getModulePrefix(moduleName), ".", "/");
        String lastModulePath = StringUtils.replace(getModuleAfter(moduleName), ".", "/");
        // 包路径
        String packageName = genTable.getPackageName();
        // 大写类名
        String className = genTable.getClassName();
        // 业务名称
        String businessName = genTable.getBusinessName();
        // 上级菜单路径
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);

        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(moduleName, ".", "/");
        String packagePath = StrUtil.isNotBlank(packageName) ? "/" + StringUtils.replace(packageName, ".", "/") : "";
        String mybatisPath = MYBATIS_PATH + "/"  + (StrUtil.isNotBlank(packageName) ? packagePath : "");

        String vuePath = "vue";
        String htmlPath = "html";
        if (template.contains("form.java.vm")) {
            fileName = StringUtils.format("{}/controller{}/req/{}.java", javaPath, packagePath, "Req"+className+"");
        } else if (template.contains("page.java.vm")) {
            fileName = StringUtils.format("{}/controller{}/req/{}.java", javaPath, packagePath, "Req"+className+"Pagination");
        } else

        if (template.contains(DOMAIN_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/entity/{}.java", javaPath, packagePath, className);
        } else if (template.contains(DETAIL_DTO_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/domain/dto/{}DetailDto.java", javaPath, packagePath, className);
        } else if (template.contains(PAGE_DTO_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/domain/dto/{}PageDto.java", javaPath, packagePath, className);
        } else if (template.contains(PARAMS_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/domain/params/{}Params.java", javaPath, packagePath, className);
        } else if (template.contains(PAGE_PARAMS_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/domain/params/{}PageParams.java", javaPath, packagePath, className);
        } else if (template.contains(CONVERT_MAPPER_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/mapper/convert/Abstract{}ConvertMapper.java", javaPath, packagePath, className);
        } else if (template.contains(MAPPER_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/mapper/{}Mapper.java", javaPath, packagePath, className);
        } else if (template.contains(SERVICE_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/service/{}Service.java", javaPath, packagePath, className);
        } else if (template.contains(SERVICE_IMPL_JAVA_VM)) {
            fileName = StringUtils.format("{}{}/service/{}ServiceImpl.java", javaPath, packagePath, className);
        } else if (template.contains(CONTROLLER_JAVA_VM)) {
            fileName = StringUtils.format("{}/controller{}/{}Controller.java", javaPath, packagePath, className);
        } else if (template.contains(MAPPER_XML_VM)) {
            fileName = StrUtil.format("{}/{}Mapper.xml", MYBATIS_PATH+"/"+packageName, className);
        } else if (template.contains(API_JS_VM)) {
            fileName = StringUtils.format("{}/api/{}{}/{}.js", vuePath, lastModulePath, packagePath, businessName);
        } else if (template.contains(INDEX_VUE_VM)) {
            fileName = StringUtils.format("{}/views/{}{}/index.vue", vuePath,  packagePath, businessName);
        } else if (template.contains(INDEX_TREE_VUE_VM)) {
            fileName = StringUtils.format("{}/views/{}{}/{}/index.vue", vuePath, lastModulePath, packagePath, businessName);
        } else if (template.contains(LIST_HTML_VM)) {
            fileName = StringUtils.format("{}/{}.html", htmlPath, businessName);
        } else if (template.contains(LIST_TREE_HTML_VM)) {
            fileName = StringUtils.format("{}/{}.html", htmlPath, businessName);
        } else if (template.contains(TREE_HTML_VM)) {
            fileName = StringUtils.format("{}/tree.html", htmlPath);
        } else if (template.contains(ADD_HTML_VM)) {
            fileName = StringUtils.format("{}/add.html", htmlPath);
        } else if (template.contains(EDIT_HTML_VM)) {
            fileName = StringUtils.format("{}/edit.html", htmlPath);
        }

        else if (template.contains("api.ts.vm")) {
            fileName = StringUtils.format("{}/api/{}{}.ts", vuePath,  packagePath, className);
        }
        else if (template.contains("request.ts.vm")) {
            fileName = StringUtils.format("{}/api/request/{}{}.ts", vuePath,  packagePath, className);
        }
        return fileName;
    }

    /**
     * 获取模块路径前缀
     *
     * @param moduleName 模块路径
     * @return 模块路径前缀
     */
    public static String getModulePrefix(String moduleName) {
        int index = moduleName.indexOf(".", moduleName.indexOf(".") + 1);
        return StringUtils.substring(moduleName, 0, index);
    }

    /**
     * 获取模块路径后缀
     *
     * @param moduleName 模块路径
     * @return 模块路径后缀
     */
    public static String getModuleAfter(String moduleName) {
        int index = moduleName.indexOf(".", moduleName.indexOf(".") + 1);
        return StringUtils.substring(moduleName, index + 1);
    }

    /**
     * 根据列类型获取导入包
     *
     * @param genTable 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();
        HashSet<String> importList = new HashSet<>();
        for (GenTableColumn column : columns) {
            if (GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.util.Date");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            } else if (GenConstants.TYPE_BIG_DECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
            if (!column.isPk() && column.isRequired()) {
                if (GenConstants.TYPE_STRING.equals(column.getJavaType())) {
                    importList.add("javax.validation.constraints.NotBlank");
                } else {
                    importList.add("javax.validation.constraints.NotNull");
                }
            }
        }
        return importList;
    }

    /**
     * 根据列类型获取字典组
     *
     * @param genTable 业务表对象
     * @return 返回字典组
     */
    public static String getDictString(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();
        Set<String> dictSet = new HashSet<>();
        addDictSet(dictSet, columns);
        return StringUtils.join(dictSet, ", ");
    }

    /**
     * 添加字典列表
     *
     * @param dictSet 字典列表
     * @param columns 列集合
     */
    public static void addDictSet(Set<String> dictSet, List<GenTableColumn> columns) {
        for (GenTableColumn column : columns) {
            if (column.notSuperColumn() && StringUtils.isNotEmpty(column.getDictType()) && StringUtils.equalsAny(
                    column.getHtmlType(),
                    new String[]{GenConstants.HTML_SELECT, GenConstants.HTML_RADIO, GenConstants.HTML_CHECKBOX})) {
                dictSet.add("'" + column.getDictType() + "'");
            }
        }
    }

    /**
     * 获取包后缀
     *
     * @param packageName 包路径
     * @param moduleName 模块路径
     * @return 包后缀
     */
    public static String getPackageAfter(String packageName, String moduleName) {
        if (StrUtil.isBlank(packageName)) {
            return getModuleAfter(moduleName);
        }
        String dot = ".";
        if (packageName.contains(dot)) {
            packageName = packageName.substring(packageName.lastIndexOf(dot) + 1);
        }
        return packageName;
    }

    /**
     * 获取权限前缀
     *
     * @param packageName  包名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String packageName, String businessName) {
        return StrUtil.format("{}:{}", packageName, businessName);
    }

    /**
     * 获取树编码
     *
     * @param paramsObj 生成其他选项
     * @return 树编码
     */
    public static String getTreeCode(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_CODE)) {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_CODE));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取树父编码
     *
     * @param paramsObj 生成其他选项
     * @return 树父编码
     */
    public static String getTreeParentCode(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取树名称
     *
     * @param paramsObj 生成其他选项
     * @return 树名称
     */
    public static String getTreeName(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_NAME));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取需要在哪一列上面显示展开按钮
     *
     * @param genTable 业务表对象
     * @return 展开按钮列序号
     */
    public static int getExpandColumn(GenTable genTable) {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeName = paramsObj.getString(GenConstants.TREE_NAME);
        int num = 0;
        for (GenTableColumn column : genTable.getColumns()) {
            if (column.isList()) {
                num++;
                String columnName = column.getColumnName();
                if (columnName.equals(treeName)) {
                    break;
                }
            }
        }
        return num;
    }
}