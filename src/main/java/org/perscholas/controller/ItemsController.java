package org.perscholas.controller;

import org.perscholas.model.Items;
import org.perscholas.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ItemsController {
	
	@Autowired
	private ItemsService itemService;

	@GetMapping("/itemList")
	public String viewHomePage(Model model) {
		model.addAttribute("listItems", itemService.getAllItems());
		return "list_item";
	}
	
	@GetMapping("/showNewItemForm")
	public String showNewItemForm(Model model) {
		Items items = new Items();
		model.addAttribute("items", items);
		return "new_item";
	}
	
	@PostMapping("/saveItem")
	public String saveItem(@ModelAttribute("items") Items items) {
		itemService.saveItem(items);
		return "redirect:/itemList";
	}
	
	@GetMapping("/showFormForUpdateItem/{id}")
	public String showFormForUpdateItem(@PathVariable (value = "id")long id, Model model ) {
		Items items = itemService.getItemById(id);
		
		model.addAttribute("items", items);
		return "update_item";
	}

	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable (value = "id")long id) {
		this.itemService.deleteItemById(id);
		return "redirect:/itemList";
	}

}
