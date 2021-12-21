package org.perscholas.controller;

import org.perscholas.model.Orders;
import org.perscholas.model.UsersRoles;
import org.perscholas.service.OrdersService;
import org.perscholas.service.UsersRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersRolesController {

    @Autowired
    private UsersRolesService usersRolesService;

    @GetMapping("/usersRolesList")
    public String viewHomePage(Model model) {
        model.addAttribute("listUsersRoles", usersRolesService.getAllUsersRoles());
        return "list_usersRoles";
    }
    @GetMapping("/showFormForUpdateUsersRoles/{id}")
    public String showFormForUpdateUsersRoles(@PathVariable(value = "id")long id, Model model ) {
        UsersRoles usersRoles = usersRolesService.getUsersRolesById(id);
        model.addAttribute("usersRoles", usersRoles);
        return "update_usersRoles";
    }
    @PostMapping("/saveUsersRoles")
    public String saveUsersRoles(@ModelAttribute("usersRoles") UsersRoles usersRoles) {
        usersRolesService.saveUsersRoles(usersRoles);
        return "redirect:/usersRolesList";
    }

}
