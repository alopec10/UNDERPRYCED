package com.inso2.inso2.service.user;

import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.UserRepository;
import com.inso2.inso2.service.bid.DeleteAllBidsOfUserService;
import org.springframework.stereotype.Service;

@Service
public class DeleteFullAddressService {
    private final UserRepository userRepository;
    private final DeleteAllBidsOfUserService deleteAllBidsOfUserService;

    public DeleteFullAddressService(UserRepository userRepository, DeleteAllBidsOfUserService deleteAllBidsOfUserService) {
        this.userRepository = userRepository;
        this.deleteAllBidsOfUserService = deleteAllBidsOfUserService;
    }

    public void delete(User user){
        user.setAddress(null);
        user.setCountry(null);
        user.setZipCode(null);
        userRepository.saveAndFlush(user);
        deleteAllBidsOfUserService.delete(user);
    }
}
