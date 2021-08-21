#!/bin/bash

PID=$(ps -ef | grep "python3 server.py" | sed -n '1p' | awk '{print $2}')
kill -9 $PID

docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
