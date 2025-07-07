#!/bin/sh

# Fail the script on any error
set -e

# Print debug info
echo "Starting Spring Boot application..."

# Start the Spring Boot application
exec java -jar /app/app.jar
