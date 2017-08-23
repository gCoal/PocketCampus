package com.google.servlet.JerseyInit;

import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        initParams = {
                @WebInitParam(name = "javax.ws.rs.Application", value="com.google.servlet.JerseyInit.JerseyConfig"),
                },
        urlPatterns="/rest/*",
        loadOnStartup = 2
)
public class JerseyLoader extends ServletContainer {

}
