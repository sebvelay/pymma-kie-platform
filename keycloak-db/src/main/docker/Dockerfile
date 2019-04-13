FROM postgres:9.4
RUN mkdir /home/pgdata
RUN mkdir /home/pgdata/keycloak

RUN chown -R postgres:  /home/pgdata


VOLUME /home/pgdata
RUN chown postgres: /home/pgdata
ADD 01_init.sql /docker-entrypoint-initdb.d/
ADD 02_Create_table.sql /docker-entrypoint-initdb.d/
ADD 03_Create_table_content.sql /docker-entrypoint-initdb.d/

CMD ["postgres"]

