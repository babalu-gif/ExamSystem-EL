package com.my.dao;

import com.my.entity.Question;
import com.my.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao
{

    // 添加试题
    public boolean add(Question question)
    {
        boolean flag = false;
        PreparedStatement ps = null;
        String sql = "insert into t_question(title, optionA, optionB, optionC, optionD, answer)" +
                " values(?, ?, ?, ?, ?, ?)";

       ps = DBUtil.createStatement(sql);
        try
        {
            ps.setString(1, question.getTitle());
            ps.setString(2, question.getOptionA());
            ps.setString(3, question.getOptionB());
            ps.setString(4, question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getAnswer());

            if(ps.executeUpdate() == 1)
            {
                flag = true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.close(ps, null);
        }

        return flag;
    }


    // 删除试题
    public boolean delete(Integer questionId)
    {
        boolean flag = false;
        String sql = "delete from t_question where questionId = ?";
        PreparedStatement ps = null;

        ps = DBUtil.createStatement(sql);
        try
        {
            ps.setInt(1, questionId);
            if(ps.executeUpdate() == 1)
            {
                flag = true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.close(ps, null);
        }
        return flag;
    }


    // 试题查询
    public List findAll()
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> list = new ArrayList();
        String sql = "select * from t_question";

        ps = DBUtil.createStatement(sql);
        try
        {
            rs = ps.executeQuery();
            while(rs.next())
            {
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");

                Question question =new Question(questionId, title, optionA, optionB, optionC, optionD, answer);
                list.add(question);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.close(ps, rs);
        }
        return list;
    }


    // 根试题编号查询试题详细信息
    public Question findById(String questionId)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Question question = null;
        String sql = "select * from t_question where questionId = ?";

        ps = DBUtil.createStatement(sql);
        try
        {
            ps.setInt(1, Integer.valueOf(questionId));
            rs = ps.executeQuery();
            while(rs.next())
            {
                Integer questionID = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                question = new Question(questionID, title, optionA, optionB, optionC, optionD, answer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.close(ps, rs);
        }
        return question;
    }


    // 更新试题
    public boolean update(Question question)
    {
        String sql = "update t_question set title = ?, optionA = ?, optionB = ?," +
                " optionC = ?, optionD = ?, answer = ? where questionId = ?";
        PreparedStatement ps = null;
        boolean flag = false;

        ps = DBUtil.createStatement(sql);
        try
        {
            ps.setString(1, question.getTitle());
            ps.setString(2, question.getOptionA());
            ps.setString(3, question.getOptionB());
            ps.setString(4, question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getAnswer());
            ps.setInt(7, question.getQuestionId());

            if(ps.executeUpdate() == 1)
            {
                flag = true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.close(ps, null);
        }

        return flag;
    }


    // 随机取出试题
    public List findRand()
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> list = new ArrayList();
        String sql = "select * from t_question order by floor(1+rand()*7) limit 0, 4";

        ps = DBUtil.createStatement(sql);
        try
        {
            rs = ps.executeQuery();
            while(rs.next())
            {
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");

                Question question =new Question(questionId, title, optionA, optionB, optionC, optionD, answer);
                list.add(question);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.close(ps, rs);
        }
        return list;
    }
}
