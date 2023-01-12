package com.project.dentalClinic.security.config;

import com.project.dentalClinic.entities.AppUser;
import com.project.dentalClinic.entities.AppUserRole;
import com.project.dentalClinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AppUser user = new AppUser();
        AppUser userAdmin = new AppUser();
        String hashedPassword = passwordEncoder.encode("password");
        user.setName("Benji");
        user.setUsername("BenjiB");
        user.setPassword(hashedPassword);
        user.setEmail("Benji@digitalhouse.com");
        user.setAppUserRole(AppUserRole.USER);

        userAdmin.setName("Danna");
        userAdmin.setUsername("DannaV");
        userAdmin.setPassword(hashedPassword);
        userAdmin.setEmail("DannaV@digitalhouse.com");
        userAdmin.setAppUserRole(AppUserRole.ADMIN);

        userRepository.save(user);
        userRepository.save(userAdmin);
    }
}
