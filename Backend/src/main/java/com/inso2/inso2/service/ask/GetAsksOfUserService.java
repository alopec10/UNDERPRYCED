package com.inso2.inso2.service.ask;

import com.inso2.inso2.dto.ask.GetAllAsksByUserResponse;
import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAsksOfUserService {

    private final AskRepository askRepository;

    public GetAsksOfUserService(AskRepository askRepository) {
        this.askRepository = askRepository;
    }

    public List<GetAllAsksByUserResponse> get(User user){
        List<Ask> asks = askRepository.findByUser(user);
        List<GetAllAsksByUserResponse> aresp = new ArrayList<>();
        for(Ask a: asks){
            aresp.add(new GetAllAsksByUserResponse().build(a));
        }
        return aresp;
    }
}
