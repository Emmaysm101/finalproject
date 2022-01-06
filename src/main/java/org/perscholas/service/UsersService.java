package org.perscholas.service;

import java.util.List;

import org.perscholas.controller.dto.UserRegistrationDto;
import org.perscholas.model.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
	
	List<Users> getAllUsers();
	
	void saveUser(Users users);
	
	Users getUserByUserNumb(long id);

	void deleteUserById(long id);

	Users save(UserRegistrationDto registrationDto);

	public Users getCurrentlyLoggedInCustomer(Authentication authentication);

	Users getUserById(String userId);
}
