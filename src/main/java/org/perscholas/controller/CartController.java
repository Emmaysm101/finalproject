package org.perscholas.controller;


import org.perscholas.model.*;
import org.perscholas.service.CartService;
import org.perscholas.service.ItemsService;
import org.perscholas.service.OrdersService;
import org.perscholas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ItemsService itemService;

    @Autowired
    OrdersService ordersService;

    @GetMapping("/cart")
    public String cartHome(Model model, Authentication authentication) {
        Orders pendingOrder = ordersService.getPendingOrder(authentication);
        if(pendingOrder != null) {
            List<Cart> listCartItems = cartService.getAllCartItemsByOrderId(pendingOrder.getOrderId());
            model.addAttribute("listCartItems", listCartItems);
        }
        return "cart";
    }

    @GetMapping("/showFormForUpdateCart/{id}")
    public String showFormForUpdateCart(@PathVariable(value = "id") long id, Model model) {
        Cart cart = cartService.getCartById(id);
        model.addAttribute("cart", cart);
        return "update_cart";
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable(value = "id") long id) {
        this.cartService.deleteCartById(id);
        return "redirect:/cart";
    }

    @PostMapping("/saveCart")
    public String saveCart(Cart cart) {
        cartService.saveCart(cart);
        return "redirect:/cart";
    }

}
