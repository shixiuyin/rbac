package com.hzit.servlet.role;

import com.alibaba.fastjson.JSONArray;
import com.hzit.dao.RoleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindRoleByUserServlet",urlPatterns = "/findRoleByUserServlet")
public class FindRoleByUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //1.获取的用户ID
        String userId = request.getParameter("userId");


        //2.根据用户的ID,去数据库查询指定的role信息
        RoleDao roleDao = new RoleDao();
        List<Object> list = roleDao.findRoleListByUser(userId);

        //3.将其转换为json格式的数据
        String toJSONString = JSONArray.toJSONString(list);


        //4.返回数据
        response.getWriter().print(toJSONString);


    }
}
