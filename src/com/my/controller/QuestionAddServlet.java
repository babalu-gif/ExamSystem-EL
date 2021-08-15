package com.my.controller;

import com.my.dao.QuestionDao;
import com.my.entity.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class QuestionAddServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        QuestionDao dao = new QuestionDao();
        Question question = null;

        // 调用请求对象读取请求信息，得到新增信息内容
        String title = request.getParameter("title");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String answer = request.getParameter("answer");

        // 调用Dao对象将Insert命令推送到数据库，并得到处理结果
        question = new Question(null, title, optionA, optionB, optionC, optionD, answer);
        boolean flag = dao.add(question);

        // 通过请求转发，向Tomcat索要info.jsp将结果写到响应体
        if(flag)
        {
            request.setAttribute("info", "试题添加成功！");
        }
        else
        {
            request.setAttribute("info", "试题添加失败！");
        }
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }

}
