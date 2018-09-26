package com.hzit.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.hzit.bean.ResponseData;
import com.hzit.bean.User;
import com.hzit.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindUserListServlet",urlPatterns = "/findUserListServlet")
public class FindUserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //尝试分页  默认传入 page   limit 需要根据这两个返回对应范围的值

        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        //1.得到数据库所有的数据
        UserDao userDao = new UserDao();
        List<Object> userList = userDao.findUserListByPage();

        //得到总数量
        Integer count = userDao.getUserCount();

        //2.封装到ResponseData里面
        ResponseData responseData = ResponseData.success(count, userList);


        //3.装换为json对象(fastjson) 需要导包
        String jsonString = JSONObject.toJSONString(responseData);

        System.out.println("json:"+jsonString);
        response.getWriter().print(jsonString);

    }
}
