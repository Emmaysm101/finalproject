package org.perscholas.service;

import org.perscholas.model.Orders;
import org.perscholas.model.Users;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrdersService {
    List<Orders> getAllOrders();

    Orders getPendingOrder(Authentication authentication);

    void saveOrder(long id, int quantity, Authentication authentication);

    Orders getOrderById (long id);

    void deleteOrderById(long id);

    List<Orders> getOrderByUserNum(long id);
}
