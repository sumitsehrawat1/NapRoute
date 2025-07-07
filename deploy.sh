#!/bin/bash
set -e

if [ ! -s "/var/lib/postgresql/data/PG_VERSION" ]; then
  echo "Initializing database..."
  su-exec postgres initdb -D /var/lib/postgresql/data
fi

echo "Starting PostgreSQL..."
su-exec postgres postgres -D /var/lib/postgresql/data &

echo "Waiting for PostgreSQL to start..."
until pg_isready -U $POSTGRES_USER; do
  sleep 1
done

echo "Starting Spring Boot app..."
exec java -jar /app/app.jar
