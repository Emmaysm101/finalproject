package org.perscholas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Cart> cart;

}
