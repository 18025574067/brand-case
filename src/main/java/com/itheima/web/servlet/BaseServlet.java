package com.itheima.web.servlet;

import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet, 根据请求路径最后一段路径来进行方法分发
 */
public class BaseServlet extends HttpServlet {

    // 根据请求路径最后一段路径来进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取请求路径
        String uri = req.getRequestURI();  // /brand-case/brand/selectAll
        // System.out.println(uri);

        // 获取最后一段路径，方法名
        int index = uri.lastIndexOf("/");
        String methodName = uri.substring(index + 1);  // selectAll?  /selectAll?
        // System.out.println(methodName);

        // 2 执行方法
        // 2.1 获取BrandServlet UserServlet字节码对象  .class
        // this 谁调用我(this 所在的方法)，我(this)代表谁
        // System.out.println(this); // BrandServlet? HttpServlet?  BaseServlet?    BrandServlet!
        Class<? extends BaseServlet> cls = this.getClass();

        // 2.2 获取方法
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 2.3 执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
