package tn.esprit.bank.service;

import tn.esprit.bank.entity.Notification;
import tn.esprit.bank.entity.Transaction;

import java.util.List;

public interface INotificationService {

    List<Notification> findAllNotifications();

    Notification findNotificationById(Long notificationId);

    Notification createNotification(Notification notification, Transaction transaction);

    Notification updateNotification(Long notificationId, Notification notification);

    void deleteNotification(Long notificationId);

}
