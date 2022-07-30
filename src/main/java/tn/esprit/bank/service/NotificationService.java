package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Notification createNotification(Notification requestBody) {
        Notification notification = new Notification();
        notification.setContent(requestBody.getContent());
        notification.setStatus(NotificationStatus.UNREAD);
        notification.setAccount(requestBody.getAccount());
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

    public void sendNotification(Transaction transaction) {
        Notification notification = new Notification();
        if (transaction.getType().isRetrait()) {
            notification.setContent("Vous avez faire un retrait de " + transaction.getAmount() + " TND de votre compte "
                    + transaction.getBankAccountFrom().getUser().getFirstName() + " "
                    + transaction.getBankAccountFrom().getUser().getLastName()
                    + " sous la référence "
                    + transaction.getId());
            notification.setAccount(transaction.getBankAccountFrom());
        }
        if (transaction.getType().isVersement()) {
            notification.setContent("Le versement de " + transaction.getAmount() + " TND au bénéficiaire "
                    + transaction.getBankAccountTo().getUser().getFirstName()
                    + " " + transaction.getBankAccountTo().getUser().getLastName()
                    + " sous la référence "
                    + transaction.getId() + " est effectué avec succès");
            notification.setAccount(transaction.getBankAccountTo());
        }
        if (transaction.getType().isVirement()) {
            notification.setContent("Le virement de " + transaction.getAmount() + " TND du compte "
                    + transaction.getBankAccountFrom().getUser().getFirstName()
                    + " " + transaction.getBankAccountFrom().getUser().getLastName()
                    + " au bénéficiaire "
                    + transaction.getBankAccountTo().getUser().getFirstName()
                    + " " + transaction.getBankAccountTo().getUser().getLastName()
                    + " que vous venez d'émettre a été pris en charge avec succès sous la référence "
                    + transaction.getId());
            notification.setAccount(transaction.getBankAccountTo());
        }
        this.createNotification(notification);
    }

    @Override
    public void markAsSeen(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).get();
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }
}
