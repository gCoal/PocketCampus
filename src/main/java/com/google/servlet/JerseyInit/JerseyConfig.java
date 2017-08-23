package com.google.servlet.JerseyInit;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
	        packages("com.google.service");
	        register(JsonProvider.class);
	    }
	
}
