INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`) VALUES
(1, 'mike', '$2a$10$Qnuflf4enHTkKMUZ3E3hXeOdDISPp4lVEckmW5n6F0BvFl55fp45K', 1),
(2, 'sarah', '$2a$10$Qnuflf4enHTkKMUZ3E3hXeOdDISPp4lVEckmW5n6F0BvFl55fp45K', 1);

INSERT INTO `authorities` (`username`, `authority`) VALUES
('mike', 'ROLE_ADMIN'),
('mike', 'ROLE_USER'),
('sarah', 'ROLE_USER');

ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
