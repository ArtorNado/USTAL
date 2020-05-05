package com.filters;

import com.security.jwt.authentication.JwtAuthentication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
public class TransactionFilter extends GenericFilterBean {

    @Override
    public void doFilter(
    ServletRequest request,
    ServletResponse response,
    FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(req.authenticate((HttpServletResponse) response)){
            System.out.println("NE_PIZDEC");
            chain.doFilter(request, res);
        } else{
            System.out.println("PIZDEC");
        }
    }*/


    // other methods
