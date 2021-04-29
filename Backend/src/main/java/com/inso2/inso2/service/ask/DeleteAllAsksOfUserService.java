package com.inso2.inso2.service.ask;

import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteAllAsksOfUserService {
    private final AskRepository askRepository;

    public DeleteAllAsksOfUserService(AskRepository askRepository) {
        this.askRepository = askRepository;
    }

    public void delete(User user){
        List<Ask> asks = askRepository.findByUser(user);
        for(Ask a: asks){
            askRepository.deleteById(a.getIdAsk());
        }
    }
}
