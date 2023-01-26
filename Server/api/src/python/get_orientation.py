#!/usr/bin/python3
from sense_emu import SenseHat
import json
import time
<<<<<<< HEAD
import datetime

=======
>>>>>>> Mobile_app

sense = SenseHat()

while(True):
    ori_rad = sense.get_orientation_radians()
    ori_deg = sense.get_orientation_degrees()
    orientations = {"radians": ori_rad, "degrees": ori_deg}
<<<<<<< HEAD
    date = {"timestamp": str(datetime.datetime.now())}
    orientations.update(date)
=======

>>>>>>> Mobile_app
    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/orientation.dat", "w")
    f.write(json.dumps(orientations))
    f.close()
    time.sleep(0.1)