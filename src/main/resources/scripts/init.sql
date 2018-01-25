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