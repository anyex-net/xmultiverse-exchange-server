package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysDictionary;
import com.anyex.apps.common.service.SysDictionaryService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqSysDictionary;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.ObjectUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 字典数据 控制器
 * <p>File：DictionaryController.java </p>
 * <p>Title: DictionaryController </p>
 * <p>Description:DictionaryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "数据字典")
public class SysDictionaryController extends GenericController
{
    @Autowired(required = false)
    private SysDictionaryService dictionaryService;

    @GetMapping(value = "/dictionary/findById")
    @RequiresPermissions("common:dictionary:data")
    @ApiOperation(value = "根据ID取字典数据", httpMethod = "GET")
    public JsonMessage<SysDictionary> findById(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, dictionaryService.selectByPrimaryKey(id));
    }

    @GetMapping(value = "/dictionary/findByCode")
    @RequiresPermissions("common:dictionary:data")
    @ApiOperation(value = "根据字典编码和语言取字典项", httpMethod = "GET")
    public JsonMessage<List<SysDictionary>> findByCode(@RequestParam("code") String code, @RequestParam("lang") String lang) throws BusinessException
    {
        if (StringUtils.isBlank(code))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        return this.getJsonMessage(CommonEnums.SUCCESS, dictionaryService.findByCode(code, lang));
    }

    @PostMapping(value = "/dictionary/save")
    @RequiresPermissions("common:dictionary:operator")
    @ApiOperation(value = "保存或更新字典数据", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSysDictionary info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        //
        if (beanValidator(json, info))
        {
            //修改父节点时删除缓存，避免更新不同步
            if (!ObjectUtils.isEmpty(info.getParentId())) {
                SysDictionary dictionary= dictionaryService.selectByPrimaryKey(info.getParentId());
                if (!ObjectUtils.isEmpty(RedisUtils.get(dictionary.getCode()))) {
                    RedisUtils.del(dictionary.getCode());
                }
            }
            //
            SysDictionary sysDictionary = new SysDictionary();
            BeanUtils.copyProperties(info, sysDictionary);
            //
            if (null == info.getId())
            {
                sysDictionary.setCreateBy(principal.getId());
                sysDictionary.setCreateDate(System.currentTimeMillis());
            }
            //
            log.info("sysDictionary:{}", sysDictionary);
            dictionaryService.save(sysDictionary);
        }
        return json;
    }

    @GetMapping(value = "/dictionary/tree")
    @RequiresPermissions("common:dictionary:data")
    @ApiOperation(value = "返回以TREEMODEL对象的所有数据", httpMethod = "GET")
    public JsonMessage<List<SysDictionary>> tree() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, dictionaryService.treeData());
    }

    @PostMapping(value = "/dictionary/data")
    @RequiresPermissions("common:dictionary:data")
    @ApiOperation(value = "查询字典数据", httpMethod = "POST")
    public JsonMessage<List<SysDictionary>> data(Long id) throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, dictionaryService.findList(new SysDictionary(id)));
    }

    @PostMapping(value = "/dictionary/del")
    @RequiresPermissions("common:dictionary:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    public JsonMessage del(@RequestParam("id") Long id) throws BusinessException
    {
        SysDictionary dictionary = new SysDictionary();
        dictionary.setParentId(id);
        List<SysDictionary> resourcesList = dictionaryService.findList(dictionary);
        if (resourcesList == null || resourcesList.size() == 0) {
            dictionaryService.remove(id);
            return this.getJsonMessage(CommonEnums.SUCCESS);
        } else {
            log.error("存在子节点，当前节点无法删除!");
            return this.getJsonMessage(CommonEnums.ERROR_EXIST_SUBNODE);
        }
    }
}
