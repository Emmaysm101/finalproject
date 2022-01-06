package org.perscholas.finalproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.perscholas.model.Role;
import org.perscholas.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void saveRoleTest() {
        Role role = Role.builder()
                .id(1L)
                .roleName("Customer")
                .build();
        roleRepository.save(role);

        Assertions.assertThat(role.getId()).isGreaterThan(0);
    }
}
