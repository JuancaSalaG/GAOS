#!/bin/bash
echo "Running Migrations"
for sql_file in /home/database/sql/*.sql; do
    if [ -f "$sql_file" ]; then
        echo "Running Script: $sql_file"
        mysql -u "root" -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < "$sql_file"
        if [ $? -ne 0 ]; then
            echo "Error executing $sql_file"
            exit 1
        fi
    fi
done