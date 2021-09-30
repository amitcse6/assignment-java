package com.am.assignment.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private String message;

    public CustomAccessDeniedHandler(String message) {
        this.message = message;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.access.AccessDeniedException e) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 403);
        map.put("msg", message);
        map.put("path", httpServletRequest.getServletPath());
        map.put("timestamp", System.currentTimeMillis());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(httpServletResponse.getOutputStream(), map);
        } catch (Exception ex) {
            throw new ServletException();
        }
//        httpServletResponse.getWriter().print("You don't have required role to perform this action.");
    }
}
