package com.inso2.inso2.service.ask;

import com.inso2.inso2.dto.ask.AskRequest;
import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AskRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateOrModifyAskService {

    private final ProductDetailsRepository productDetailsRepository;

    private final AskRepository askRepository;

    private final ProductRepository productRepository;

    private final CreateAskService createAskService;

    private final ModifyAskService modifyAskService;

    public CreateOrModifyAskService(ProductDetailsRepository productDetailsRepository, AskRepository askRepository, ProductRepository productRepository, CreateAskService createAskService, ModifyAskService modifyAskService) {
        this.productDetailsRepository = productDetailsRepository;
        this.askRepository = askRepository;
        this.productRepository = productRepository;
        this.createAskService = createAskService;
        this.modifyAskService = modifyAskService;
    }

    public String createOrModify(AskRequest req, User user) throws Exception {
            Product product = productRepository.findByRef(req.getRef());
            ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
            Ask ask = askRepository.findByUserAndProductDetails(user, productDetails);
            if (ask == null) {
                createAskService.create(req.getPrice(), user, productDetails);
                return "Ask created";
            } else {
                modifyAskService.modify(req.getPrice(), productDetails, ask);
                return "Ask modified";
            }
    }
}
