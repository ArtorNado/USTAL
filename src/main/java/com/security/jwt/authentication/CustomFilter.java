package com.security.jwt.authentication;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println("Role"+ req.isUserInRole("USER"));
        System.out.println("Role"+ req.isUserInRole("ADMIN"));
        if (req.authenticate(res) && !(req.getHeader("Authorization") == null)) chain.doFilter(request, response);
        else res.sendRedirect("/signIn");

    }

}
