package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.Notification;
import tn.esprit.bank.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<Notification> findAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification findNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId).get();
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long notificationId, Notification requestBody) {
        Notification notification = notificationRepository.findById(notificationId).get();
        notification.setStatus(requestBody.getStatus());
        notification.setContent(requestBody.getContent());
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).get();
        notificationRepository.delete(notification);
    }
}
