//package com.anyex.apps.csrf;
//
//import com.blocain.exchange.tags.CustomRequestProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <p>File：CSRFDataValueProcessor</p>
// * <p>Title: </p>
// * <p>Description: 用于配合 spring <form:form> 标签 在渲染时加入 csrf token 隐藏域</p>
// * <p>Copyright: Copyright (c) 2014 2014/3/22 10:52</p>
// * <p>Company: com.blocain</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Component
//public class CSRFDataValueProcessor implements CustomRequestProcessor
//{
//    @Autowired
//    private CSRFTokenManager csrfTokenManager;
//
//    @Override
//    public String processAction(HttpServletRequest request, String action, String httpMethod)
//    {
//        return action;
//    }
//
//    @Override
//    public String processFormFieldValue(HttpServletRequest request, String name, String value, String type)
//    {
//        return value;
//    }
//
//    @Override
//    public Map<String, String> getExtraHiddenFields(HttpServletRequest request)
//    {
//        return getExtraHiddenFields(request, "defaultForm");
//    }
//
//    @Override
//    public Map<String, String> getExtraHiddenFields(HttpServletRequest request, String formId)
//    {
//        Map<String, String> hiddenFields = new HashMap<>();
//        hiddenFields.put(CSRFTokenManager.CSRF_TOKEN_NAME, csrfTokenManager.getTokenForRequest(request, formId));
//        return hiddenFields;
//    }
//
//    @Override
//    public String processUrl(HttpServletRequest request, String url)
//    {
//        return url;
//    }
//}
