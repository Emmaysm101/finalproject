package org.perscholas.controller;

import org.perscholas.model.Cart;
import org.perscholas.model.Items;
import org.perscholas.model.Orders;
import org.perscholas.model.Users;
import org.perscholas.service.CartService;
import org.perscholas.service.ItemsService;
import org.perscholas.service.OrdersService;
import org.perscholas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private CartService cartService;

    @GetMapping("/orderList")
    public String viewHomePage(Model model) {
        model.addAttribute("listOrders", ordersService.getAllOrders());
        return "list_order";
    }

    @GetMapping("/showNewOrderForm")
    public String showNewOrderForm(Model model) {
        Orders orders = new Orders();
        model.addAttribute("orders", orders);
        return "new_order";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("orders") Orders orders) {
//        ordersService.saveOrder(orders);
        return "redirect:/orderList";
    }

    @GetMapping("/showFormForUpdateOrder/{id}")
    public String showFormForUpdateOrder(@PathVariable(value = "id") long id, Model model) {
//        Orders orders = ordersService.getOrderById(id);
//        model.addAttribute("orders", orders);
        return "update_order";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable(value = "id") long id) {
//        this.ordersService.deleteOrderById(id);
        return "redirect:/orderList";
    }

    @PostMapping("/saveCustomerOrder/{id}")
    public String saveCustomerOrder(@PathVariable(value = "id") long id,Cart cart, Authentication authentication) {
        ordersService.saveOrder(id, cart.getOrderQuantity(), authentication);
        return "redirect:/cart";
    }
    @GetMapping("/checkOut")
    public String checkOut(Model model, Authentication authentication) {
        return "checkOut";
    }
}
