package com.anyex.apps.controller;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * IndexController
 * <p>File: IndexController.java </p>
 * <p>Title: IndexController </p>
 * <p>Description: IndexController </p>
 * <p>Copyright: Copyright (c) 2019-05-20</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@ApiIgnore
public class IndexController extends GenericController
{
    @GetMapping("/")
    public String index() throws BusinessException
    {
        return "redirect:swagger-ui.html";
    }
}
