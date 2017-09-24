package com.google.loader.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("进入JwtAuthenticationTokenFilter");

        // 得到 请求头的 认证信息 authToken
        String authToken = request.getHeader("Authorization");

        // 解析 authToken 得到 用户名
        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        System.out.println("checking authentication for customer " + username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 检验token是否有效，并检验其保存的用户信息是否正确
            if (jwtTokenUtil.validateTokenByRedis(authToken)) {
                // 根据用户名从数据库查找用户信息
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                // token 有效，为该请求装载 用户权限信息
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }
        System.out.println("离开JwtAuthenticationTokenFilter");
        chain.doFilter(request, response);
    }




}

