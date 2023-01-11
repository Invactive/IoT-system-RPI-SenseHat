#!/usr/bin/python3

from sense_emu import SenseHat

sense = SenseHat()

O = [0, 0, 0]
all = []
for i in range(64):
    all.append(O)
sense.set_pixels(all)