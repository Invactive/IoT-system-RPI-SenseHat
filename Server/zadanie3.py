#!/usr/bin/python3

from sense_emu import SenseHat
import sys
import getopt
import json

sense = SenseHat()

sysarg = sys.argv[1:]


setX = None
setY = None
setR = 0
setG = 0
setB = 0

try:
	opts, args = getopt.getopt(sysarg, 'x:y:r:g:b:')
except getopt.GetoptError as err:
	print(err)
	sys.exit(1)

for opt, arg in opts:
	if opt in ['-x']:
		if int(arg)>= 0 and int(arg) <=7:
			setX = int(arg)
		else:
			print("Podaj liczbe X z zakresu 0-7")

	if opt in ['-y']:
		if int(arg)>= 0 and int(arg) <=7:
			setY = int(arg)
		else:
			print("Podaj liczbe Y z zakresu 0-7")

	if opt in ['-r']:
		if int(arg)>= 0 and int(arg) <=255:
			setR = int(arg)
		else:
			print("Podaj liczbe R z zakresu 0-255")

	if opt in ['-g']:
		if int(arg)>= 0 and int(arg) <=255:
			setG = int(arg)
		else:
			print("Podaj liczbe G z zakresu 0-255")

	if opt in ['-b']:
		if int(arg)>= 0 and int(arg) <=255:
			setB = int(arg)
		else:
			print("Podaj liczbe B z zakresu 0-255")


if setX != None and setY != None:
	sense.set_pixel(setX, setY, setR, setG, setB)





