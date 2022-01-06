package org.perscholas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderId")
    private Long orderId;

    @ManyToOne (targetEntity = Users.class, cascade = {CascadeType.PERSIST})
    @JoinColumn(name ="userNum", referencedColumnName="userNum")
    private Users users;

    @Column(name="orderStatus")
    private String orderStatus;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Cart> cart;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }
}
