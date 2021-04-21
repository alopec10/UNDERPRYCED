package com.inso2.inso2.service.user;

import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoadUserService {
    private final UserRepository userRepository;

    public LoadUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User load(Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        return userRepository.findByEmail(email);
    }
}
