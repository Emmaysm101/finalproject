package org.perscholas.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.perscholas.model.Orders;
import org.perscholas.model.Users;
import org.perscholas.repository.OrdersRepository;
import org.perscholas.service.OrderServiceImplement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    OrdersRepository ordersRepository;

    @InjectMocks
    private OrderServiceImplement orderServiceImplement;

    @BeforeEach
    void setUp() {
        Users users = new Users();
        Orders orders = Orders.builder()
                .orderId(999L)
                .users(users)
                .orderStatus("Pending").build();

        Mockito.when(ordersRepository.findById(999L)).thenReturn(Optional.of(orders));
    }

    @Test
    @DisplayName("Get Data Based on Order ID")
    public void testGetOrderById() {
        Long orderId = 999L;
        Orders found = orderServiceImplement.getOrderById(orderId);

        assertEquals(orderId, found.getOrderId());

    }
}
