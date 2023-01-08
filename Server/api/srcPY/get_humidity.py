#!/usr/bin/python3
import json
from sense_emu import SenseHat


sense = SenseHat()
humi = sense.get_humidity()

result_json = json.dumps({"humidity": humi})

print(result_json)