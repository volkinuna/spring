package com.example.springbasic.ch01;

import com.example.springbasic.service.MyService;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    //직접 bean 등록
    @Bean
    public MyBean myBean() {
        return new MyBean(); //spring container로 객체 return
    }

    @Bean
    public MyService myService() {
        return new MyService();
    }
}