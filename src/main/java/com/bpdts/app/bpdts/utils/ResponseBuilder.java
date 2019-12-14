package com.bpdts.app.bpdts.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class ResponseBuilder {

    private static final String SUCCESS = "success";
    private static final String DATA = "data";

    public ResponseEntity success(Object object) {

        HashMap<String, Object> map = new HashMap<>(2);

        map.put(SUCCESS, true);
        map.put(DATA, object);

        return (new ResponseEntity<>(map, HttpStatus.OK));

    }
}
