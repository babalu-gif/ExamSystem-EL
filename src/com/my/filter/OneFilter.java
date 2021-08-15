package com.my.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = null;
        // 调用请求对象读取请求包中请求行的URI，了解用户访问的资源文件是谁
        String uri = request.getRequestURI(); // [/网站名/资源文件名 /myWeb/login.html or /myWeb/login or...]

        // 如果本次请求资源文件与登录相关[login.html 或者 LoginServlet]此时应该无条件放行
        // uri.indexOf("login") != -1 "login"在uri中
        if(uri.indexOf("login") != -1 || "/myWeb/".equals(uri))
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return ;
        }

        // 如果本次请求访问的是其他资源文件，需要得到用户在服务端HttpSession
        session = request.getSession(false);
        if(session != null) // 放行
        {
           filterChain.doFilter(servletRequest, servletResponse);
           return ;
        }

        // 做拒绝请求
        request.getRequestDispatcher("/login_error.html").forward(servletRequest, servletResponse);

        /**
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 拦截后，通过请求对象向Tomcat索要当前用户的HttpSession
        HttpSession session = request.getSession(false);

        // 判断来访用户的合法性问题
        if(session == null)
        {
            request.getRequestDispatcher("/login_error.html").forward(servletRequest, servletResponse);
            return ;
        }

        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
        */
    }
}
