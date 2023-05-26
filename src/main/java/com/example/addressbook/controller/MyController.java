package com.example.addressbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.addressbook.model.Info;

import jakarta.validation.Valid;

@Controller

public class MyController {
    
    @GetMapping("/home")
    public String showHome(){
        return "home";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        var userInfo = new Info();
        model.addAttribute("info", userInfo);
        return "signup";
    }

    @PostMapping("/signup/save")
    public String save(@ModelAttribute @Valid Info userInfo, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.toString());
            return "signup";
        }

        return "save";
    }
}
