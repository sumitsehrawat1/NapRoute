#!/bin/sh

# Fail the script on any error
set -e

# Print the env variables
echo "Starting app with environment:"
env

# Print debug info
echo "Starting Spring Boot application..."

# Start the Spring Boot application
exec java -jar /app/app.jar
