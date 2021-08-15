package com.my.controller;

import com.my.dao.UserDao;
import com.my.entity.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserAddServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserDao dao = new UserDao();

        // 调用请求对象读取请求头参数信息，得到用户的信息
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");

        // 调用UserDao将用户信息填充到INSERST命令并借助JDBC规范发送到数据库服务器
        Users user = new Users(null, userName, password, sex, email);
        Date startDate = new Date();
        boolean flag = dao.add(user, request);
        Date endDate = new Date();
        System.out.println("添加用户消耗时间：" + (endDate.getTime()-startDate.getTime()) + "毫秒"); // 12~13ms

        // 调用响应对象将处理结果以二进制形式写入到响应体
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        if(flag)
        {
            pw.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }
        else
        {
            pw.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
        // Tomcat负责销毁请求对象和响应对象
        // Tomcat负责将Http响应协议包推送到发起请求的浏览器上
        // 浏览器根据响应头content-type指定编译器对响应体二进制内容编辑
        // 浏览器将编辑后结果在窗口展示给用户结束
    }

}
