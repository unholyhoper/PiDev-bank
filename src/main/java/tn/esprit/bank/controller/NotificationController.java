package tn.esprit.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.Notification;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.service.NotificationService;
import tn.esprit.bank.util.Constants;

import java.util.List;


@RestController
@RequestMapping(Constants.APP_ROOT + "/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;


    @GetMapping("/getAll")
    public List<Notification> getAllNotifications() {
        return notificationService.findAllNotifications();
    }


    @GetMapping("/getById/{Id}")
    public ResponseEntity getNotificationById(@PathVariable("Id") Long notificationId) {
        try {
            return ResponseEntity.ok(notificationService.findNotificationById(notificationId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity createNotification(@RequestBody Notification notification, Transaction transaction) {
        try {
            return ResponseEntity.ok(notificationService.createNotification(notification, transaction));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity updateNotification(@PathVariable("Id") Long notificationId, @RequestBody Notification notification) {
        try {
            return ResponseEntity.ok(notificationService.updateNotification(notificationId, notification));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{Id}")
    public void deleteNotification(@PathVariable("Id") Long notificationId) {
        notificationService.deleteNotification(notificationId);
    }
}








