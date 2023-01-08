#!/usr/bin/python3

from sense_emu import SenseHat
import sys
import getopt
import json


sense = SenseHat()
result_dict = {}

sysarg = sys.argv[1:]

try:
	opts, args = getopt.getopt(sysarg, 'rpyu:')
except getopt.GetoptError as err:
	print(err)
	sys.exit(1)

for opt, arg in opts:
	if opt in ['-u']:
		if arg == 'deg':
			orientation = "deg"
		elif arg == 'rad':
			orientation = "rad"

	if opt in ['-r']:
		if orientation == 'deg':
			roll = sense.get_orientation_degrees()["roll"]
		elif orientation == 'rad':
			roll = sense.get_orientation_radians()["roll"]
		roll_dict = {"roll" : str(roll)}
		result_dict.update(roll_dict)

	if opt in ['-p']:
		if orientation == 'deg':
			pitch = sense.get_orientation_degrees()["pitch"]
		elif orientation == 'rad':
			pitch = sense.get_orientation_radians()["pitch"]
		pitch_dict = {"pitch" : str(pitch)}
		result_dict.update(pitch_dict)

	if opt in ['-y']:
		if orientation == 'deg':
			yaw = sense.get_orientation_degrees()["yaw"]
		elif orientation == 'rad':
			yaw = sense.get_orientation_radians()["yaw"]
		yaw_dict = {"yaw" : str(yaw)}
		result_dict.update(yaw_dict)

result_json = json.dumps(result_dict)
print(result_json)


