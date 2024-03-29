#!/usr/bin/python3
import json
from sense_emu import SenseHat
import time
import datetime


sense = SenseHat()

while(True):
    press_hpa = sense.get_pressure() # millibars == hPa
    press_mmhg = press_hpa * 0.75006156130264
    result = {"press_hpa" : press_hpa, "press_mmhg": press_mmhg}
    date = {"timestamp": str(datetime.datetime.now())}
    result.update(date)
    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/pressure.dat", "w")
    f.write(json.dumps(result))
    f.close()
    time.sleep(0.1)
