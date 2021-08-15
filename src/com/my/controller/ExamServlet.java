package com.my.controller;

import com.my.entity.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExamServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        List<Question> list = null;
        String userName = (String) session.getAttribute("key1");
        int score = 0;
        // 从当前用户私人储物柜得到系统提供的四种题目信息
        list = (List<Question>) session.getAttribute("key");
        // 从请求包读取用户对于4道题给出的答案
        for(Question question : list)
        {
            Integer questionId = question.getQuestionId();
            String answer = question.getAnswer();
            String userAnswer = request.getParameter("answer_"+questionId);
            // 判分
            if(answer.equals(userAnswer)) // 防止用户不选择，出现空指针异常
            {
                score += 25;
            }
        }
        // 将分数写到request中，作为共享数据
        request.setAttribute("info", userName + "本次考试成绩："+score);
        // 请求转发调用jsp将用户本次考试分数写入到响应体
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }
}
