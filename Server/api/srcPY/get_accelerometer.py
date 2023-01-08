#!/usr/bin/python3
import json
from sense_emu import SenseHat

sense = SenseHat()

accel = sense.get_accelerometer()

print(json.dumps(accel))
