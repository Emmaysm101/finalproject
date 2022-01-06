package org.perscholas.controller;

import org.perscholas.controller.dto.UserRegistrationDto;
import org.perscholas.model.Users;
import org.perscholas.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

//    @PostMapping
//    public String registerUserAccount(@ModelAttribute("users")  UserRegistrationDto userRegistrationDto) {
//        usersService.save(userRegistrationDto);
//        return "redirect:/registration?success";
//    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("users") @Valid UserRegistrationDto userRegistrationDto,
                                      BindingResult result) {

        Users users = usersService.getUserById(userRegistrationDto.getUserId());
        if (users != null) {
            result.rejectValue("userId", null, "This ID is already exist");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        usersService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
