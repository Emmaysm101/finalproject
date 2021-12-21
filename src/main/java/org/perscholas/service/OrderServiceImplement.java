package org.perscholas.service;


import org.perscholas.model.Cart;
import org.perscholas.model.Items;
import org.perscholas.model.Orders;
import org.perscholas.model.Users;
import org.perscholas.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplement implements OrdersService {

    @Autowired
    UsersService usersService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    ItemsService itemsService;

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void saveOrder(long id, int quantity, Authentication authentication) {
        Users user = usersService.getCurrentlyLoggedInCustomer(authentication);

        Long userNum = user.getUserNum();
        List<Orders> orders = getOrderByUserNum(userNum);
        Items items = itemsService.getItemById(id);
        if (orders.isEmpty() || orders.stream().allMatch(order -> order.getOrderStatus().equals("Complete"))) {
            Orders tempOrder = new Orders();
            tempOrder.setUsers(user);
            tempOrder.setOrderStatus("Pending");

            Cart tempCart = new Cart();
            tempCart.setItems(items);
            tempCart.setOrders(tempOrder);
            tempCart.setOrderQuantity(quantity);

            tempOrder.setCart(new ArrayList<>());
            tempOrder.getCart().add(tempCart);

            this.ordersRepository.save(tempOrder);
        } else {
            Orders pendingOrder = orders.stream().filter(order -> order.getOrderStatus().equals("Pending")).findFirst().get();
            Cart tempCart = new Cart();
            tempCart.setItems(items);
            tempCart.setOrders(pendingOrder);
            tempCart.setOrderQuantity(quantity);
            cartService.saveCart(tempCart);
        }
    }

    @Override
    public Orders getOrderById(long id) {
        Optional<Orders> optional = ordersRepository.findById(id);
        Orders orders = null;
        if (optional.isPresent()) {
            orders = optional.get();
        } else {
            throw new RuntimeException("Order not found for id");
        }
        return orders;
    }

    @Override
    public void deleteOrderById(long id) {
//        this.ordersRepository.deleteById(id);
    }

    @Override
    public List<Orders> getOrderByUserNum(long id) {
        List<Orders> orderList = ordersRepository.findByUsers_UserNum(id);
        return orderList;
    }

    @Override
    public Orders getPendingOrder(Authentication authentication) {
        Users user = usersService.getCurrentlyLoggedInCustomer(authentication);
        Long userNum = user.getUserNum();
        List<Orders> orders = getOrderByUserNum(userNum);
        return orders.stream().filter(order -> order.getOrderStatus().equals("Pending")).findFirst().orElse(null);
    }

}
