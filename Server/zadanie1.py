#!/usr/bin/python3

from sense_emu import SenseHat
import sys
import getopt
import json

sense = SenseHat()

result_dict = {}

sysarg = sys.argv[1:]

try:
	opts, args = getopt.getopt(sysarg, 'h:p:t:')
except getopt.GetoptError as err:
	print(err)
	sys.exit(1)

for opt, arg in opts:
	if opt in ['-h']:
		humidity = sense.get_humidity() # %
		if arg == '%':
			humidity_res = str(humidity) + "%"
		elif float(arg) >= 0 and float(arg) <= 1:
			humidity_res = humidity / 100
		humidity_dict = {"humidity" : str(humidity_res)}
		result_dict.update(humidity_dict)

	if opt in ['-p']:
		pressure = sense.get_pressure() # millibars == hPa
		if arg == 'hPa':
			pressure_res = str(pressure) + "hPa"
		elif arg == 'mmHg':
			pressure_res = str(pressure * 0.75006156130264) + "mmHg"
		pressure_dict = {"pressure" : pressure_res}
		result_dict.update(pressure_dict)

	if opt in ['-t']:
		tempC = sense.get_temperature() # C
		if arg == 'C':
			temp_res = str(tempC) + "C"
		elif arg == 'F':
			temp_res = str(1.8 * tempC + 32) + "F"
		temperature_dict = {"temperature" : temp_res}
		result_dict.update(temperature_dict)

result_json = json.dumps(result_dict)

print(str(result_json))


