package org.perscholas.service;

import org.perscholas.model.Cart;
import org.perscholas.model.Items;
import org.perscholas.model.Users;
import org.perscholas.repository.CartRepository;
import org.perscholas.repository.ItemsRepository;
import org.perscholas.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImplement implements CartService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CartRepository cartRepository;

    public CartServiceImplement(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCartItemsByOrderId(Long id) {
        return cartRepository.findAllByOrders_OrderId(id);
    }

    @Override
    public Items getItemById(long id) {
        Optional<Items> optional = itemsRepository.findById(id);
        Items items = null;
        if (optional.isPresent()) {
            items = optional.get();
        } else {
            throw new RuntimeException("Item not found for id");
        }
        return items;
    }


    @Override
    public Cart getCartById(long id) {
        Optional<Cart> optional = cartRepository.findById(id);
        Cart cart = null;
        if (optional.isPresent()) {
            cart = optional.get();
        } else {
            throw new RuntimeException("Cart not found for id");
        }
        return cart;
    }

    @Override
    public void deleteCartById(long id) {
        this.cartRepository.deleteById(id);
    }
}
