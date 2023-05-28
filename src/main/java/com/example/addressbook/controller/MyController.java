package com.example.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.addressbook.model.Info;
import com.example.addressbook.repository.InfoRedis;

import jakarta.validation.Valid;

@Controller

public class MyController {

    @Autowired
    InfoRedis repository;

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        var userInfo = new Info();
        model.addAttribute("info", userInfo);
        return "signup";
    }

    @PostMapping("/signup/save")
    public String save(@ModelAttribute @Valid Info userInfo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        repository.saveInfo(userInfo);
        model.addAttribute("successMessage", "You have successfully signed up.");
        return "save";
    }

    // @GetMapping("/userlist")
    // public String userlist(Model model){
    // List<Info> infos = repository.getAllInfo();
    //
    // //System.out.println(infos);
    // return "userlist";
    // }

    @GetMapping("/user/{userid}")
    public String showUser(Model model, @PathVariable String userid) {
        Info user = new Info();
        user = repository.getInfoByID(userid);

        if (user == null) {
            System.out.println(user);
            return "error";
        }
        model.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            Model model) {
        System.out.println(username + ":" + password);
        List<Info> infos = repository.getAllInfo();
        for (Info info : infos) {
            if (username.equals(info.getUserName())) {
                if (password.equals(info.getPassword())) {
                    model.addAttribute("allinfo", infos);
                    return "userlist";
                }

                return "login";
            }
        }

        return "login";
    }
}
