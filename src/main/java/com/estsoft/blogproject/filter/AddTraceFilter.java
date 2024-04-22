package com.estsoft.blogproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class AddTraceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("add trace filter init() {}",filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        request.setAttribute("traceId", UUID.randomUUID().toString());
        log.info("2 before {}",request.getAttribute("traceId"));
        chain.doFilter(request,response);
        log.info("2 after");
    }

    @Override
    public void destroy() {
        log.info("add trace filter destroy()");
    }
}
