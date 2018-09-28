package com.hzit.servlet.role;

import com.hzit.dao.RoleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateRoleByUser",urlPatterns = "/updateRoleByUser")
public class UpdateRoleByUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1.获取用户ID 和 roleId
        String[] roleIds = request.getParameterValues("roleId");
        String userId = request.getParameter("userId");

        //2.根据参数操作数据库
        RoleDao roleDao = new RoleDao();
        int row = roleDao.updateOrDeleteRole(userId,roleIds);


        //3.返回结果
        response.getWriter().print(row);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
