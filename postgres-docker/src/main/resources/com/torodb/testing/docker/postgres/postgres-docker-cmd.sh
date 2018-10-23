echo "localhost:*:test:test:test" > ~/.pgpass;
chmod 400 ~/.pgpass;
(
    while ! psql -h localhost -U test -d test;
    do
        sleep 1;
    done;
    echo "Database started";
) &
./docker-entrypoint.sh postgres