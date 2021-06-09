package com.example.InfoManagement.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于 登录校验 的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req= (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String uri = req.getRequestURI();
        if (uri.substring(uri.lastIndexOf("/")).endsWith("login.jsp") || uri.equals("/loginController") || isLogin(req)){
            chain.doFilter(req, resp);
        }else {
            if (!uri.equals("/"))
                req.setAttribute("msg", "请登录后访问授权页面!");
            req.getRequestDispatcher("/jsp/admin/login.jsp").forward(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    private boolean isLogin(HttpServletRequest req){
        return req.getSession().getAttribute("loginFlag")!=null;
    }
}
