package com.anyex.apps.controller.system;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.system.req.ReqAuthUserInfo;
import com.anyex.apps.controller.system.req.ReqLogin;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.AccountPolicyException;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.TreeModel;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.shiro.model.UserInfoToken;
import com.anyex.apps.system.entity.SysResources;
import com.anyex.apps.system.entity.SysUserInfo;
import com.anyex.apps.system.service.SysResourcesService;
import com.anyex.apps.system.service.SysUserInfoService;
import com.anyex.apps.utils.NetworkUtils;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * AuthController Introduce
 * <p>File：AuthController.java </p>
 * <p>Title: AuthController </p>
 * <p>Description:AuthController </p>
 * <p>Copyright: Copyright (c) 17/6/21</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.AUTH)
@Api(tags = "安全认证")
public class AuthController extends GenericController
{
    @Autowired(required = false)
    private SysUserInfoService userInfoService;

    @Autowired(required = false)
    private SysResourcesService sysResourcesService;

    @PostMapping(value = "/login/submit")
    @ApiOperation(value = "用户登录认证", httpMethod = "POST")
    public JsonMessage submit(HttpServletRequest request, @ModelAttribute ReqLogin reqLogin)
    {
        Subject subject = SecurityUtils.getSubject();
        try
        {
            if (SecurityUtils.getSubject().isAuthenticated())
            { // 如果登录过就直接进入后台
                Serializable sessinId = subject.getSession().getId();
                return this.getJsonMessage(CommonEnums.SUCCESS, sessinId);
            }
            //
            String ip = NetworkUtils.getIpAddr(request);
            StringBuffer key = new StringBuffer(MessageConst.ADMIN_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip);
            String captchaText = RedisUtils.get(key.toString());
            if (captchaText == null || !captchaText.equalsIgnoreCase(reqLogin.getCaptcha()))
            { // 验证码检验
                throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
            }
            if (StringUtils.isBlank(reqLogin.getUsername()) || null == reqLogin.getPassword())
            { // 必要参数校验
                throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
            }
            //
            UserInfoToken token = new UserInfoToken(reqLogin.getUsername(), reqLogin.getPassword());
            //
            token.setHost(NetworkUtils.getIpAddr(request));
            subject.login(token);
        }
        catch (IncorrectCredentialsException ice)
        {
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_PASSWORD);
        }
        catch (UnknownAccountException uae)
        {
            return this.getJsonMessage(CommonEnums.ERROR_USER_NOT_EXIST);
        }
        catch (ExcessiveAttemptsException eae)
        {
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_TIMEOUT);
        }
        catch (AccountPolicyException gae)
        {
            return this.getJsonMessage(CommonEnums.NEED_POLICY_CHECK);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, subject.getSession().getId());
    }

    @GetMapping(value = "/userInfo")
    @ApiOperation(value = "获取当前会话用户信息", httpMethod = "GET")
    public JsonMessage<SysUserInfo> getUserInfo() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        SysUserInfo info = userInfoService.selectByPrimaryKey(principal.getId());
        log.info("permission");
        List<SysResources> listSysResources = sysResourcesService.findByUserId(OnLineUserUtils.getPrincipal().getId());
        //
        // 遍历
        final StringBuilder permission = new StringBuilder();
        listSysResources.forEach(sysResources -> {
            if(permission.length() > 0){
                permission.append(";");
                permission.append(sysResources.getResCode());
            } else {
                permission.append(sysResources.getResCode());
            }
        });
        log.info("permission:{}", permission.toString());
        //
        info.setPermission(permission.toString());
        return this.getJsonMessage(CommonEnums.SUCCESS, info);
    }

    @GetMapping(value = "/menuTree")
    @ApiOperation(value = "获取当前会话用户菜单", httpMethod = "GET")
    public JsonMessage<List<TreeModel>> menuTree() throws BusinessException
    {
        log.info("menuTree");
        List<TreeModel> data = OnLineUserUtils.getUserResourcesMenu();
        log.info("menuTree:{}", data);
        return getJsonMessage(CommonEnums.SUCCESS, data);
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "用户退出认证", httpMethod = "POST")
    public JsonMessage logout() throws BusinessException
    {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject)
        {
            subject.logout();
        }
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @ApiOperation(value = "更新当前会话用户信息", httpMethod = "POST")
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public JsonMessage updateUser(@ModelAttribute ReqAuthUserInfo info) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        SysUserInfo sysUserInfo = userInfoService.selectByPrimaryKey(principal.getId());
        BeanUtils.copyProperties(info, sysUserInfo);
        //
        sysUserInfo.setUpdateDate(System.currentTimeMillis());
        log.info("sysUserInfo:{}", sysUserInfo);
        userInfoService.updateByPrimaryKey(sysUserInfo);
        return json;
    }

    @ApiOperation(value = "修改当前会话用户登录密码", httpMethod = "POST")
    @RequestMapping(value = "/user/changePwd", method = RequestMethod.POST)
    public JsonMessage changePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        userInfoService.changePassword(principal.getId(), oldPwd, newPwd);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
