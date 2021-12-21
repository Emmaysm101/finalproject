package org.perscholas.service;


import org.perscholas.model.Orders;
import org.perscholas.model.Users;
import org.perscholas.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplement implements OrdersService{

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void saveOrder(Orders orders) {
        this.ordersRepository.save(orders);

    }

    @Override
    public Orders getOrderById(long id) {
        Optional<Orders> optional = ordersRepository.findById(id);
        Orders orders = null;
        if(optional.isPresent()) {
            orders = optional.get();
        }else {
            throw new RuntimeException("Order not found for id");
        }
        return orders;
    }

    @Override
    public void deleteOrderById(long id) {
        this.ordersRepository.deleteById(id);
    }

    @Override
    public List<Orders> getOrderByUserNum(long id) {
        List<Orders> orderList = ordersRepository.findByUserNum(id);
        return orderList;
    }
}
