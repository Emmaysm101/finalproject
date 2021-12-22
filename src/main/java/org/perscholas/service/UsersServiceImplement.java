package org.perscholas.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.perscholas.controller.dto.UserRegistrationDto;
import org.perscholas.model.Role;
import org.perscholas.model.Users;
import org.perscholas.repository.RoleRepository;
import org.perscholas.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImplement implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public UsersServiceImplement(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void saveUser(Users users) {
        this.usersRepository.save(users);
    }

    @Override
    public Users getUserById(long id) {
        Optional<Users> optional = usersRepository.findById(id);
        Users users = null;
        if (optional.isPresent()) {
            users = optional.get();
        } else {
            throw new RuntimeException("User not found for id");
        }
        return users;
    }

    @Override
    public void deleteUserById(long id) {
        this.usersRepository.deleteById(id);
    }

    @Override
    public Users save(UserRegistrationDto registrationDto) {
        Optional<Role> role = roleRepository.findById(1l);
        Users users = new Users(registrationDto.getUserId(), passwordEncoder.encode(registrationDto.getUserPw()),
                registrationDto.getUserName(), registrationDto.getUserAddress(), Arrays.asList(role.get()));
        return usersRepository.save(users);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserId(username);
        if (users == null) {
            throw new UsernameNotFoundException("Invalid ID or Password");
        }
        return new org.springframework.security.core.userdetails.User(users.getUserId(), users.getUserPw(), mapRolesToAuthorities(users.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    public Users getCurrentlyLoggedInCustomer(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        Users users = null;
        Object principal = authentication.getPrincipal();

//        if(principal instanceof UserDetails) {
            String username= ((UserDetails) principal).getUsername();
            users = usersRepository.findByUserId(username);
//        }else {
//            String username = principal.toString();
//            users = usersRepository.findByUserId(username);
//        }

        return users;
    }
}
