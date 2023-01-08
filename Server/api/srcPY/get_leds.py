#!/usr/bin/python3
import json
from sense_emu import SenseHat

sense = SenseHat()
pixel_list = sense.get_pixels()

idx = 0
result = {}
for i in pixel_list:
    result[idx] = i
    idx += 1

print(json.dumps(result))
