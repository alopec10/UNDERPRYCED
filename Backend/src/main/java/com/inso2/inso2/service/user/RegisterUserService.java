package com.inso2.inso2.service.user;

import com.inso2.inso2.dto.register.RegisterRequest;
import com.inso2.inso2.model.Role;
import com.inso2.inso2.model.RoleName;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.RoleRepository;
import com.inso2.inso2.repository.UserRepository;
import com.inso2.inso2.service.alert.CreateAlertService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterUserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CreateAlertService createAlertService;

    public RegisterUserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, CreateAlertService createAlertService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.createAlertService = createAlertService;
    }

    public void register(RegisterRequest req) throws Exception {
        String name = req.getName();
        String surname = req.getSurname();
        String email = req.getEmail();
        if(!this.isNameValid(name)){
            throw new Exception("Invalid name format");
        }
        if(!this.isSurnameValid(surname)){
            throw new Exception("Invalid surname format");
        }
        if(!this.isEmailValid(email)){
            throw new Exception("Invalid email format");
        }
        User u = new User();
        u.setName(name);
        u.setSurname(surname);
        u.setEmail(email);
        if(isPasswordValid(req.getPassword())){
            u.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        else{
            throw new Exception("Password format is not correct");
        }
        Set<String> strRoles = req.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
            roles.add(userRole);
        }
        else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
                    roles.add(adminRole);

                }
                else {
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
                    roles.add(userRole);
                }
            });
        }
        u.setRoles(roles);
        userRepository.saveAndFlush(u);
        createAlertService.createWelcomeAlert(u);
    }

    private boolean isNameValid(String name){
        Pattern pattern = Pattern.compile(".{1,20}");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isSurnameValid(String surname){
        Pattern pattern = Pattern.compile(".{1,30}");
        Matcher matcher = pattern.matcher(surname);
        return matcher.matches();
    }

    private boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String pass){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$");
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

}
