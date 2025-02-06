package com.anyex.apps.account;

import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.account.entity.Attribute;
import com.anyex.apps.account.service.AttributeService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AttributeServiceImplTest extends BaseServiceImplTest
{
    @Autowired
    private AttributeService attributeService;

    @Test
    public void list() throws BusinessException {
        List<Attribute> list = attributeService.findList(new Attribute());
        for(Attribute attribute : list)
        {
            System.out.println(attribute);
        }
    }

    @Test
    public void entity() throws BusinessException {
        Attribute a = attributeService.findByUserId("13700000001");
        System.out.println(a);
    }

    @Test
    public void findByUserIds()
    {
        List<String> aaa = new ArrayList<String>();
        aaa.add("13700000002");
        List<Attribute> result =attributeService.findByUserIds(aaa);
        System.out.println(result);
    }

    @Test
    public void search()
    {
        Attribute search = new Attribute();
        search.setUserId("13700000001");
        search.setGender(null);
        Pagination pagination = new Pagination(1,20);
        PaginateResult<Attribute> result =attributeService.search(pagination,search);
        System.out.println(result);
    }
}
