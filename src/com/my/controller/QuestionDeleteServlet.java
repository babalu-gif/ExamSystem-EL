package com.my.controller;

import com.my.dao.QuestionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class QuestionDeleteServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 调用请求对象读取请求头参数信息，得到试题编号
        QuestionDao dao = new QuestionDao();
        Integer questionId = Integer.valueOf(request.getParameter("questionId"));
        // 调用DAO推送查询命令得到这个试题编号对应的试题信息
        boolean flag = dao.delete(questionId);
        // 调用question_Delete.jsp将全部试题写入到响应体
        if(flag)
        {
            request.setAttribute("key1", "试题删除成功！");
        }
        else
        {
            request.setAttribute("key1", "试题删除失败！");
        }
        request.getRequestDispatcher("/question_Delete.jsp").forward(request, response);
    }
}
