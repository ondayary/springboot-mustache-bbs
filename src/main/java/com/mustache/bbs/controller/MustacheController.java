package com.mustache.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MustacheController {

    @GetMapping(value = "/hi") // mustacheCon 메서드가 /hi 라는 요청으로 동작한다.
    public String mustacheCon(Model model) {
        model.addAttribute("username", "daon"); // view에 값을 넘기겠다.
        return "greetings"; // mustacheCon 메서드는 반환값으로 greetings 페이지를 보여준다.
    }

    // PathVariable로부터 넘어온 값 넘기기
    @GetMapping(value = "/hi/{id}")
    public String mustacheCon2(@PathVariable String id, Model model) {
        model.addAttribute("username", "daon");
        model.addAttribute("id", id); // view에 값을 넘기겠다.
        return "greetings"; // greetings라는 이름의 view를 리턴
    }
}
