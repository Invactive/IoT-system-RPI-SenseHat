#!/usr/bin/python3

from sense_emu import SenseHat

sense = SenseHat()
pixel_list = sense.get_pixels()
print(pixel_list)