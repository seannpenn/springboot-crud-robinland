package com.robinland.pos.RobinlandPos.functions.role.repository;

import com.robinland.pos.RobinlandPos.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
