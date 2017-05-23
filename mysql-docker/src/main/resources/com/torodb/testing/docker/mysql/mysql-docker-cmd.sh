(
    while ! mysql -h localhost -u test -ptest -D test -e 'SELECT 1';
    do
        sleep 1;
    done;
    set -e;
    mysql -h localhost -u root -ptest -D test -e "GRANT ALL PRIVILEGES ON *.* TO 'test'@'%'";
    mysql -h localhost -u root -ptest -D test -e "FLUSH PRIVILEGES";
    echo "Database started";
) &
./entrypoint.sh mysqld