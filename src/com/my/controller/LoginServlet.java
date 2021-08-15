package com.my.controller;

import com.my.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserDao dao = new UserDao();
        // 调用请求对象对请求体使用utf-8字符集做出重新编辑
        request.setCharacterEncoding("utf-8");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        boolean flag = dao.login(userName, password);

        // 为true说明用户名和密码正确
        if(flag)
        {
            // 在判定来访用户身份合法后，通过请求对象向Tomcat申请为当前用户申请一个HttpSession
            HttpSession session = request.getSession();
            session.setAttribute("key1", userName);
            response.sendRedirect("/myWeb/index.html");
        }
        else
        {
            response.sendRedirect("/myWeb/login_error.html");
        }
    }
}
