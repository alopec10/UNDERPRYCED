package com.inso2.inso2.service.alert;

import com.inso2.inso2.model.Alert;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AlertRepository;
import org.springframework.stereotype.Service;

@Service
public class MarkAlertAsReadService {
    private final AlertRepository alertRepository;

    public MarkAlertAsReadService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void mark(User user, long idAlert){
        Alert alert = alertRepository.findByUserAndIdAlert(user, idAlert);
        alert.setRead(true);
        alertRepository.saveAndFlush(alert);
    }
}
