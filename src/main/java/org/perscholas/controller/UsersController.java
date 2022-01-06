package org.perscholas.controller;

import org.perscholas.model.Users;
import org.perscholas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	

	@GetMapping("/userList")
	public String viewHomePage(Model model) {
		model.addAttribute("listUsers", usersService.getAllUsers());
		return "list_user";
	}
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "new_user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("users") Users users) {
		usersService.saveUser(users);
		return "redirect:/userList";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id")long id, Model model ) {
		Users users = usersService.getUserByUserNumb(id);
		
		model.addAttribute("users", users);
		return "update_user";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id")long id) {
		this.usersService.deleteUserById(id);
		return "redirect:/userList";
	}

}
