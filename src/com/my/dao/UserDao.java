package com.my.dao;

import com.my.entity.Users;
import com.my.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao
{
    static Connection conn = null;
    static PreparedStatement ps = null;

    // 添加用户信息
    public boolean add(Users user)
    {
        boolean flag = false;
        //Connection conn = null;

        try
        {
            String sql = "insert into t_user(user_Name, user_Password, user_Sex, user_Email)" +
                    " values(?, ?, ?, ?)";

            ps = DBUtil.createStatement(sql);

            ps.setString(1, user.getUser_Name());
            ps.setString(2, user.getUser_Password());
            ps.setString(3, user.getUser_Sex());
            ps.setString(4, user.getUser_Email());

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

    public boolean add(Users user, HttpServletRequest request)
    {
        boolean flag = false;
        //Connection conn = null;

        try
        {
            String sql = "insert into t_user(user_Name, user_Password, user_Sex, user_Email)" +
                    " values(?, ?, ?, ?)";

            ps = DBUtil.createStatement(request, sql);

            ps.setString(1, user.getUser_Name());
            ps.setString(2, user.getUser_Password());
            ps.setString(3, user.getUser_Sex());
            ps.setString(4, user.getUser_Email());

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

    // 查询所有用户信息
    public List findAll()
    {
        String sql = "select * from t_user";
        List<Users> userList = new ArrayList();
        ps = DBUtil.createStatement(sql);
        ResultSet rs = null;

        try
        {
            rs = ps.executeQuery();

            while(rs.next())
            {
                Integer user_Id = rs.getInt("user_Id");
                String user_Name = rs.getString("user_Name");
                String user_Password = rs.getString("user_Password");
                String user_Sex = rs.getString("user_Sex");
                String user_Email = rs.getString("user_Email");

                Users user = new Users(user_Id, user_Name, user_Password, user_Sex, user_Email);
                userList.add(user);
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

        return userList;
    }

    // 根据用户编号删除用户信息
    public boolean delete(String user_Id)
    {
        String sql = "delete from t_user where user_id = ?";
        boolean flag = false;
        PreparedStatement ps = null;

        ps = DBUtil.createStatement(sql);
        try
        {
            ps.setInt(1, Integer.valueOf(user_Id));
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

    // 登录验证
    public boolean login(String userName, String password)
    {
        boolean flag = false;
        String sql = "select user_Name, user_Password from t_user where user_Name = ? and user_Password = ?";
        ResultSet rs = null;

        ps = DBUtil.createStatement(sql);
        try
        {
            ps.setString(1, userName);
            ps.setString(2, password);

            rs = ps.executeQuery();
            while(rs.next())
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
            DBUtil.close(ps, rs);
        }

        return flag;
    }
}
