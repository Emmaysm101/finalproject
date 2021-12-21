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
        ordersService.saveOrder(orders);
        return "redirect:/orderList";
    }

    @GetMapping("/showFormForUpdateOrder/{id}")
    public String showFormForUpdateOrder(@PathVariable(value = "id") long id, Model model) {
        Orders orders = ordersService.getOrderById(id);
        model.addAttribute("orders", orders);
        return "update_order";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable(value = "id") long id) {
        this.ordersService.deleteOrderById(id);
        return "redirect:/orderList";
    }

    @PostMapping("/saveCustomerOrder/{id}")
    public String saveCustomerOrder(@PathVariable(value = "id") long id, Cart cart, Authentication authentication) {
        Users user = usersService.getCurrentlyLoggedInCustomer(authentication);
        Long userNum = user.getUserNum();
        List<Orders> orders = ordersService.getOrderByUserNum(userNum);
        Items items = itemsService.getItemById(id);
        Long itemId = items.getItemId();
        int orderQuantity = cart.getOrderQuantity();
        String itemName = items.getItemName();


        if (orders.size() == 0) {
            Orders orders1 = new Orders(userNum, "Pending");
            ordersService.saveOrder(orders1);
            Long orders1OrderId = orders1.getOrderId();
            Cart tempCart = new Cart(user, items, itemName, orderQuantity, orders1OrderId);
            cartService.saveCart(tempCart);
        } else {
            for (int i = 0; i < orders.size(); i++) {
                if ((orders.get(i).getOrderStatus()).equals("Pending")) {
                    Long orderId = orders.get(i).getOrderId();
                    Cart tempCart = new Cart(user, items, itemName, orderQuantity, orderId);

                    List<Cart> cartItemsList = cartService.listCartItems(user);
                    if (cartItemsList.size() != 0) {
                        boolean exists = false;
                        for (int j = 0; j < cartItemsList.size(); j++) {
                            if ((cartItemsList.get(j).getItemName()).equals(itemName)) {
                                exists = true;
                                cartItemsList.get(j).setOrderQuantity(cartItemsList.get(j).getOrderQuantity() + orderQuantity);
                                cartService.saveCart(cartItemsList.get(j));
                            }
                        }
                        if (!exists) {
                            cartService.saveCart(tempCart);
                        }
                    }
                } else {
                    Orders orders1 = new Orders(userNum, "Pending");
                    ordersService.saveOrder(orders1);
                    Long orders1OrderId = orders1.getOrderId();
                    Cart tempCart = new Cart(user, items, itemName, orderQuantity, orders1OrderId);
                    cartService.saveCart(tempCart);
                }
            }
        }
        return "redirect:/cart";
    }
    @GetMapping("/checkOut")
    public String checkOut(Model model, Authentication authentication) {
        Users user = usersService.getCurrentlyLoggedInCustomer(authentication);
        Long userNum = user.getUserNum();
        List<Orders> ordersList = ordersService.getOrderByUserNum(userNum);

        for (int i=0; i<ordersList.size(); i++) {
            if ((ordersList.get(i).getOrderStatus()).equals("Pending")) {
                Long orderId = ordersList.get(i).getOrderId();
                Orders orders = ordersService.getOrderById(orderId);
                orders.setOrderStatus("Completed");
                ordersService.saveOrder(orders);
            }
        }

//        List<Cart> cartItemsByUser = cartService.listCartItems(user);
//        for (int i=0; i<cartItemsByUser.size(); i++) {
//
//        }
        model.addAttribute("orderList", ordersList);
        return "checkOut";
    }
}
