#!/bin/bash

if [ $# -eq 0 ]
then
    echo "Usage: ./run.sh --build <front,api,all>"
    exit 1
fi

build_frontend() {
    $(docker image rm food-ordering-service-frontend > /tmp/err.txt)
    $(docker build --no-cache -t food-ordering-service-frontend frontend/ >> /tmp/err.txt)
}

build_api() {
    $(docker image rm food-ordering-service-api > /tmp/err.txt)
    $(cd api/foodordering && mvn clean -Dmaven.test.skip install && cd ../../)
    $(docker build --no-cache -t food-ordering-service-api api/foodordering >> /tmp/err.txt)
}

docker-compose down

if [ $# -ge 2 ]
then
    if [ $1 == "--build" ] && [$2 == "front" ]
    then
        build_frontend
    fi
    if [ $1 == "--build" ] && [ $2 == "api" ]
    then
        build_api
    fi
    if [ $1 == "--build" ] && [ $2 == "all" ]
    then
        build_frontend
        build_api
    fi
fi

docker-compose up
