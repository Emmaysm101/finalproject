package org.perscholas.controller;

import org.perscholas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/login")
    public String signinHome() {
        return "signin";
    }


}
