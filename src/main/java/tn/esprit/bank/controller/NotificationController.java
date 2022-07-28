package tn.esprit.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.Notification;
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
    public Notification getNotificationById(@PathVariable("Id") Long notificationId) {
        return notificationService.findNotificationById(notificationId);
    }

    @PostMapping("/create")
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @PutMapping("/update/{Id}")
    public Notification updateNotification(@PathVariable("Id") Long notificationId, @RequestBody Notification notification) {
        return notificationService.updateNotification(notificationId, notification);
    }

    @DeleteMapping("/delete/{Id}")
    public void deleteNotification(@PathVariable("Id") Long notificationId) {
        notificationService.deleteNotification(notificationId);
    }
}








