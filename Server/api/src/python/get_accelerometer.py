#!/usr/bin/python3
import json
from sense_emu import SenseHat
import time
import datetime


sense = SenseHat()

while(True):
    accel = sense.get_accelerometer()
    date = {"timestamp": str(datetime.datetime.now())}
    data = json.dumps(accel)
    data = json.loads(data)
    data.update(date)
    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/accelerometer.dat", "w")
    f.write(json.dumps(data))
    f.close()
    time.sleep(0.1)