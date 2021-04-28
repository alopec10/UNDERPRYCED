package com.inso2.inso2.controller;

import com.inso2.inso2.model.User;
import com.inso2.inso2.service.alert.GetAllAlertsService;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alert")
public class AlertController {
    private final LoadUserService loadUserService;
    private final GetAllAlertsService getAllAlertsService;

    public AlertController(LoadUserService loadUserService, GetAllAlertsService getAllAlertsService) {
        this.loadUserService = loadUserService;
        this.getAllAlertsService = getAllAlertsService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByUser(){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            return ResponseEntity.ok(getAllAlertsService.get(user));
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
