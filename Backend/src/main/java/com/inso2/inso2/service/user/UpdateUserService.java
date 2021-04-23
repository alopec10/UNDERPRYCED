package com.inso2.inso2.service.user;

import com.inso2.inso2.dto.user.update.UserUpdateRequest;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UpdateUserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UpdateUserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void update(UserUpdateRequest req, User user) throws Exception {
        if(!user.getName().equals(req.getName())){
            user.setName(req.getName());
        }
        if(!user.getSurname().equals(req.getSurname())){
            user.setSurname(req.getSurname());
        }
        if(!user.getEmail().equals(req.getEmail())){
            user.setEmail(req.getEmail());
        }
        String new_pass = passwordEncoder.encode(req.getPassword());
        if(req.getPassword() != null && !req.getPassword().isBlank() && !user.getPassword().equals(new_pass)){
            if(isPasswordValid(req.getPassword())){
                user.setPassword(new_pass);
            }
            else{
                throw new Exception("Password format is not correct");
            }
        }
        if(user.getAddress() == null || !user.getAddress().equals(req.getAddress())){
            if (req.getAddress() == null || req.getAddress().isBlank()){
                user.setAddress(null);
            }
            else{
                user.setAddress(req.getAddress());
            }
        }
        if(user.getCountry() == null || !user.getCountry().equals(req.getCountry())){
            if (req.getCountry() == null || req.getCountry().isBlank()){
                user.setCountry(null);
            }
            else{
                user.setCountry(req.getCountry());
            }
        }
        if(user.getZipCode() == null || !user.getZipCode().equals(req.getZipCode())){
            if (req.getZipCode() == null || req.getZipCode().isBlank()){
                user.setZipCode(null);
            }
            else{
                user.setZipCode(req.getZipCode());
            }
        }
        if(user.getPhoneNumber() == null || !user.getPhoneNumber().equals(req.getPhoneNumber())){
            if (req.getPhoneNumber() == null || req.getPhoneNumber().isBlank()){
                user.setPhoneNumber(null);
            }
            else if(isPhoneNumberValid(req.getPhoneNumber())){
                user.setPhoneNumber(req.getPhoneNumber());
            }
            else{
                throw new Exception("Phone number format is not correct");
            }
        }
        userRepository.save(user);
    }

    private boolean isPasswordValid(String pass){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$");
        Matcher matcher = pattern.matcher(pass);
        System.out.println(matcher.matches());
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
