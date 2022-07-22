INSERT INTO `roles` (`name`) VALUES ('GUEST');
INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('BANKER');

-- INSERT INTO `moral_user` VALUES (1,'GUEST_ADDRESS',1,'GUEST_COUNTRY','GUEST_FIRSTNAME',_binary '\1','GUEST_LASTNAME','$2a$10$9CS6NwcybuMSyhIAD/YBTuPI8dDQmV1hSHhNaq9hX.UGs2MqJF7Gm','GUEST','guest@esprit.tn'),(2,'USER_ADDRESS',2,'USER_COUNTRY','USER_FIRSTNAME',_binary '\1','USER_LASTNAME','$2a$10$ELkrBjA/RYljh232y3W3Q.qoBR59EMr3Q6T.75hhdBh3znuI6B8kW','USER'),(3,'BANKER_ADDRESS',3,'BANKER_COUNTRY','BANKER_FIRSTNAME',_binary '\1','BANKER_LASTNAME','$2a$10$.AD8lSRw6OZvMGSnNarNtenK65o9jwQguqwrWjuzKgl8uteipmwae','BANKER');

INSERT INTO `moral_user` VALUES (1,'GUEST_ADDRESS',1,'GUEST_COUNTRY','guest@esprit.tn','GUEST_FIRSTNAME',_binary '','GUEST_LASTNAME','$2a$10$jcDleFBz8II4TNP2qIx7PuLTRHyc6dG4qsfD3hb75yrzx4WZqdD36','GUEST_'),(2,'USER_ADDRESS',2,'USER_COUNTRY','user@esprit.tn','USER_FIRSTNAME',_binary '','USER_LASTNAME','$2a$10$a.BQ3yE1OpYDfBmTGFx7.uTXSXqQdDhRrgE.rD08krAxdH/b2jp5C','USER'),(3,'BANKER_ADDRESS',3,'BANKER_COUNTRY','banker@esprit.tn','BANKER_FIRSTNAME',_binary '','BANKER_LASTNAME','$2a$10$LOLwH.RBMcjg0CkKhEBfmO9Mt8BhInpQvPsNouezJPWnHCYxRv8Me','BANKER');


INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('3', '3');

UPDATE `HIBERNATE_SEQUENCES` SET next_val = 3  WHERE sequence_name like "default";