package com.api.bank.controllers.helloworld;

import org.springframework.web.bind.annotation.RestController;


public class HelloWorld {

    private final String content;

    public HelloWorld(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
