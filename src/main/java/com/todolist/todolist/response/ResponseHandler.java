package com.todolist.todolist.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, Boolean isSuccess, HttpStatus status, Object responseObj){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isSuccess",isSuccess);
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",responseObj);
        return new ResponseEntity<Object>(map,status);
    }
}
