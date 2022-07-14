INSERT INTO `roles` (`name`) VALUES ('GUEST');
INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('BANKER');

INSERT INTO `pidev_bank`.`moral_user` VALUES (1,'GUEST_ADDRESS',1,'GUEST_COUNTRY','GUEST_FIRSTNAME','GUEST_LASTNAME','$2a$10$ywsr6q53RATdoWgA9qFSUeKWNVWsORqabEQb3zdpXmiu2XKDI2lbi','GUEST');
INSERT INTO `pidev_bank`.`moral_user` VALUES (2,'USER_ADDRESS',2,'USER_COUNTRY','USER_FIRSTNAME','USER_LASTNAME','$2a$10$9UzU3qtSNJHEVTRLUdom/.Yb.FVl2YIJh0PBp9rH6uu5OXeAq.Qdi','USER');
INSERT INTO `pidev_bank`.`moral_user` VALUES (3,'BANKER_ADDRESS',3,'BANKER_COUNTRY','BANKER_FIRSTNAME','BANKER_LASTNAME','$2a$10$ukMKlDTjrPhHHn.pl3kUC.bKQAU7KxK.sYe3KDFja66f4ineIGL4K','BANKER');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES ('3', '3');
