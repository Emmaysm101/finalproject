package org.perscholas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    private Cart cart;

}
