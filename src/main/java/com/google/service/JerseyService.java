package com.google.service;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.entity.Student;


@Path("/Jersey")
public class JerseyService {
    @Resource
    Student student;

    @GET
    @Path("/Test")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Test(@Context HttpRequest httpRequest, @Context HttpResponse httpResponse){
        student.setId("001");
        student.setName("zhangsan");
        Response.ResponseBuilder builder = Response.ok(student);
        builder.status(200);
        return builder.build();
    }
}
