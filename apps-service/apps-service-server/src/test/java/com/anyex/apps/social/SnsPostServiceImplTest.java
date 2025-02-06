package com.anyex.apps.social;


import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.model.SnsPostCityQueryModel;
import com.anyex.apps.social.model.SnsPostQueryModel;
import com.anyex.apps.social.service.SnsPostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SnsPostServiceImplTest extends BaseServiceImplTest  {

    @Autowired
    SnsPostService postService;


    @Test
    public void cityPosts()
    {
        Pagination pagination = new Pagination(1,10);
        SnsPostCityQueryModel search = new SnsPostCityQueryModel();
        search.setCity("nb");
        search.setViewerUserId("user");
        postService.cityPosts(pagination,search);

    }


    @Test
    public void homePosts()
    {
        Pagination pagination = new Pagination(1,100);
        SnsPostQueryModel search = new SnsPostQueryModel();
        search.setUserId("15700000005");
        search.setViewerUserId("15700000001");
        postService.homePosts(pagination,search);

    }



}