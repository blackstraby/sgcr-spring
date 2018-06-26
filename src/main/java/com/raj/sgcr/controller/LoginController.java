package com.raj.sgcr.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @GetMapping(value = "")
    public String login(Model model){
        return "/auth/login";
    }

}