<%@ page import="java.util.List" %>
<%@ page import="com.my.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/7/28
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <form action="/myWeb/exam">
            <table border="2">
                <tr>
                    <td>试题编号</td>
                    <td>题目信息</td>
                    <td>A</td>
                    <td>B</td>
                    <td>C</td>
                    <td>D</td>
                    <td>答案</td>
                </tr>

                <%
                    List<Question> list = (List) session.getAttribute("key");
                    for(Question question : list){
                %>
                <tr>
                    <td><%=question.getQuestionId()%></td>
                    <td><%=question.getTitle()%></td>
                    <td><%=question.getOptionA()%></td>
                    <td><%=question.getOptionB()%></td>
                    <td><%=question.getOptionC()%></td>
                    <td><%=question.getOptionD()%></td>
                    <td>
                        <input type="radio" name="answer_<%=question.getQuestionId()%>" value="A"/>A
                        <input type="radio" name="answer_<%=question.getQuestionId()%>" value="B"/>B
                        <input type="radio" name="answer_<%=question.getQuestionId()%>" value="C"/>C
                        <input type="radio" name="answer_<%=question.getQuestionId()%>" value="D"/>D
                    </td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td align="center" colspan="3"><input type="submit" value="交卷"></td>
                    <td align="center" colspan="4"><input type="reset" value="重做"></td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
