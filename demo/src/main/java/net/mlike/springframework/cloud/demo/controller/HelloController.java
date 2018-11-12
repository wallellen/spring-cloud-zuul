package net.mlike.springframework.cloud.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        if (System.currentTimeMillis() % 3 == 0) { // 随机性超时， 测试hystrix熔断
            Thread.sleep(10 * 1000);
        }
        return String.format("hello %s, now %s", applicationName, new Date().toString());
    }
}
