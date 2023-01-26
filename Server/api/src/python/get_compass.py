#!/usr/bin/python3
import json
from sense_emu import SenseHat
import time
import datetime

sense = SenseHat()

while(True):
    comp = sense.get_compass()
    date = {"timestamp": str(datetime.datetime.now())}
    data = json.dumps({"north": comp})
    data = json.loads(data)
    data.update(date)
    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/compass.dat", "w")
    f.write(json.dumps(data))
    f.close()
    time.sleep(0.1)