package com.inso2.inso2.service.user;

import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AskRepository;
import com.inso2.inso2.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckUserHasAsksOrBidsService {
    private final AskRepository askRepository;
    private final BidRepository bidRepository;

    public CheckUserHasAsksOrBidsService(AskRepository askRepository, BidRepository bidRepository) {
        this.askRepository = askRepository;
        this.bidRepository = bidRepository;
    }

    public boolean check(User user){
        List<Ask> asks = askRepository.findByUser(user);
        List<Bid> bids = bidRepository.findByUser(user);
        return !asks.isEmpty() || !bids.isEmpty();
    }
}
