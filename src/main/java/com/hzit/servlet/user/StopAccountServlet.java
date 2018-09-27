package com.hzit.servlet.user;

import com.hzit.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StopAccountServlet",urlPatterns = "/stopAccountServlet")
public class StopAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取ID
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userId = request.getParameter("userId");

        //2.调用删除方法
        UserDao userDao = new UserDao();
        int row = userDao.stopAccount(userId);

        //3.返回结果
        response.getWriter().print(row);

    }
}
