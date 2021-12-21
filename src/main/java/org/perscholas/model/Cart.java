package org.perscholas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Orders orders;



}
