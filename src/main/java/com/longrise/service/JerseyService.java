package com.longrise.service;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Service
@Path("/Jersey")
public class JerseyService {

    @GET
    @Path("/Test")
    public void Test(@Context HttpRequest httpRequest, @Context HttpResponse httpResponse){

    }
}
