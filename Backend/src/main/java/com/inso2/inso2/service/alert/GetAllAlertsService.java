package com.inso2.inso2.service.alert;

import com.inso2.inso2.dto.alert.GetAllAlertsResponse;
import com.inso2.inso2.model.Alert;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllAlertsService {
    private final AlertRepository alertRepository;

    public GetAllAlertsService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public List<GetAllAlertsResponse> get(User user){
        List<Alert> alerts = alertRepository.findByUserOrderByIdAlert(user);
        List<GetAllAlertsResponse> response = new ArrayList<>();
        for(Alert a: alerts){
            response.add(new GetAllAlertsResponse(a.getTitle(), a.getMessage(), a.getDate().toString().substring(0, 10), a.isRead()));
        }
        return response;
    }
}
