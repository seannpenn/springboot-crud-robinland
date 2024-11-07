package com.robinland.pos.RobinlandPos.functions.auth;

import com.robinland.pos.RobinlandPos.dto.LoginDto;
import com.robinland.pos.RobinlandPos.dto.RegisterDto;
import com.robinland.pos.RobinlandPos.functions.role.repository.RoleRepository;
import com.robinland.pos.RobinlandPos.functions.user.repository.UserRepository;
import com.robinland.pos.RobinlandPos.functions.userrole.repository.UserRoleRepository;
import com.robinland.pos.RobinlandPos.model.Role;
import com.robinland.pos.RobinlandPos.model.User;
import com.robinland.pos.RobinlandPos.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        try {
            Optional<User> user = userRepository.findByUserName(loginDto.getUserName());

            if(ObjectUtils.isEmpty(user)){
                throw new Exception("Username doesn't exist");
            }else{
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getUserName(),
                                loginDto.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

            return new ResponseEntity<>("User signed in successfully!", HttpStatus.OK);
        } catch (AuthenticationException e) {
            System.out.println("AuthenticationException " + e.getMessage());

            return new ResponseEntity<>("Password is incorrect!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUserName(registerDto.getUserName())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        UserRole userRole = new UserRole();
        user.setUserName(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role role = roleRepository.findByName("USER").isPresent() ? roleRepository.findByName("USER").get() : null;

        user = userRepository.save(user);
        userRole.setUser(user);
        userRole.setRole(role);
        try {
            userRoleRepository.save(userRole);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}

