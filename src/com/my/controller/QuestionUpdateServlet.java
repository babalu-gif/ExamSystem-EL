package com.my.controller;

import com.my.dao.QuestionDao;
import com.my.entity.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class QuestionUpdateServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        QuestionDao dao = new QuestionDao();
        Question question = null;

        // 调用请求对象读取请求信息，得到信息内容
        String questionId = request.getParameter("questionId");
        String title = request.getParameter("title");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String answer = request.getParameter("answer");
        question = new Question(Integer.valueOf(questionId), title, optionA, optionB, optionC, optionD, answer);

        // 调用Dao实现更新操作
        boolean flag = dao.update(question);
        // 调用info.jsp将操作结果写到响应体
        if(flag)
        {
            request.setAttribute("info", "试题更新成功！");
        }
        else
        {
            request.setAttribute("info", "试题更新失败！");
        }
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }

}
