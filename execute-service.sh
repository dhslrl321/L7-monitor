#!/bin/bash
CONF_HOST_IP=$(awk '/^HOST_IP/{print $3}' boribob.conf)
DOCKER_HOST_IP=$(awk '/^services:/' docker-compose-test.yml)

DOCKER_COMPOSE_FILE=docker-compose-test.yml

echo CONF_HOST IP : $CONF_HOST_IP
echo DOCKER_HOST_IP : $DOCKER_HOST_IP

exit 0