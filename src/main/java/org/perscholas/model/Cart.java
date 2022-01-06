package org.perscholas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne (targetEntity = Items.class, cascade = {CascadeType.PERSIST})
    @JoinColumn(name ="itemId", referencedColumnName="itemId")
    private Items items;

    @Column(name = "orderQuantity")
    private int orderQuantity;

    @Column(name = "subTotal")
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Orders orders;

    public Cart(Long cartId, Items items, int orderQuantity, Orders orders) {
        this.cartId = cartId;
        this.items = items;
        this.orderQuantity = orderQuantity;
        this.subTotal = orderQuantity * items.getItemPrice();
        this.orders = orders;
    }
}
