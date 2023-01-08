#!/bin/sh

sudo chmod 777 /dev/shm/*
echo "Finished"

python3 /var/www/html/readJoystick.py
