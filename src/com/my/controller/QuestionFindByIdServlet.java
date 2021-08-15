package com.my.controller;

import com.my.dao.QuestionDao;
import com.my.entity.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class QuestionFindByIdServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        QuestionDao dao = new QuestionDao();
        Question question = null;
        // 调用请求对象读取请求头参数信息，得到试题编号
        String questionId = request.getParameter("questionId");
        // 调用DAO推送查询命令得到这个试题编号对应的试题信息
        question = dao.findById(questionId);
        // 调用question_Update.jsp将试题信息写入到响应体
        request.setAttribute("key", question);
        request.getRequestDispatcher("/question_Update.jsp").forward(request, response);
    }

}
