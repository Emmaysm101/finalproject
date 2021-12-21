package org.perscholas.repository;


import org.perscholas.model.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
}
