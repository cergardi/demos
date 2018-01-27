CREATE DATABASE demousuarioext;

/* Crear tabla de roles de la aplicación */
CREATE TABLE app_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

/* Crear tabla de usuarios de la aplicación */
CREATE TABLE app_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

/* Crear tabla de roles de usuarios */
CREATE TABLE user_role (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES app_user (id),
  CONSTRAINT FK_APP_ROLE FOREIGN KEY (role_id) REFERENCES app_role (id)
);

INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
-- Clave no encriptada: jwtpass
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (1, 'Usuario', 'Ricoh', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'usuario.ricoh');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (2, 'Admin', 'Admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'admin.admin');


INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);


/* Crear Tabla USUARIOS */
create table USUARIOS (
   id INTEGER NOT NULL AUTO_INCREMENT,
   nombre VARCHAR(30) NOT NULL,
   apellidos VARCHAR(60) NOT NULL,
   edad  INTEGER NOT NULL,
   PRIMARY KEY (id)
);
   
/* REGISTROS BASE TABLA USUARIOS */
INSERT INTO USUARIOS (nombre ,apellidos , edad)
VALUES ('Diego', 'Cernuda',30);
   
INSERT INTO USUARIOS (nombre ,apellidos , edad)
VALUES ('Beatriz', 'Blanco',30);
 
commit;