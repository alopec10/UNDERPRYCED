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
        String name = req.getName();
        String surname = req.getSurname();
        String email = req.getEmail();
        String phoneNumber = req.getPhoneNumber();
        if(!this.isNameValid(name)){
            throw new Exception("Invalid name format");
        }
        if(!this.isSurnameValid(surname)){
            throw new Exception("Invalid surname format");
        }
        if(!this.isEmailValid(email)){
            throw new Exception("Invalid email format");
        }
        if(!this.isPhoneNumberValid(phoneNumber)){
            if(phoneNumber != null && !phoneNumber.isBlank()){
                throw new Exception("Invalid phone number format");
            }
            phoneNumber = null;
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
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
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
