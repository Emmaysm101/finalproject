package org.perscholas.service;


import org.perscholas.model.UsersRoles;

import java.util.List;

public interface UsersRolesService {
    List<UsersRoles> getAllUsersRoles();

    void saveUsersRoles(UsersRoles usersRoles);
    UsersRoles getUsersRolesById(long userId);
}
