package com.inso2.inso2.controller;

import com.inso2.inso2.model.User;
import com.inso2.inso2.service.alert.GetAllAlertsService;
import com.inso2.inso2.service.alert.MarkAlertAsReadService;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alert")
public class AlertController {
    private final LoadUserService loadUserService;
    private final GetAllAlertsService getAllAlertsService;
    private final MarkAlertAsReadService markAlertAsReadService;

    public AlertController(LoadUserService loadUserService, GetAllAlertsService getAllAlertsService, MarkAlertAsReadService markAlertAsReadService) {
        this.loadUserService = loadUserService;
        this.getAllAlertsService = getAllAlertsService;
        this.markAlertAsReadService = markAlertAsReadService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            return ResponseEntity.ok(getAllAlertsService.get(user));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/markAsRead", method = RequestMethod.GET)
    public ResponseEntity<?> markAsRead(@RequestParam long id) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            markAlertAsReadService.mark(user, id);
            return ResponseEntity.ok("Alert read");
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
