package tn.esprit.bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


}
