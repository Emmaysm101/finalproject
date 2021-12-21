package org.perscholas.service;


import org.perscholas.model.Orders;
import org.perscholas.model.UsersRoles;
import org.perscholas.repository.UsersRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersRolesServiceImplement implements UsersRolesService{

    @Autowired
    private UsersRolesRepository usersRolesRepository;

    @Override
    public List<UsersRoles> getAllUsersRoles() {
        return usersRolesRepository.findAll();
    }

    @Override
    public void saveUsersRoles(UsersRoles usersRoles) {
        this.usersRolesRepository.save(usersRoles);

    }

    @Override
    public UsersRoles getUsersRolesById(long userId) {
        Optional<UsersRoles> optional = usersRolesRepository.findById(userId);
        UsersRoles usersRoles = null;
        if(optional.isPresent()) {
            usersRoles = optional.get();
        }else {
            throw new RuntimeException("Users not found for id");
        }
        return usersRoles;
    }
}
