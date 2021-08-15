package com.my.controller;

import com.my.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserDao dao = new UserDao();
        String user_Id = request.getParameter("user_Id");


        boolean flag = dao.delete(user_Id);

        PrintWriter pw = null;
        response.setContentType("text/html;charset=utf-8");
        pw = response.getWriter();
        if(flag)
        {
            pw.print("<font style='color:red;font-size:40'>用户信息删除成功</font>");
        }
        else
        {
            pw.print("<font style='color:red;font-size:40'>用户信息删除失败</font>");
        }
    }

}
