#!/usr/bin/python3

from sense_emu import SenseHat
import json

sense = SenseHat()

f = open("led.json")

data = json.load(f)
keyLin = data['LED']

if keyLin:
	#  Set led from x, y matrix
	setX = int(keyLin) % 8
	setY = int((int(keyLin) - setX) / 8)

	# Set color
	setR = data['R'] if data['R'] else 0
	setG = data['G'] if data['G'] else 0
	setB = data['B'] if data['B'] else 0

	sense.set_pixel(setX, setY, int(setR), int(setG), int(setB))

else:
	print("Unknown LED value")





