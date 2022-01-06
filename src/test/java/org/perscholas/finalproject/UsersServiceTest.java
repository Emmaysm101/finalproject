package org.perscholas.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.perscholas.model.Users;
import org.perscholas.repository.RoleRepository;
import org.perscholas.repository.UsersRepository;
import org.perscholas.service.UsersServiceImplement;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @Mock
    UsersRepository usersRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    private UsersServiceImplement usersServiceImplement;

    @BeforeEach
    void setUp() {
        Users users = Users.builder()
                .userNum(999L)
                .userId("hello")
                .userPw("hello")
                .userName("hello")
                .userAddress("hello").build();

        Mockito.when(usersRepository.findById(999L)).thenReturn(Optional.of(users));
    }

    @Test
    @DisplayName("Get Data Based on User Number")
    public void testGetUserById() {
        Long userNum = 999L;
        Users found = usersServiceImplement.getUserByUserNumb(userNum);

        assertEquals(userNum, found.getUserNum());

    }
}
