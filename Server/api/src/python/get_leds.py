#!/usr/bin/python3
import json
from sense_emu import SenseHat
import time
import datetime


sense = SenseHat()

while(True):
    pixel_list = sense.get_pixels()
    idx = 0
    result = {}
    for i in pixel_list:
        result[idx] = i
        idx += 1
    date = {"timestamp": str(datetime.datetime.now())}
    result.update(date)
    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/pixels.dat", "w")
    f.write(json.dumps(result))
    f.close()
    time.sleep(0.1)