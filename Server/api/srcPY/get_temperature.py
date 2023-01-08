#!/usr/bin/python3
import json
from sense_emu import SenseHat

sense = SenseHat()

tempC = sense.get_temperature()
tempF = 1.8 * tempC + 32

temp_humiC = sense.get_temperature_from_humidity()
temp_humiF = 1.8 * temp_humiC + 32

temp_pressC = sense.get_temperature_from_pressure()
temp_pressF = 1.8 * temp_pressC + 32

result = {"temp": {"tempC":tempC, "tempF": tempF}, 
        "temp_humi": {"tempC": temp_humiC, "tempF": temp_humiF},
        "temp_press": {"tempC": temp_pressC, "tempF": temp_pressF}}


print(json.dumps(result))



