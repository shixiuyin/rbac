package com.hzit.servlet.user;

import com.hzit.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelUserServlet",urlPatterns = "/DelUserServlet")
public class DelUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取参数  可能是多个数据
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String[] userIds = request.getParameterValues("userId");

        //判断是否有数据
        if(userIds!=null && userIds.length>0) {

            //2.调用数据库删除的方法
            UserDao userDao = new UserDao();
            int row = userDao.delUserById(userIds);

            //3.返回结果
            response.getWriter().print(row);
        }else
        {
            response.getWriter().print(0);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
