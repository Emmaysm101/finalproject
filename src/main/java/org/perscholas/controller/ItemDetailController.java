package org.perscholas.controller;

import org.perscholas.model.Items;
import org.perscholas.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemDetailController {

    @Autowired
    private ItemsService itemService;

    @GetMapping("/itemDetail/{id}")
    public String showItemDetail(@PathVariable(value = "id")long id, Model model ) {
        Items items = itemService.getItemById(id);
        model.addAttribute("items", items);
        return "itemDetail";
    }
}
