package com.api.bank.BankApiLayer.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private static final String template = "Hello, %s!";

    @RequestMapping("/Hello-World")
    public HelloWorld helloWorld(
            @RequestParam(value = "Name", defaultValue = "World")
            String strContent) {
        return new HelloWorld(String.format(template, strContent));
    }

}


