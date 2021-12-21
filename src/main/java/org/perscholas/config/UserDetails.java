package org.perscholas.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.perscholas.model.Role;
import org.perscholas.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

	private Users users;
	

	public UserDetails(Users users) {
		this.users = users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = users.getRoles();
		List <SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return users.getUserPw();
	}

	@Override
	public String getUsername() {
		return users.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
