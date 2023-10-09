package com.todolist.todolist.service.impl;

import com.todolist.todolist.service.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class LoggingServiceImpl implements LoggingService {
    Logger logger = LoggerFactory.getLogger("LoggingServiceImpl");

    @Override
    public void displayReq(HttpServletRequest request, Object body) {
        StringBuilder reqMessage = new StringBuilder();
        Map<String,String> parameters = getParameters(request);
        reqMessage.append("REQUEST::::::::");
        reqMessage.append(" METHOD = [").append(request.getMethod()).append("]");
        reqMessage.append(" PATH = [").append(request.getRequestURI()).append("]");

        if (!parameters.isEmpty()){
            reqMessage.append(" PARAMETERS =[").append(parameters).append("]");
        }
        if(!Objects.isNull(body)){
            reqMessage.append(" BODY :[").append(body).append("]");
        }
        logger.info("LOG REQUEST : {}",reqMessage);
    }

    @Override
    public void displayResp(HttpServletRequest request, HttpServletResponse response, Object body) {
        StringBuilder respMessage = new StringBuilder();
        Map<String,String> headers = getHeaders(response);
        respMessage.append("RESPONSE::::::::");
        respMessage.append(" METHOD = [").append(request.getMethod()).append("]");
        if(!headers.isEmpty()) {
            respMessage.append(" RESPONSE HEADERS = [").append(headers).append("]");
        }
        respMessage.append(" RESPONSE BODY = [").append(body).append("]");

        logger.info("LOG RESPONSE : {}",respMessage);
    }

    private Map<String, String> getHeaders(HttpServletResponse response) {
        Map<String,String> headers = new HashMap<>();
        Collection<String> headerMap = response.getHeaderNames();
        for(String str : headerMap) {
            headers.put(str,response.getHeader(str));
        }
        return headers;
    }

    private Map<String, String> getParameters(HttpServletRequest request) {
        Map<String,String> parameters = new HashMap<>();
        Enumeration<String> params = request.getParameterNames() ;
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            String paramValue = request.getParameter(paramName);
            parameters.put(paramName,paramValue);
        }
        return parameters;
    }

}
