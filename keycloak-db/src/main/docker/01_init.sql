CREATE USER keycloak_user WITH PASSWORD 'keycloak_user';
CREATE TABLESPACE keycloak_tablesplace owner keycloak_user location '/home/pgdata/keycloak';
create database keycloakdb ENCODING = 'UTF8' TABLESPACE keycloak_tablesplace;
GRANT ALL PRIVILEGES ON database keycloakdb to keycloak_user;