package org.perscholas.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.perscholas.model.Cart;
import org.perscholas.model.Items;
import org.perscholas.model.Orders;
import org.perscholas.repository.CartRepository;
import org.perscholas.service.CartServiceImplement;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    private CartServiceImplement CartServiceImplement;

    @BeforeEach
    void setUp() {
        Items items = new Items();
        Orders orders = new Orders();
        Cart cart = Cart.builder()
                .cartId(999L)
                .items(items)
                .orderQuantity(1)
                .subTotal(65.0)
                .orders(orders).build();

        Mockito.when(cartRepository.findById(999L)).thenReturn(Optional.of(cart));
    }

    @Test
    @DisplayName("Get Data Based on Cart ID")
    public void testGetCartById() {
        Long cartId = 999L;
        Cart found = CartServiceImplement.getCartById(cartId);

        assertEquals(cartId, found.getCartId());

    }
}
