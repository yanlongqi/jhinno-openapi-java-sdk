package com.jhinno.sdk.openapi.example;

import com.jhinno.sdk.openapi.JHApiRequestHandler;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig implements JHApiRequestHandler {

    @Override
    public String getCurrentUserName() {
        return "yanlongqi";
    }
}
