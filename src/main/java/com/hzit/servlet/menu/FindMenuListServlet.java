package com.hzit.servlet.menu;

import com.alibaba.fastjson.JSONArray;
import com.hzit.bean.Menu;
import com.hzit.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindMenuListServlet",urlPatterns = "/FindMenuListServlet")
public class FindMenuListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //1.获取角色的ID
        String roleId = request.getParameter("roleId");

        //2.获取该角色id下所有的菜单
        MenuDao menuDao = new MenuDao();
        List<Menu> menuList = menuDao.findMenuListByRoleId(roleId);

        //3.转换成json
        String toJSONString = JSONArray.toJSONString(menuList);

        //4.返回结果
        response.getWriter().print(toJSONString);

    }
}
