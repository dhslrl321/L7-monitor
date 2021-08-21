#!/bin/bash

docker-compose up -d --build
cd daemon
pip3 install -r requirements.txt
python3 server.py &

