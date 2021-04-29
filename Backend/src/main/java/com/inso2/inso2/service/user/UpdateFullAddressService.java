package com.inso2.inso2.service.user;

import com.inso2.inso2.dto.user.update.UpdateAddressRequest;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UpdateFullAddressService {
    private final UserRepository userRepository;

    public UpdateFullAddressService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update(UpdateAddressRequest req, User user) throws Exception{
        String address = req.getAddress();
        String zipCode = req.getZipCode();
        String country = req.getCountry();
        if(!this.isAddressValid(address)){
            throw new Exception("Invalid address format");
        }
        if(!this.isCountryValid(country)){
            throw new Exception("Invalid country format");
        }
        if(!this.isZipCodeValid(zipCode)){
            throw new Exception("Invalid zipCode format");
        }
        user.setAddress(address);
        user.setZipCode(zipCode);
        user.setCountry(country);
        userRepository.saveAndFlush(user);
    }

    private boolean isAddressValid(String address){
        Pattern pattern = Pattern.compile(".{10,150}");
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    private boolean isCountryValid(String country){
        Pattern pattern = Pattern.compile("^[A-ZÀ-Ö][A-Za-zÀ-ÖØ-öø-ÿ\\s\\-]+$");
        Matcher matcher = pattern.matcher(country);
        return matcher.matches();
    }

    private boolean isZipCodeValid(String zipCode){
        Pattern pattern = Pattern.compile("^\\d{5}(?:[-\\s]\\d{4})?$");
        Matcher matcher = pattern.matcher(zipCode);
        return matcher.matches();
    }
}
