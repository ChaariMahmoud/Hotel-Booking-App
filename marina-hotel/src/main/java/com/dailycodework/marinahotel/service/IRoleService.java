package com.dailycodework.marinahotel.service;

import com.dailycodework.marinahotel.model.Role;
import com.dailycodework.marinahotel.model.User;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);
    Role findByName(String name) throws RoleNotFoundException;

    User removeUserFromRole(Long userId, Long roleId);
    User assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}
