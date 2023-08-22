package com.tutorial.security.services;

import com.tutorial.security.models.Role;
import com.tutorial.security.models.User;
import com.tutorial.security.repositories.RoleRepository;
import com.tutorial.security.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public UserRoleServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User assignUserRoles(User user, Set<Role> roles) {
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public User assignUserRole(User user, Role role) {
        return this.assignUserRoles(user, new HashSet<>(){{
            add(role);
        }});
    }

    @Override
    public User assignUserRoleByNames(User user, List<String> names) {
        return null;
    }
}
