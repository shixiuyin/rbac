package com.hzit.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *监听application域的创建和销毁
 */
public class HelloListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //当创建application域对象的时候，自动调用该方法
        //整个只加载一次  作用:一般用来初始化项目，加载配置文件

        System.out.println("application.....创建成功!!!");


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("application.....开始销毁!!!");
    }
}
