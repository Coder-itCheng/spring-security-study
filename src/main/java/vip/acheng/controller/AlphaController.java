package vip.acheng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlphaController {

    @GetMapping("hello")
    public String hello() {
        return "hello spring security!";
    }
    
}
