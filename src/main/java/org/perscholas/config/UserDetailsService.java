package org.perscholas.config;

import org.perscholas.model.Users;
import org.perscholas.config.UserDetails;
import org.perscholas.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Users users = usersRepository.findByUserId(userId);
		if (users != null ) {
			return new UserDetails(users);
		}
		throw new UsernameNotFoundException ("Could not find user ID: " + userId);
	}
	
}
