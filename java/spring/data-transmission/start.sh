#!/usr/bin/env bash

export DOCKER_HOST_IP=$(hostname)
export DATABASE_PASSWORD=P@ssw0rd

docker-compose up -d
