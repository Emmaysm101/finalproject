package org.perscholas.controller;

import org.perscholas.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ItemsService itemService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("listItems", itemService.getAllItems());
        return "index";
    }


}
