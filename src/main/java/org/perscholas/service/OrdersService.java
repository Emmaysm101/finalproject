package org.perscholas.service;

import org.perscholas.model.Orders;
import org.perscholas.model.Users;

import java.util.List;

public interface OrdersService {
    List<Orders> getAllOrders();

    void saveOrder(Orders orders);

    Orders getOrderById (long id);

    void deleteOrderById(long id);

    List<Orders> getOrderByUserNum(long id);
}
