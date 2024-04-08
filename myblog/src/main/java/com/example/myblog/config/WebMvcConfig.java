package com.example.myblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration //하나 이상의 Bean을 정의하고 관리하는 클래스임을 나타냄(스프링에서 의존성 관리를 해줌)
public class WebMvcConfig implements WebMvcConfigurer {

    //String uploadPath = "file:///C:/blog/post/"; 와 같다.
    @Value("${uploadPath}")
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //localhost/images로 시작하는 모든 경로의 파일들은 uploadPath에서 찾아온ㄷ.
        //uploadPath에 이미지가 있어야한다.
        registry.addResourceHandler("/images/**") //** : images 폴더의 모든 하위 경로
                .addResourceLocations(uploadPath); //이미지 경로를 우회시킨다.
    }

    @Bean
    MappingJackson2JsonView jsonView() { //데이터를 json 객체로 리턴해준다.
        return new MappingJackson2JsonView();
    }
}
