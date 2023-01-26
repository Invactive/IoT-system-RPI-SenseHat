#!/usr/bin/python3
import json
import time
import datetime
import os


json_keys = []
directory = "/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/"
json_dict = {}
interval_float = 1


<<<<<<< HEAD
def read_file(file_path):
    with open(file_path, 'r') as f:
        return f.read()

# Adding keys to dict from filenames
for file in os.listdir(directory):
        if file.endswith(".dat"):
=======
def read_file(file_path: str):
    with open(file_path, 'r') as f:
        return f.read()

for file in os.listdir(directory):
        if file.endswith(".dat"):
            data = read_file(directory + file)
>>>>>>> Mobile_app
            key = file.replace('.dat', '')
            json_keys.append(key)
            json_dict[key] = []
            json_dict["timestamp"] = []

while(True):
    json_dict["timestamp"].append(str(datetime.datetime.now()))
    for file in os.listdir(directory):
        if file.endswith(".dat"):
<<<<<<< HEAD
            try:
                data = read_file(directory + file)
                data = json.loads(data)
            except:
                pass
=======
            data = read_file(directory + file)
>>>>>>> Mobile_app
            key = file.replace('.dat', '')
            json_dict[key].append(data)

    if len(json_dict[key]) > 10:
        for k in json_keys:
            json_dict[k].pop(0)
        json_dict["timestamp"].pop(0)

    log = open("/home/jakub/Desktop/IoT-system-RPI-SenseHat/Server/api/src/data/logs.json", 'w')
<<<<<<< HEAD
    log.write(json.dumps(json_dict))
=======
    log.write(str(json_dict))
>>>>>>> Mobile_app
    log.close()

    time_interval = read_file(directory + "interval.txt")
    interval_float = float(time_interval)
<<<<<<< HEAD
    time.sleep(interval_float)
=======
    time.sleep(interval_float)
>>>>>>> Mobile_app
