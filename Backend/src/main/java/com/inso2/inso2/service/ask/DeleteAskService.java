package com.inso2.inso2.service.ask;

import com.inso2.inso2.dto.ask.delete.DeleteAskRequest;
import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AskRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteAskService {
    private final ProductDetailsRepository productDetailsRepository;

    private final AskRepository askRepository;

    public DeleteAskService(ProductDetailsRepository productDetailsRepository, AskRepository askRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.askRepository = askRepository;
    }

    public void delete(DeleteAskRequest req, User user) throws Exception {
        ProductDetails productDetails = productDetailsRepository.findByIdProductDetails(req.getIdProductDetails());
        Ask ask = askRepository.findByUserAndProductDetails(user, productDetails);
        if(ask == null){
            throw new Exception("The ask doesn't exist");
        }
        askRepository.deleteById(ask.getIdAsk());
    }
}
