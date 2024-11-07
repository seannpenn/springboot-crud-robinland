package com.robinland.pos.RobinlandPos.security;

import com.robinland.pos.RobinlandPos.functions.role.service.RoleService;
import com.robinland.pos.RobinlandPos.functions.user.repository.UserRepository;
import com.robinland.pos.RobinlandPos.model.Role;
import com.robinland.pos.RobinlandPos.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.robinland.pos.RobinlandPos.model.User user = userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return new User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(mapToRoles(user)));
    }

    private List<Role> mapToRoles(com.robinland.pos.RobinlandPos.model.User user) {
        return user.getRoles().stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
