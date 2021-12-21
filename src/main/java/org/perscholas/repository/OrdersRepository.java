package org.perscholas.repository;




import org.perscholas.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    public List<Orders> findByUsers_UserNum(Long userNum);
}
