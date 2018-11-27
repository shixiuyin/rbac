package com.hzit.filter;


import javax.servlet.*;
import java.io.IOException;

public class HelloFilter implements Filter {

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 每次请求都会到该方法下
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //ServletRequest:HttpServletRequest的父类
        //ServletResponse:HttpServletResponse的父类
        // FilterChain filterChain :过滤器的链条

        //重要的方法:满足要求，给目标请求放行
        //假设不放行:请求会一直堵塞等待

        ServletContext context = servletRequest.getServletContext();
        System.out.println("开始过滤Filter:"+context);

        //filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("结束过滤Filter:"+context);
    }

    /**
     *销毁
     */
    @Override
    public void destroy() {

    }
}
