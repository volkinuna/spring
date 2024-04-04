package com.example.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    //모든 도메인 뒤에 '/'가 있으나 생략됨
    @GetMapping(value = "/") //localhost로 접속
    public String index() {
        return "index";
    }

    @GetMapping(value = "/view") //경로 : localhost/view
    public String view() {
        return "post/view";
    }

    @GetMapping(value = "/list") //경로 : localhost/list
    public String list() {
        return "post/list";
    }

    @GetMapping(value = "/write") //경로 : localhost/write
    public String write() {
        return "post/write";
    }

    @GetMapping(value = "/rewrite") //경로 : localhost/rewrite
    public String rewrite() {
        return "post/rewrite";
    }
}

