#!/bin/bash
if [ $# -lt 1 ]; then
    echo "Error: Bash Script has no argument."
    echo "Uso: $0 [dev or prod]"
    exit 1
fi

PROJECT_DIR=$(pwd)
ENV_FILE=""
export PROJECT_DIR

check_env_file() {
    ENV_FILE="${PROJECT_DIR}"/config/."$1".env 
    if [ -f $ENV_FILE ]; then
        echo "File .$1.env found."
    else
        echo "Error: File .$1.env not found."
        exit 1
    fi
}

if [ $1 == "dev" ]; then
    echo "Running in Development Mode"
    check_env_file $1
    docker compose -f config/docker/docker-compose.yml --env-file $ENV_FILE up -d
    clear
elif [ $1 == "prod" ]; then
    echo "Running in Production Mode"
    check_env_file $1
    docker compose -f config/docker/docker-compose.yml -f config/docker-compose.prod.yml --env-file $ENV_FILE up -d
    clear
elif [ $1 == "down" ]; then
    echo "Stopping containers"
    check_env_file "dev"
    docker compose -f config/docker/docker-compose.yml --env-file $ENV_FILE down
    clear
elif [ $1 == "down-prod" ]; then
    echo "Stopping containers"
    check_env_file "prod"
    docker compose -f config/docker/docker-compose.yml -f config/docker-compose.prod.yml --env-file $ENV_FILE down
    clear
else
    echo "Error: Invalid argument."
    echo "Use: $0 [dev or prod]"
    exit 1
fi