package com.estsoft.blogproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class FilterTestController {
    @ResponseBody
    @GetMapping("/filter/test")
    public void test(HttpServletRequest request){
        String transactionId = (String) request.getAttribute("traceId");
        log.info(transactionId);
    }
}
