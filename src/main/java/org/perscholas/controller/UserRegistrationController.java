package org.perscholas.controller;

import org.perscholas.controller.dto.UserRegistrationDto;
import org.perscholas.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UsersService usersService;

    public UserRegistrationController(UsersService usersService) {
        this.usersService = usersService;
    }
    @ModelAttribute("users")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("users") UserRegistrationDto userRegistrationDto) {
        usersService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
