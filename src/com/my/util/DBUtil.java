package com.my.util;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Map;

/**
 * @author 付博文
 *	JDBC工具类，简化JDBC编程
 */
public class DBUtil
{
    public static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    public static final String DRIVER = bundle.getString("Driver");
    public static final String URL = bundle.getString("url");
    public static final String USER = bundle.getString("user");
    public static final String PASSWORD = bundle.getString("password");

    // ----------------通过全局作用域对象得到Connnection-----------start
    public static Connection getConnection(HttpServletRequest request)
    {
        // 通过请求对象，得到全局作用域对象
        ServletContext application = request.getServletContext();

        // 从全局作用域对象得到map
        Map map = (Map)application.getAttribute("key1");

        // 从map集合中得到一个处于空闲状态Connection
        Iterator it = map.keySet().iterator();
        while(it.hasNext())
        {
            conn = (Connection)it.next();
            boolean flag = (boolean)map.get(conn);
            if(flag == true)
            {
                map.put(conn, false);
                break;
            }
        }

        return conn;
    }

    public static PreparedStatement createStatement(HttpServletRequest request, String sql)
    {
        conn = DBUtil.getConnection(request);
        PreparedStatement ps = null;

        try
        {
            ps = conn.prepareStatement(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ps;
    }


    public static void close(HttpServletRequest request, Connection conn, Statement stm, ResultSet rs)
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if(stm!= null)
        {
            try
            {
                stm.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        map.put(conn, true);

    }
    // ----------------通过全局作用域对象得到Connnection-----------end


    static
    {
      //  String driver = bundle.getString(dirver);

        try
        {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        System.out.println("Driver驱动创建成功！");

    }

    private static Connection conn = null;
    // private static PreparedStatement ps = null;
    private DBUtil()
    {

    }

    /**
     *
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection()
    {

        try
        {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e)
        {
            System.out.println("获取数据库连接失败");
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public static PreparedStatement createStatement(String sql)
    {
        conn = DBUtil.getConnection();
        PreparedStatement ps = null;

        try
        {
            ps = conn.prepareStatement(sql);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return ps;
    }

    /**
     *
     * @param ps 数据库操作对象
     * @param rs 结果集
     */
    public static void close(PreparedStatement ps, ResultSet rs)
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if(ps!= null)
        {
            try
            {
                ps.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}