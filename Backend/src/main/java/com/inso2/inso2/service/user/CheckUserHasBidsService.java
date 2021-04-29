package com.inso2.inso2.service.user;

import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckUserHasBidsService {
    private final BidRepository bidRepository;

    public CheckUserHasBidsService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public boolean check(User user){
        List<Bid> bids = bidRepository.findByUser(user);
        return !bids.isEmpty();
    }
}
