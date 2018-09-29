package com.hzit.servlet.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzit.bean.Nav;
import com.hzit.bean.User;
import com.hzit.dao.MenuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadNavServlet",urlPatterns = "/loadNavServlet")
public class LoadNavServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //1.判断用户是否登录(session能否找到用户名)
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();

        Object userInfo = session.getAttribute("userInfo");//因为登录的时候如果成功了，那么就存放该数据

        if(userInfo==null)
        {
            //重定向到登录界面
            response.sendRedirect(request.getContextPath()+"/jsp/Login.jsp");
        }else {

            //表示用户已经登录，获取用户id
            User uesr = (User)userInfo;
            String userId = uesr.getUserId();

            MenuDao menuDao = new MenuDao();

            JSONObject jsonObject = new JSONObject();

            List<Nav> navByRoleId = menuDao.findNavByRoleId(userId);

            jsonObject.put("data",navByRoleId);
            //String toJSONString = JSONArray.toJSONString(navByRoleId);

            response.getWriter().println(jsonObject.toString());

        }


        //2.调用加载的方法







    }
}
