package com.inso2.inso2.service.bid;

import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteAllBidsOfUserService {
    private final BidRepository bidRepository;

    public DeleteAllBidsOfUserService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public void delete(User user){
        bidRepository.deleteByUser(user);
    }
}
