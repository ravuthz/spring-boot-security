package com.tutorial.security.services;

import com.tutorial.security.models.Role;
import com.tutorial.security.models.User;

import java.util.List;
import java.util.Set;

public interface UserRoleService {
    User assignUserRoles(User user, Set<Role> roles);
    User assignUserRole(User user, Role role);
    User assignUserRoleByNames(User user, List<String> names);
}
