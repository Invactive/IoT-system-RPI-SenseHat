#!/usr/bin/python3
import json
from sense_emu import SenseHat
import time


sense = SenseHat()

while(True):
    comp = sense.get_compass()

    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/compass.dat", "w")
    f.write(json.dumps({"north": comp}))
    f.close()
    time.sleep(0.1)