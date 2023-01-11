#!/usr/bin/python3
from sense_emu import SenseHat
import json
import time


sense = SenseHat()

midPresses = 0
axisX = 0
axisY = 0
res_dict = {}

while True:
    for event in sense.stick.get_events():
        if event.action == 'pressed' or event.action == 'held':
            if event.direction == 'up':
                axisY += 1
            if event.direction == 'down':
                axisY -= 1
            if event.direction == 'right':
                axisX += 1
            if event.direction == 'left':
                axisX -= 1

        if event.action == 'pressed':
            if event.direction == 'middle':
                midPresses += 1


    res_dict = {"X": axisX, "Y": axisY, "Mid": midPresses}
    
    result_json = json.dumps(res_dict)
    f = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/joystick.dat", "w")
    f.write(result_json)
    f.close()
    
    time.sleep(1)


