package com.my.listener;

import com.my.util.DBUtil;
import com.sun.javafx.collections.MappingChange;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OneListener implements ServletContextListener
{
    /*
        在Tomcat启动时，预先创建20个Connection，在userDao.add方法
        执行时将创建好的Connection交给add方法
     */

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Map map = new HashMap();
        for(int i = 0; i < 20; ++i)
        {
            Connection conn = DBUtil.getConnection();
            System.out.println("在Http服务器启动时，创建Connection：" + conn);
            map.put(conn, true); // true表示这个通道处于空闲状态，false表示通道正在被使用
        }

        // 为了在Http服务器运行期间，一直都可以使用这20个Connection，将其保存到全局作用域对象
        ServletContext application = sce.getServletContext();
        application.setAttribute("key1", map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        ServletContext application = sce.getServletContext();

        Map map = (Map)application.getAttribute("key1");

        // 通过迭代器对map集合中Connection对象进行排序
        Iterator it = map.keySet().iterator();
        while(it.hasNext())
        {
            Connection conn = (Connection)it.next();
            if(conn != null)
            {
                System.out.println(conn + "兄弟安心走，二十年后再来斗罗大陆哦");
            }
        }

    }
}
