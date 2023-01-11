#!/usr/bin/python3
import json
from sense_emu import SenseHat
import time


sense = SenseHat()

while(True):
    humi = sense.get_humidity()

    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/humidity.dat", "w")
    f.write(json.dumps({"humidity": humi}))
    f.close()
    time.sleep(0.1)