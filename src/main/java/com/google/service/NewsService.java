package com.google.service;

import com.longrise.annotation.Register;
import com.longrise.communicate.HttpType;
import com.google.logic.NewsLogic;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Service
@Path("/news")
public class NewsService {
    @Resource
    private NewsLogic newsLogic;


    @GET
    @Path("/DBTest")
    @Register(name = "DBTest",HttpMethod = HttpType.GET_PATH,version = "1.0.0")
    public JSONObject DBTest(){
        return  newsLogic.DbTest();
    }

    @GET
    @Path("/DBTest02")
    @Register(name = "DBTest02",HttpMethod = HttpType.GET_PATH,version = "1.0.0")
    public JSONObject DBTest02(){
        return  newsLogic.DbTest02();
    }

    @POST
    @Path("/PersistenceTest")
    @Register(name = "PersistenceTest",HttpMethod = HttpType.POST_PATH,version = "1.0.0")
    public String PersistenceTest(){
        return  newsLogic.PersistenceTest();
    }

    @POST
    @Path("/PersistenceTest02/{id}")
    @Register(name = "PersistenceTest02",HttpMethod = HttpType.POST_PATH,version = "1.0.0")
    public String PersistenceTest02(@PathParam("id")int id){
        return  newsLogic.PersistenceTest02(id);
    }

    @POST
    @Path("/Test")
    @Register(name = "Test",HttpMethod = HttpType.POST_PATH,version = "1.0.0")
    public String Test(){
        return  newsLogic.Test();
    }


}
