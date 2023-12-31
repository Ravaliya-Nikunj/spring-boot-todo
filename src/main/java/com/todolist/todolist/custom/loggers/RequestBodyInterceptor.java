package com.todolist.todolist.custom.loggers;

import com.todolist.todolist.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

@ControllerAdvice
public class RequestBodyInterceptor extends RequestBodyAdviceAdapter {

    @Autowired
    LoggingService loggingService;

    @Autowired
    HttpServletRequest request;

    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,MethodParameter parameter,Type targetType,Class<? extends HttpMessageConverter<?>> converterType) {
        loggingService.displayReq(request,body);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
}
