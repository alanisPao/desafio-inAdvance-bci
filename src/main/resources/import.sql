
INSERT INTO users (id, name, password, email, created, is_active) values(uuid(), 'Admin Api', '$2a$12$8LC3SWeoZm7LKjuZ.aE5TurhyP89S3ECdkvLlKPV9561CyUDqEiLe', 'admin@gmail.com', NOW(),true);

INSERT INTO phones (id, number, city_code, country_code, user_id) values (uuid(),'+56956784334', '1', '1', select id from users where email='palacios@gmail.com');
INSERT INTO phones (id, number, city_code, country_code, user_id) values (uuid(), '+56988902234', '5', '1', select id from users where email='palacios@gmail.com');