FROM postgres:16-alpine

WORKDIR /var/lib/postgresql

COPY dbdata/db-data.tar.gz .
RUN tar -xzvf db-data.tar.gz -C .
RUN rm db-data.tar.gz

RUN chown -R postgres:postgres data

CMD ["postgres", "-c", "listen_addresses=*"]
