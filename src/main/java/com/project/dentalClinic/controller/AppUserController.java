package com.project.dentalClinic.controller;

import com.project.dentalClinic.entities.AppUser;
import com.project.dentalClinic.security.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @GetMapping
    public ResponseEntity<?> user() throws Exception {

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser user = new AppUser();
        user.setName(userDetails.getUsername());

        return ResponseEntity.ok(user);
    }
}
