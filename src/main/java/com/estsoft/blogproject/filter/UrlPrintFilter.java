package com.estsoft.blogproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class UrlPrintFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("url print filter init() {}",filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("do filter {}", httpServletRequest.getRequestURL().toString());
        request.setAttribute("traceId",UUID.randomUUID().toString());
        log.info("1 before {}",request.getAttribute("traceId"));
        chain.doFilter(request,response); //다음 필터로 넘어가기 위해서
        log.info("1 after");
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
        log.info("url print filter destroy()");

    }
}
