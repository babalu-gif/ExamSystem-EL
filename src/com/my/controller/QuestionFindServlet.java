package com.my.controller;

import com.my.dao.QuestionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class QuestionFindServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        QuestionDao dao = new QuestionDao();
        // 调用DAO推送查询命令得到所有试题
        List list = dao.findAll();
        // 将得到的试题添加到请求作用域对象作为共享数据
        request.setAttribute("key1", list);
        // 请求转发向Tomcat调用questions.jsp将结果写入到响应体中
        request.getRequestDispatcher("/questions.jsp").forward(request, response);
    }

}
