#!/bin/bash

sleep 20
sudo chmod 777 /dev/shm/*
sleep 5

sudo touch /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/interval.txt
sudo chmod 777 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/interval.txt
echo 5 > /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/interval.txt

#Startup python scripts
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_accelerometer.py &
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_compass.py &
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_humidity.py &
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_joystick.py &
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_orientation.py &
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_pressure.py &
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_temperature.py &
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_leds.py &
python3 /home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/python/get_logs.py &
