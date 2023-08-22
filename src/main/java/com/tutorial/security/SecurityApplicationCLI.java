package com.tutorial.security;

import com.tutorial.security.models.Role;
import com.tutorial.security.models.User;
import com.tutorial.security.repositories.RoleRepository;
import com.tutorial.security.repositories.UserRepository;
import com.tutorial.security.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SecurityApplicationCLI implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleService service;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world from Command Line Runner");
        List<User> users = this.createUsers();
        List<Role> roles = this.createRoles();
        assignUsersRoles(users, roles);
    }

    List<User> createUsers() {
        List<User> userList = new ArrayList<>();
        long count = userRepository.count();

        String password = encoder.encode("123123");

        if (count <= 0) {
            userList.add(new User("adminz@gm.com", "adminz", password));
            userList.add(new User("editor@gm.com", "editor", password));
            userList.add(new User("visitor@gm.com", "visitor", password));
            userList.add(new User("user@gm.com", "user", password));
            userRepository.saveAll(userList);
        } else {
            userList = userRepository.findAll();
        }

        return userList;
    }

    List<Role> createRoles() {
        List<Role> roleList = roleRepository.findAll();

        if (roleList.size() == 0) {
            roleList = new ArrayList<>(){{
                add(new Role("ADMIN"));
                add(new Role("EDITOR"));
                add(new Role("VISITOR"));
                add(new Role("USER"));
            }};
            roleRepository.saveAll(roleList);
        }

        return roleList;
    }

    void assignUsersRoles(List<User> users, List<Role> roles) {
        service.assignUserRoles(users.get(0), new HashSet<>(roles));
        service.assignUserRole(users.get(1), roles.get(1));
        service.assignUserRole(users.get(2), roles.get(2));
        service.assignUserRole(users.get(3), roles.get(3));
    }
}
