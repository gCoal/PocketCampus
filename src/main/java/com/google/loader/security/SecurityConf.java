package com.google.loader.security;

import com.dingdingzaixian.untils.ClassUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    // Spring会自动寻找同样类型的具体类注入，这里就是JwtUserDetailsServiceImpl了
//    @Resource
//    private UserDetailsService userDetailsService;
//
//    @Resource
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                // 设置UserDetailsService
//                .userDetailsService(this.userDetailsService)
//                // 使用BCrypt进行密码的hash
//                .passwordEncoder(passwordEncoder());
//    }
    // 装载BCrypt密码编码器
    private static String[] authPaths;
    static {
        Object[] objs=ClassUtil.getAuthPath("com.dingdingzaixian");
        authPaths=new String[objs.length];
        for(int i=0;i<objs.length;i++)
        {
            authPaths[i]=objs[i].toString();
        }
    }


    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/hello").permitAll()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/WeChatCustomer/Login","/WeChatCustomer/SignIn").permitAll()
                .antMatchers(authPaths).authenticated()
//                .anyRequest().authenticated()   // 除上面外的所有请求全部需要鉴权认证
                .and()

                .addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);


        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }

}
