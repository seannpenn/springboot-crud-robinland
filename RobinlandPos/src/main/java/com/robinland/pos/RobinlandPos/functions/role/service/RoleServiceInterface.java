package com.robinland.pos.RobinlandPos.functions.role.service;

import com.robinland.pos.RobinlandPos.functions.role.dto.RoleRequestDto;
import com.robinland.pos.RobinlandPos.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleServiceInterface {
    public List<Role> getAllRoles();

    public Optional<Role> getRole(Long roleId)  throws Exception;

    public Optional<Role> getRoleByName(String name)  throws Exception;

    public Role addRole(RoleRequestDto requestDto) throws Exception;

    public Role updateRole(Long roleId, RoleRequestDto requestDto) throws Exception;

    void deleteRole(Long roleId) throws Exception;
}
