package org.perscholas.service;

import org.perscholas.model.Cart;

import org.perscholas.model.Items;
import org.perscholas.model.Users;

import java.util.List;

public interface CartService {
    public List<Cart> listCartItems(Users users);

    void saveCart(Cart cart);

    List<Cart> getAllCartItemsByOrderId(Long id);

    Items getItemById(long id);

    Users getUserById(long id);

    Cart getCartById(long id);

    void deleteCartById(long id);



}
