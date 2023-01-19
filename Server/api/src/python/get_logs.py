#!/usr/bin/python3
import json
import time
import datetime
import os


json_keys = []
directory = "/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/"
json_dict = {}
interval_float = 1


def read_file(file_path: str):
    with open(file_path, 'r') as f:
        return f.read()

for file in os.listdir(directory):
        if file.endswith(".dat"):
            data = read_file(directory + file)
            key = file.replace('.dat', '')
            json_keys.append(key)
            json_dict[key] = []
            json_dict["timestamp"] = []

while(True):
    json_dict["timestamp"].append(str(datetime.datetime.now()))
    for file in os.listdir(directory):
        if file.endswith(".dat"):
            data = read_file(directory + file)
            key = file.replace('.dat', '')
            json_dict[key].append(data)

    if len(json_dict[key]) > 10:
        for k in json_keys:
            json_dict[k].pop(0)
        json_dict["timestamp"].pop(0)

    log = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/logs.json", 'w')
    log.write(str(json_dict))
    log.close()

    time_interval = read_file(directory + "interval.txt")
    interval_float = float(time_interval)
    time.sleep(interval_float)
