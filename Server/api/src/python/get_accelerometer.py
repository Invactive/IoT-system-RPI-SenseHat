#!/usr/bin/python3
import json
from sense_emu import SenseHat
import time


sense = SenseHat()

while(True):
    accel = sense.get_accelerometer()

    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/accelerometer.dat", "w")
    f.write(json.dumps(accel))
    f.close()
    time.sleep(0.1)