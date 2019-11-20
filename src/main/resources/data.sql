INSERT INTO users (id, email, password, full_name, username, role_id)  VALUES(1, 'example1@example.com', '$2a$04$AqM0bgu8.YGSY2jRbcIckOI2FMwSavsiVd.jHgrx3jIF5lcpsG1xK', 'Oláh Lajos', 'Abdys', 1);
INSERT INTO users (id, email, password, full_name, username, role_id)  VALUES(2, 'example2@example.com', '$2a$04$AqM0bgu8.YGSY2jRbcIckOI2FMwSavsiVd.jHgrx3jIF5lcpsG1xK', 'Hajdu Benedek', 'beni', 2);
INSERT INTO users (id, email, password, full_name, username, role_id)  VALUES(3, 'example3@example.com', '$2a$04$AqM0bgu8.YGSY2jRbcIckOI2FMwSavsiVd.jHgrx3jIF5lcpsG1xK', 'Csoltkó András', 'bandi', 3);
INSERT INTO users (id, email, password, full_name, username, role_id)  VALUES(4, 'example4@example.com', '$2a$04$AqM0bgu8.YGSY2jRbcIckOI2FMwSavsiVd.jHgrx3jIF5lcpsG1xK', 'Tejes László', 'laci', 4);
INSERT INTO users (id, email, password, full_name, username, role_id)  VALUES(5, 'example5@example.com', '$2a$04$AqM0bgu8.YGSY2jRbcIckOI2FMwSavsiVd.jHgrx3jIF5lcpsG1xK', 'Admin Admin', 'admin', 5);

INSERT INTO roles (id, role) VALUES(1, 'ROLE_HRVEZETO');
INSERT INTO roles (id, role) VALUES(2, 'ROLE_HRMUNKATARS');
INSERT INTO roles (id, role) VALUES(3, 'ROLE_SZAKMAIVEZETO');
INSERT INTO roles (id, role) VALUES(4, 'ROLE_SZAKMAIMUNKATARS');
INSERT INTO roles (id, role) values(5,'ROLE_ADMIN');

INSERT INTO job_type (id, name) VALUES(1, 'Szoftverfejlesztő');
INSERT INTO job_level (id, level) VALUES(1, 'Junior');
INSERT INTO location (id, city) VALUES(1, 'Debrecen');

ALTER TABLE `users` CHANGE `role_id` `role_id` BIGINT(20) NULL DEFAULT NULL;

INSERT INTO employee (id, first_name, last_name, birth_name, mail, birth_day, birth_place, mother, phone_number, status, cv_id, level_id, location_id, type_id)
VALUES(1, 'Példa', 'Péter', 'Példa Péter', 'example@example.com', null, 'Debrecen', 'Példa Janka', '06301234567', 'Aktív', null, 1, 1, 1);

INSERT INTO employee (id, first_name, last_name, birth_name, mail, birth_day, birth_place, mother, phone_number, status, cv_id, level_id, location_id, type_id)
VALUES(2, 'Példa', 'Géza', 'Példa Géza', 'example2@example.com', null, 'Debrecen', 'Példa Janka', '06301234567', 'Aktív', null, 1, 1, 1);