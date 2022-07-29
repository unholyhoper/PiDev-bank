package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.Notification;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.NotificationStatus;
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

    @KafkaListener(topics = "myNotification", groupId = "notificationGroup")
    public void consumedNotification(String message) {
        System.out.println("Consumed Notification : " + message);
    }

    @Override
    public Notification createNotification(Notification requestBody, Transaction transaction) {
        Notification notification = new Notification();
        if (transaction.getType().isRetrait()) {
            notification.setContent("Vous avez faire un retrait de " + transaction.getAmount() + " TND sous la référence"
                    + transaction.getId());
            notification.setAccount(transaction.getBankAccountFrom());
        }
        if (transaction.getType().isVersement()) {
            notification.setContent("Vous avez reçu un versement de " + transaction.getAmount() + " TND sous la référence"
                    + transaction.getId());
            notification.setAccount(transaction.getBankAccountTo());
        }
        if (transaction.getType().isVirement()) {
            notification.setContent("Le virement de " + transaction.getAmount() + " TND au bénéficiaire "
                    + transaction.getBankAccountTo().getUser().getFirstName()
                    + " " + transaction.getBankAccountTo().getUser().getLastName()
                    + " que vous venez d'émettre a été pris en charge avec succès sous la référence "
                    + transaction.getId());
            notification.setAccount(transaction.getBankAccountTo());
        }
        notification.setStatus(NotificationStatus.UNREAD);
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
