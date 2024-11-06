package com.robinland.pos.RobinlandPos.functions.role.service;

import com.robinland.pos.RobinlandPos.functions.role.dto.RoleRequestDto;
import com.robinland.pos.RobinlandPos.functions.role.repository.RoleRepository;
import com.robinland.pos.RobinlandPos.model.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements RoleServiceInterface{

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Optional<Role> getRole(Long roleId) throws Exception {
        Optional<Role> existingRole = roleRepository.findById(roleId);

        if(ObjectUtils.isEmpty(existingRole)){
            throw new Exception("No role found");
        }

        return existingRole;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Optional<Role> getRoleByName(String roleName) throws Exception {
        Optional<Role> existingRole = roleRepository.findByName(roleName);

        if(ObjectUtils.isEmpty(existingRole)){
            throw new Exception("No role found");
        }

        return existingRole;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Role addRole(RoleRequestDto requestDto) throws Exception {

        Role role = new Role();
        BeanUtils.copyProperties(requestDto, role);

        try {
            role = roleRepository.save(role);
        } catch (Exception e) {
            throw new Exception("Failed to add role data");
        }

        return role;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Role updateRole(Long roleId, RoleRequestDto requestDto) throws Exception {
        Role oldRole = new Role();
        Role updatedRole = new Role();
        Optional<Role> existingRole = roleRepository.findById(roleId);

        if(!ObjectUtils.isEmpty(existingRole)){
            BeanUtils.copyProperties(existingRole, oldRole);
            BeanUtils.copyProperties(requestDto, oldRole);
            BeanUtils.copyProperties(oldRole, updatedRole);

        }else{
            throw new Exception("Role not found");
        }

        updatedRole = roleRepository.save(updatedRole);

        return updatedRole;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteRole(Long roleId) throws Exception {
        Optional<Role> existingUser = roleRepository.findById(roleId);

        if(!ObjectUtils.isEmpty(existingUser)){
            roleRepository.deleteById(roleId);
        }else{
            throw new Exception("Role not found");
        }
    }
}
