package com.project.dentalClinic.controller;

import com.project.dentalClinic.entities.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {

    @GetMapping("/user")
    public ResponseEntity<?> user() throws Exception {

        UserDetails userDatails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser user = new AppUser();
        user.setName(userDatails.getUsername());

        return ResponseEntity.ok(user);
    }
}
