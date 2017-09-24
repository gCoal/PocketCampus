package com.google.loader.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SpringSessionInitializer extends AbstractHttpSessionApplicationInitializer {
        public SpringSessionInitializer() {
            super(SessionConfig.class);
        }
}
