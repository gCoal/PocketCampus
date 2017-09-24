package com.google.loader.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ViewRouter extends WebMvcConfigurerAdapter {
    @Value("${spring.staticResource}")
    private String staticResource;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        ViewControllerRegistration r=registry.addViewController("/index");
        r.setViewName("index");
        r.setStatusCode(HttpStatus.GONE);

        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(staticResource);
        super.addResourceHandlers(registry);
    }



    /**
     * 配置视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");// 视图前缀
        resolver.setSuffix(".html");// 视图后缀,文件类型
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    /**
     * 配置静态资源的处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

		/*
		 *  调用enable方法来要求DispatcherServlet将对静态资源的请求
		 *  转发到Servlet容器中默认的servlet上,而不是使用DispatcherServlet本身来
		 *  处理此类请求
		 */
        configurer.enable();
    }
                                                                                                                                                                                                                                                        

}
