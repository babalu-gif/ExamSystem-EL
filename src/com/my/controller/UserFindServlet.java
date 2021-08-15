package com.my.controller;

import com.my.dao.UserDao;
import com.my.entity.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserDao dao = new UserDao();

        PrintWriter pw = null;
        List<Users> userList = dao.findAll();



        // 启动服务
        response.setContentType("text/html;charset=utf-8");
        pw = response.getWriter();
        pw.print("<table border='2' align='center'>");
        pw.print("<tr>");
        pw.print("<td>用户编号</td>");
        pw.print("<td>用户名称</td>");
        pw.print("<td>用户密码</td>");
        pw.print("<td>用户性别</td>");
        pw.print("<td>用户邮箱</td>");
        pw.print("<td>操作</td>");
        pw.print("</tr>");

        for(Users user : userList)
        {
            pw.print("<tr>");
            pw.print("<td>" + user.getUser_Id() + "</td>");
            pw.print("<td>" + user.getUser_Name() + "</td>");
            pw.print("<td>" + "******" + "</td>");
            pw.print("<td>" + user.getUser_Sex() + "</td>");
            pw.print("<td>" + user.getUser_Email() + "</td>");
            pw.print("<td><a href='/myWeb/user/delete?user_Id="+ user.getUser_Id() +"'>删除用户</a></td>");
            pw.print("</tr>");
        }
        pw.print("</table>");

    }
}
