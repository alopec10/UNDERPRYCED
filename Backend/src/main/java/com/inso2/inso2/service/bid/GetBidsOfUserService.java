package com.inso2.inso2.service.bid;

import com.inso2.inso2.dto.bid.GetAllBidsByUserResponse;
import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetBidsOfUserService {
    private final BidRepository bidRepository;

    public GetBidsOfUserService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public List<GetAllBidsByUserResponse> get(User user){
        List<Bid> bids = bidRepository.findByUser(user);
        List<GetAllBidsByUserResponse> bresp = new ArrayList<>();
        for(Bid b: bids){
            bresp.add(new GetAllBidsByUserResponse().build(b));
        }
        return bresp;
    }
}
