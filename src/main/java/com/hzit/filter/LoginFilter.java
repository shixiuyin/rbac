package com.hzit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 所有的请求都会拦截
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //过滤器 一般用来处理整个程序中的共性问题(编码处理,事务处理,验证权限)
        HttpServletRequest request  =(HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //1.由于配置 /** 所有的请求都会被拦截
        //2.自己配置规则 什么放行
        //如果用户已经登录，直接放行  如果没有登录，跳转到登录界面
        HttpSession session = request.getSession();

        //获取请求路径
        String path = request.getServletPath();

        //需要对入口放行
        if("/jsp/Login.jsp".equals(path)||"/loginServlet".equals(path)||path.contains("/frame"))
        {
            chain.doFilter(request,response);
            return;
        }
        System.out.println("path------>:"+path);
        Object userInfo = session.getAttribute("userInfo");
        if(userInfo==null) //没有登录
        {
            //跳转到登录界面
            response.sendRedirect(request.getContextPath()+"/jsp/Login.jsp");
            return;
        }
        else { //放行
            //3.放行的操作
            chain.doFilter(request,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
