#!/usr/bin/python3
from sense_emu import SenseHat
import json
import time
import datetime


sense = SenseHat()

while(True):
    ori_rad = sense.get_orientation_radians()
    ori_deg = sense.get_orientation_degrees()
    orientations = {"radians": ori_rad, "degrees": ori_deg}
    date = {"timestamp": str(datetime.datetime.now())}
    orientations.update(date)
    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/orientation.dat", "w")
    f.write(json.dumps(orientations))
    f.close()
    time.sleep(0.1)
