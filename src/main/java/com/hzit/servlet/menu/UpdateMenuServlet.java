package com.hzit.servlet.menu;

import com.hzit.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateMenuServlet",urlPatterns ="/updateMenuServlet")
public class UpdateMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1.获取roleId
        String roleId = request.getParameter("roleId");

        //2.根据传入的资源菜单的ID进行修改(先删除，在添加)
        String[] menuIds = request.getParameterValues("menuIds");
        MenuDao menuDao = new MenuDao();

        int row = menuDao.updateOrDeleteMenuByRoleId(roleId,menuIds);

        //3.返回操作的结果
        response.getWriter().print(row);






    }
}
