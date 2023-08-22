package com.tutorial.security.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('USER', 'EDITOR', 'VISITOR', 'ADMIN')")
    public String userAccess(Principal principal, Authentication authentication) {
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        System.out.println(authentication);
        return "User Content.";
    }

    @GetMapping("/editor")
    @PreAuthorize("hasAuthority('EDITOR') or hasAuthority('ADMIN')")
    public String editorAccess() {
        return "Editor Board.";
    }

    @GetMapping("/visitor")
    @PreAuthorize("hasAuthority('VISITOR') or hasAuthority('ADMIN')")
    public String visitorAccess() {
        return "Visitor Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
