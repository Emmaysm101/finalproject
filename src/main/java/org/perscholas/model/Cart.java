package org.perscholas.model;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne (targetEntity = Users.class, cascade = {CascadeType.PERSIST})
    @JoinColumn(name ="userId", referencedColumnName="userId")
    private Users users;

    @ManyToOne (targetEntity = Items.class, cascade = {CascadeType.PERSIST})
    @JoinColumn(name ="itemId", referencedColumnName="itemId" )
    private Items items;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "orderQuantity")
    private int orderQuantity;

    @Column(name = "orderId")
    private Long orderId;

    public Cart() {
    }

    public Cart(Users users, Items items, String itemName, int orderQuantity) {
        this.users = users;
        this.items = items;
        this.itemName = itemName;
        this.orderQuantity = orderQuantity;
    }

    public Cart(Users users, Items items, String itemName, int orderQuantity, Long orderId) {
        this.users = users;
        this.items = items;
        this.itemName = itemName;
        this.orderQuantity = orderQuantity;
        this.orderId = orderId;
    }

    public Cart(Long cartId, Users users, Items items, String itemName, int orderQuantity, Long orderId) {
        this.cartId = cartId;
        this.users = users;
        this.items = items;
        this.itemName = itemName;
        this.orderQuantity = orderQuantity;
        this.orderId = orderId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Users getUsers() {
        return users;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
