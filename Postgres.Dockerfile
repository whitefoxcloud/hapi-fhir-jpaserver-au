FROM postgres:16-alpine

WORKDIR /var/lib/postgresql

COPY dbdata/part_* .
RUN cat part_* > db-data.tar.gz
RUN tar -xzvf db-data.tar.gz -C .
RUN rm part_*
RUN rm db-data.tar.gz

RUN chown -R postgres:postgres data

CMD ["postgres", "-c", "listen_addresses=*"]
