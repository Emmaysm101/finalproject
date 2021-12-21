package org.perscholas.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderId")
    private Long orderId;

    @Column(name="userNum")
    private Long userNum;

    @Column(name="orderStatus")
    private String orderStatus;

    public Orders() {
    }

    public Orders(Long userNum,  String orderStatus) {
        this.userNum = userNum;
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


}
