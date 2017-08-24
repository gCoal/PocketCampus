package com.google.loader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // 声明本类为配置类组件
@EnableWebMvc // 启用SpringMVC
/*
 * 启用组件扫描,扫描spitter.web包下的所有带有Component注解的类
 * 如果不启用spring将只能找到显式声明在配置类中的控制器
 */
@ComponentScan("com.google")
public class WebConfig extends WebMvcConfigurerAdapter {

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