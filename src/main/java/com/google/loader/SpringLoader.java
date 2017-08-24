package com.google.loader;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringLoader extends AbstractAnnotationConfigDispatcherServletInitializer {

        @Override
        protected Class<?>[] getRootConfigClasses() {
            return new Class<?>[]{applicationContext.class};
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class<?>[]{WebConfig.class};
        }

        @Override
        protected String[] getServletMappings() {
            return new String[] { "/" };
        }
}
