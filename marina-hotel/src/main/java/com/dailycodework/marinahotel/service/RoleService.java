package com.dailycodework.marinahotel.service;

import com.dailycodework.marinahotel.exception.RoleAlreadyExistException;
import com.dailycodework.marinahotel.exception.RoleNotFoundException;
import com.dailycodework.marinahotel.exception.UserAlreadyExistsException;
import com.dailycodework.marinahotel.exception.UserRoleMismatchException;
import com.dailycodework.marinahotel.model.Role;
import com.dailycodework.marinahotel.model.User;
import com.dailycodework.marinahotel.repository.RoleRepository;
import com.dailycodework.marinahotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository ;
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role theRole) {
        String roleName = "ROLE_"+theRole.getName().toUpperCase();
        Role role = new Role(roleName);
        if (roleRepository.existsByName(roleName)){
            throw new RoleAlreadyExistException(theRole.getName()+" role already exists");
        }
        return roleRepository.save(role) ;
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUsersFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByName(String name) throws RoleNotFoundException {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with name: " + name));
    }


    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + roleId));

        if (role.getUsers().contains(user)) {
            role.removeUserFromRole(user);
            roleRepository.save(role);
            return user;
        } else {
            throw new UserRoleMismatchException("User is not assigned to the role");
        }
    }


    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Role> roleOptional = roleRepository.findById(roleId);

        userOptional.ifPresent(user -> roleOptional.ifPresent(role -> {
            if (user.getRoles().contains(role)) {
                throw new UserAlreadyExistsException(
                        user.getFirstName() + " is already assigned to the " + role.getName() + " role");
            } else {
                role.assignRoleToUser(user);
                roleRepository.save(role);
            }
        }));

        return userOptional.orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
    }


    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + roleId));

        role.removeAllUsersFromRole();
        return roleRepository.save(role);
    }

}
