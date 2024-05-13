
INSERT INTO users (id, name, password, email) values(uuid(), 'Paola Palacios', '$2a$12$2/QfY9PDl.3zetHvk5Vxc.DV2HKaieooPRmLuSmLDIsowE.Vqrv0C', 'palacios@gmail.com');

INSERT INTO phones (id, number, city_code, country_code, user_id) values (uuid(),'+56956784334', '1', '1', select id from users where email='palacios@gmail.com');
INSERT INTO phones (id, number, city_code, country_code, user_id) values (uuid(), '+56988902234', '5', '1', select id from users where email='palacios@gmail.com');