#!/usr/bin/env bash

# if [[ -z $1  ]]; then
#   echo "usage: ./build-docker.sh 001"
#   exit 1
# fi
#
./gradlew clean build -x check -x test
# docker build . -t spring-boot-playground:$1 -f Dockerfile
docker-compose up --build
