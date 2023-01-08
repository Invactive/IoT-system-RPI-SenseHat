#!/usr/bin/python3

from sense_emu import SenseHat
import json


sense = SenseHat()

ori_rad = sense.get_orientation_radians()
ori_deg = sense.get_orientation_degrees()

orientations = {"radians": ori_rad, "degrees": ori_deg}

print(json.dumps(orientations))

