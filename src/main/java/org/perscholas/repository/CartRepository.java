package org.perscholas.repository;

import org.perscholas.model.Cart;
import org.perscholas.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//    public List<Cart> findByUserId(Users users);
//    public List<Cart> findByUsers_UserId(String userId);
//    public Cart findByUserNumAndItemId(Long userNum, long itemId);
//
//    @Modifying
//    @Query("Update Cart c Set c.orderQuantity = ?1 Where c.users.userNum = ?2 And c.items.itemId = ?3")
//    public void updateQuantity(int quantity, Long userNum, Long itemId);
//
//    @Modifying
//    @Query("Delete From Cart c Where c.users.userNum = ?1 and c.items.itemId =?2")
//    public void deleteByUsersAndItems(Long userNum, Long itemId);
}
