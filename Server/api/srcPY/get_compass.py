#!/usr/bin/python3
import json
from sense_emu import SenseHat


sense = SenseHat()
comp = sense.get_compass()

result_json = json.dumps({"north": comp})

print(result_json)