package org.perscholas.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.perscholas.model.Cart;
import org.perscholas.model.Items;
import org.perscholas.model.Orders;
import org.perscholas.model.UsersRoles;
import org.perscholas.repository.CartRepository;
import org.perscholas.repository.UsersRolesRepository;
import org.perscholas.service.CartServiceImplement;
import org.perscholas.service.UsersRolesServiceImplement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsersRolesServiceTest {

    @Mock
    UsersRolesRepository usersRolesRepository;

    @InjectMocks
    private UsersRolesServiceImplement usersRolesServiceImplement;

    @BeforeEach
    void setUp() {
        UsersRoles usersRoles = UsersRoles.builder()
                .userId(999L)
                .roleId(999L).build();

        Mockito.when(usersRolesRepository.findById(999L)).thenReturn(Optional.of(usersRoles));
    }

    @Test
    @DisplayName("Get Data Based on User Number")
    public void testGetUsersRolesById() {
        Long userId = 999L;
        UsersRoles found = usersRolesServiceImplement.getUsersRolesById(userId);

        assertEquals(userId, found.getUserId());

    }
}
