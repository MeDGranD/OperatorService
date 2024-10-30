INSERT INTO roles (role) VALUES
('Admin'),
('Editor'),
('Viewer'),
('Moderator'),
('Contributor');

INSERT INTO users (role_id, username) VALUES
(1, 'user1'),
(2, 'user2'),
(3, 'user3'),
(1, 'user4'),
(2, 'user5'),
(3, 'user6'),
(1, 'user7'),
(2, 'user8'),
(3, 'user9'),
(1, 'user10'),
(2, 'user11'),
(3, 'user12'),
(1, 'user13'),
(2, 'user14'),
(3, 'user15'),
(1, 'user16'),
(2, 'user17'),
(3, 'user18'),
(1, 'user19'),
(2, 'user20');

INSERT INTO tarifs (cost, minutes, "mobile internet", sms, "wi-fi") VALUES
(9.99, 100, 5, 100, 0),
(14.99, 200, 10, 200, 0),
(19.99, 300, 15, 300, 0),
(24.99, 400, 20, 400, 0),
(29.99, 500, 30, 500, 0),
(34.99, 600, 50, 600, 0),
(39.99, 700, 65, 700, 0),
(44.99, 800, 10, 1000, 0),
(49.99, 900, 15, 1200, 0),
(54.99, 1000, 65, 1500, 0);

INSERT INTO reviews (stars, user_id, review) VALUES
(5, 1, 'Excellent service! Highly recommend.'),
(4, 2, 'Very good, but could improve on response time.'),
(3, 3, 'Average experience, nothing special.'),
(2, 4, 'Not satisfied with the product quality.'),
(1, 5, 'Terrible service, would not recommend.'),
(5, 6, 'Absolutely wonderful! Will come back again.'),
(4, 7, 'Good value for money.'),
(3, 8, 'It was okay, I expected more.'),
(2, 9, 'Not what I was hoping for, but it was fine.'),
(1, 10, 'Disappointed. I had higher expectations.'),
(5, 11, 'Fantastic experience! Loved it!'),
(4, 12, 'Pretty solid overall.'),
(3, 13, 'Mediocre at best.'),
(2, 14, 'Could use some improvements.'),
(1, 15, 'Very poor quality.'),
(5, 16, 'Exceeded my expectations!'),
(4, 17, 'Really liked it, just a few issues.'),
(3, 18, 'It was decent, nothing more.'),
(2, 19, 'Not great, but not terrible either.'),
(1, 20, 'Awful experience. I won’t be back.');