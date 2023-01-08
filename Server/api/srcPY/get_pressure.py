#!/usr/bin/python3
import json
from sense_emu import SenseHat

sense = SenseHat()

press_hpa = sense.get_pressure() # millibars == hPa
press_mmhg = press_hpa * 0.75006156130264

result = {"press_hpa" : press_hpa, "press_mmhg": press_mmhg}

print(json.dumps(result))