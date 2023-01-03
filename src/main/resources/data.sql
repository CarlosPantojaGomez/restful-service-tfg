INSERT INTO USER (id, flag_active, name, nickname, password, user_type, email, first_last_name, second_last_name) VALUES (1, 1, 'Carlos', 'admin', 'admin', 4, 'admin@gmail.com', 'Pantoja', 'Gomez');
INSERT INTO USER (id, flag_active, name, nickname, password, user_type, email, first_last_name, second_last_name) VALUES (2, 1, 'Antonio', 'altoCargo1', 'altoCargo1', 3, 'admin@gmail.com', 'Navarro', 'Gallardo');
INSERT INTO USER (id, flag_active, name, nickname, password, user_type, email, first_last_name, second_last_name) VALUES (3, 1, 'Juan', 'altoCargo2', 'altoCargo2', 3, 'admin@gmail.com', 'Del Pozo', 'Gonzalez');
INSERT INTO USER (id, flag_active, name, nickname, password, user_type, email, first_last_name, second_last_name) VALUES (4, 1, 'Fernando', 'bajoCargo1', 'bajoCargo1', 2, 'admin@gmail.com', 'Rodriguez', 'Velazquez');
INSERT INTO USER (id, flag_active, name, nickname, password, user_type, email, first_last_name, second_last_name) VALUES (5, 1, 'Ale', 'bajoCargo2', 'bajoCargo2', 2, 'admin@gmail.com', 'Vicaria', 'Ramirez');
INSERT INTO USER (id, flag_active, name, nickname, password, user_type, email, first_last_name, second_last_name) VALUES (6, 1, 'Miguel', 'usuario1', 'usuario1', 1, 'admin@gmail.com', 'Gomez', 'Castillo');
INSERT INTO USER (id, flag_active, name, nickname, password, user_type, email, first_last_name, second_last_name) VALUES (7, 1, 'Jose', 'usuario2', 'usuario2', 1, 'admin@gmail.com', 'Ferreras', 'Muñoz');

INSERT INTO PRODUCT (id, name, price, flag_active) VALUES (8, 'Producto de prueba 1', 0.01, 1);
INSERT INTO PROJECT (id, name, priority, flag_active, creator_id, product_id) VALUES (1, 'Proyecto de prueba 1', 1, 1, 3, 8);
INSERT INTO TASK (id, name, priority, flag_active, project_id, creator_id) VALUES (1, 'Tarea de prueba 1', 1, 1, 1, 3);
INSERT INTO TASK (id, name, priority, flag_active, project_id, creator_id) VALUES (2, 'Tarea de prueba 2', 1, 1, 1, 3);


INSERT INTO PROJECT (id, name, priority, flag_active, creator_id, product_id) VALUES (2, 'Proyecto de prueba 2', 1, 1, 3, 8);

INSERT INTO PRODUCT (id, name, price, flag_active) VALUES (31, 'Producto de prueba 2', 0.01, 1);

INSERT INTO COUNTRY (id, name) VALUES (1 , 'España');
INSERT INTO COUNTRY (id, name) VALUES (2 , 'Estados Unidos');
INSERT INTO COUNTRY (id, name) VALUES (3 , 'Inglaterra');
INSERT INTO COUNTRY (id, name) VALUES (4 , 'China');
INSERT INTO COUNTRY (id, name) VALUES (5 , 'Francia');
INSERT INTO COUNTRY (id, name) VALUES (6 , 'India');
INSERT INTO COUNTRY (id, name) VALUES (7 , 'Canada');
INSERT INTO COUNTRY (id, name) VALUES (8 , 'Alemania');
INSERT INTO COUNTRY (id, name) VALUES (9 , 'Italia');
//