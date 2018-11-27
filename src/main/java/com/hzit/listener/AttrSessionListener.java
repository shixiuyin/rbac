package com.hzit.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 监听session域 属性的增删改
 */
public class AttrSessionListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        // 添加属性

        //1.时间  ip  内容  用户
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();

        //格式化时间
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        System.out.println(format+"---->:"+"name="+name+" value:"+value);


    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        //移除属性

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        //修改属性 (set重复的key)
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();

        System.out.println("replace前---->"+name+" "+value);

        //替换后的值
        Object attribute = httpSessionBindingEvent.getSession().getAttribute(name);
        System.out.println("replica后----->"+attribute);

    }
}
