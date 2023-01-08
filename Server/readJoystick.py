#!/usr/bin/python3

from sense_emu import SenseHat

import json
from time import sleep

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
    file = open('joystick.dat', 'w')
    file.write(result_json)
    file.close()
    
    sleep(1)


