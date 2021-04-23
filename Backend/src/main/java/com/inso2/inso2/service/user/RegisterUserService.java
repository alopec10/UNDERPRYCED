package com.inso2.inso2.service.user;

import com.inso2.inso2.dto.register.RegisterRequest;
import com.inso2.inso2.model.Role;
import com.inso2.inso2.model.RoleName;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.RoleRepository;
import com.inso2.inso2.repository.UserRepository;
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

    public RegisterUserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void register(RegisterRequest req) throws Exception {
        User u = new User();
        u.setName(req.getName());
        u.setSurname(req.getSurname());
        u.setEmail(req.getEmail());
        if(isPasswordValid(req.getPassword())){
            u.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        else{
            throw new Exception("Password format is not correct");
        }
        u.setAddress(req.getAddress());
        u.setCountry(req.getCountry());
        u.setZipCode(req.getZipCode());
        if(isPhoneNumberValid(req.getPhoneNumber())){
            u.setPhoneNumber(req.getPhoneNumber());
        }
        else if(req.getPhoneNumber()==null || req.getPhoneNumber().isBlank()){
            u.setPhoneNumber(null);
        }
        else{
            throw new Exception("Phone number format is not correct");
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
    }

    private boolean isPasswordValid(String pass){
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}");
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
