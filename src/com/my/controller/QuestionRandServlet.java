package com.my.controller;

import com.my.dao.QuestionDao;
import com.my.entity.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRandServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Question> list = new ArrayList();
        HttpSession session = request.getSession(false);
        QuestionDao dao = new QuestionDao();
        // 调用DAO对象随机从表中取出4到题目
        list = dao.findRand();
        // 将4道题目添加到request作为共享数据
        session.setAttribute("key", list);
        // 请求转发，申请调用exam.jsp将4道题写入到响应体中
        request.getRequestDispatcher("/exam.jsp").forward(request, response);
    }
}
