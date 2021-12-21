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
    public List<Cart> listCartItems(Users users) {
        return cartRepository.findByUsers_UserId(users.getUserId());
    }

    @Override
    public void saveCart(Cart cart) {
        if (cart.getCartId() == null) {
            this.cartRepository.save(cart);
        } else {
            Optional<Cart> c = cartRepository.findById(cart.getCartId());
            if (c.isPresent()) {
                c.get().setOrderQuantity(cart.getOrderQuantity());
                this.cartRepository.save(c.get());
            }
        }
    }

    @Override
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
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
    public Users getUserById(long id) {
        Optional<Users> optional = usersRepository.findById(id);
        Users users = null;
        if (optional.isPresent()) {
            users = optional.get();
        } else {
            throw new RuntimeException("User not found for id");
        }
        return users;
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
