package com.example.sensehat.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Singleton pattern
 */
public class RepositoryModel {

    // TEMP STRING FOR TESTING PURPOSES
    public static String jsonTempString;
    public static String jsonPressString;
    private String jsonHumiString = "{\"humidity\": 44.73046875}";
    private String jsonAccelString = "{\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 0.0}";
    private String jsonOrientString = "{\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}";
    private String jsonCompassString = "{\"north\": 267.3015616747542}";
    private String jsonJoystickString = "{\"X\": 0, \"Y\": 0, \"Mid\": 0}";
    private String jsonLedsString = "{\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0], \"timestamp\": \"2023-01-19 21:37:38.405584\"}";
    private String jsonLogsString = "{\"joystick\": [{\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}, {\"X\": 0, \"Y\": 0, \"Mid\": 0}], \"timestamp\": [\"2023-01-19 17:34:58.726137\", \"2023-01-19 17:35:03.733223\", \"2023-01-19 17:35:08.741881\", \"2023-01-19 17:35:13.748592\", \"2023-01-19 17:35:18.761532\", \"2023-01-19 17:35:23.764720\", \"2023-01-19 17:35:28.769130\", \"2023-01-19 17:35:33.773452\", \"2023-01-19 17:35:38.782861\", \"2023-01-19 17:35:43.788602\"], \"compass\": [{\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}, {\"north\": 267.3015616747542}], \"pressure\": [{\"press_hpa\": 1013.00830078125, \"press_mmhg\": 759.8185876965188}, {\"press_hpa\": 1013.0078125, \"press_mmhg\": 759.818221455522}, {\"press_hpa\": 1013.008056640625, \"press_mmhg\": 759.8184045760204}, {\"press_hpa\": 1013.001220703125, \"press_mmhg\": 759.8132772020662}, {\"press_hpa\": 1013.00830078125, \"press_mmhg\": 759.8185876965188}, {\"press_hpa\": 1012.99853515625, \"press_mmhg\": 759.8112628765841}, {\"press_hpa\": 1013.00048828125, \"press_mmhg\": 759.812727840571}, {\"press_hpa\": 1013.008056640625, \"press_mmhg\": 759.8184045760204}, {\"press_hpa\": 1012.98974609375, \"press_mmhg\": 759.804670538643}, {\"press_hpa\": 1012.99853515625, \"press_mmhg\": 759.8112628765841}], \"accelerometer\": [{\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}, {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}], \"temperature\": [{\"temp\": {\"tempC\": 24.984375, \"tempF\": 76.97187500000001}, \"temp_humi\": {\"tempC\": 24.984375, \"tempF\": 76.97187500000001}, \"temp_press\": {\"tempC\": 25.025, \"tempF\": 77.045}}, {\"temp\": {\"tempC\": 25.0, \"tempF\": 77.0}, \"temp_humi\": {\"tempC\": 25.0, \"tempF\": 77.0}, \"temp_press\": {\"tempC\": 24.84166666666667, \"tempF\": 76.715}}, {\"temp\": {\"tempC\": 24.984375, \"tempF\": 76.97187500000001}, \"temp_humi\": {\"tempC\": 24.984375, \"tempF\": 76.97187500000001}, \"temp_press\": {\"tempC\": 25.01875, \"tempF\": 77.03375}}, {\"temp\": {\"tempC\": 25.015625, \"tempF\": 77.028125}, \"temp_humi\": {\"tempC\": 25.015625, \"tempF\": 77.028125}, \"temp_press\": {\"tempC\": 24.947916666666664, \"tempF\": 76.90625}}, {\"temp\": {\"tempC\": 25.03125, \"tempF\": 77.05625}, \"temp_humi\": {\"tempC\": 25.03125, \"tempF\": 77.05625}, \"temp_press\": {\"tempC\": 25.1125, \"tempF\": 77.2025}}, {\"temp\": {\"tempC\": 24.984375, \"tempF\": 76.97187500000001}, \"temp_humi\": {\"tempC\": 24.984375, \"tempF\": 76.97187500000001}, \"temp_press\": {\"tempC\": 25.06458333333333, \"tempF\": 77.11625000000001}}, {\"temp\": {\"tempC\": 25.0, \"tempF\": 77.0}, \"temp_humi\": {\"tempC\": 25.0, \"tempF\": 77.0}, \"temp_press\": {\"tempC\": 24.945833333333333, \"tempF\": 76.9025}}, {\"temp\": {\"tempC\": 25.0, \"tempF\": 77.0}, \"temp_humi\": {\"tempC\": 25.0, \"tempF\": 77.0}, \"temp_press\": {\"tempC\": 25.00625, \"tempF\": 77.01125}}, {\"temp\": {\"tempC\": 25.015625, \"tempF\": 77.028125}, \"temp_humi\": {\"tempC\": 25.015625, \"tempF\": 77.028125}, \"temp_press\": {\"tempC\": 25.06041666666667, \"tempF\": 77.10875000000001}}, {\"temp\": {\"tempC\": 24.984375, \"tempF\": 76.97187500000001}, \"temp_humi\": {\"tempC\": 24.984375, \"tempF\": 76.97187500000001}, \"temp_press\": {\"tempC\": 24.960416666666667, \"tempF\": 76.92875000000001}}], \"pixels\": [{\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}, {\"0\": [0, 0, 0], \"1\": [0, 0, 0], \"2\": [0, 0, 0], \"3\": [0, 0, 0], \"4\": [0, 0, 0], \"5\": [0, 0, 0], \"6\": [0, 0, 0], \"7\": [0, 0, 0], \"8\": [0, 0, 0], \"9\": [0, 0, 0], \"10\": [0, 0, 0], \"11\": [0, 0, 0], \"12\": [0, 0, 0], \"13\": [0, 0, 0], \"14\": [0, 0, 0], \"15\": [0, 0, 0], \"16\": [0, 0, 0], \"17\": [0, 0, 0], \"18\": [0, 0, 0], \"19\": [0, 0, 0], \"20\": [0, 0, 0], \"21\": [0, 0, 0], \"22\": [0, 0, 0], \"23\": [0, 0, 0], \"24\": [0, 0, 0], \"25\": [0, 0, 0], \"26\": [0, 0, 0], \"27\": [0, 0, 0], \"28\": [0, 0, 0], \"29\": [0, 0, 0], \"30\": [0, 0, 0], \"31\": [0, 0, 0], \"32\": [0, 0, 0], \"33\": [0, 0, 0], \"34\": [0, 0, 0], \"35\": [0, 0, 0], \"36\": [0, 0, 0], \"37\": [0, 0, 0], \"38\": [0, 0, 0], \"39\": [0, 0, 0], \"40\": [0, 0, 0], \"41\": [0, 0, 0], \"42\": [0, 0, 0], \"43\": [0, 0, 0], \"44\": [0, 0, 0], \"45\": [0, 0, 0], \"46\": [0, 0, 0], \"47\": [0, 0, 0], \"48\": [0, 0, 0], \"49\": [0, 0, 0], \"50\": [0, 0, 0], \"51\": [0, 0, 0], \"52\": [0, 0, 0], \"53\": [0, 0, 0], \"54\": [0, 0, 0], \"55\": [0, 0, 0], \"56\": [0, 0, 0], \"57\": [0, 0, 0], \"58\": [0, 0, 0], \"59\": [0, 0, 0], \"60\": [0, 0, 0], \"61\": [0, 0, 0], \"62\": [0, 0, 0], \"63\": [0, 0, 0]}], \"humidity\": [{\"humidity\": 45.25390625}, {\"humidity\": 45.0}, {\"humidity\": 45.14453125}, {\"humidity\": 44.9921875}, {\"humidity\": 45.06640625}, {\"humidity\": 45.03125}, {\"humidity\": 44.80078125}, {\"humidity\": 45.55078125}, {\"humidity\": 45.54296875}, {\"humidity\": 45.38671875}], \"orientation\": [{\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}, {\"radians\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": -1.6178929602324366}, \"degrees\": {\"roll\": 0.0, \"pitch\": 0.0, \"yaw\": 267.3015616747542}}]}";

    OkHttpClient client;
    private String URL;
    static boolean resultWeb = false;


    private MutableLiveData<HashMap<String, Object>> temperaturesData;
    private MutableLiveData<HashMap<String, Object>> pressureData;
    private MutableLiveData<HashMap<String, Object>> humidityData;
    private MutableLiveData<HashMap<String, Object>> accelerometerData;
    private MutableLiveData<HashMap<String, Object>> orientationData;
    private MutableLiveData<HashMap<String, Object>> compassData;
    private MutableLiveData<HashMap<String, Object>> joystickData;
    private MutableLiveData<HashMap<String, Object>> ledsData;
    private MutableLiveData<HashMap<String, Object>> logsData;

    public RepositoryModel() {
        temperaturesData = new MutableLiveData<>();
        pressureData = new MutableLiveData<>();
        humidityData = new MutableLiveData<>();
        accelerometerData = new MutableLiveData<>();
        orientationData = new MutableLiveData<>();
        compassData = new MutableLiveData<>();
        ledsData = new MutableLiveData<>();
        logsData = new MutableLiveData<>();
        joystickData = new MutableLiveData<>();

        client = new OkHttpClient();
    }

    public void setIP(String IP){
        this.URL = "http://" + IP + "/api/";
    }

    // Fetch current temperature for chart
    private void fetchTemperatureChart(){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        try {
            getRequest("temperature", this.jsonTempString);
//            Get temp object - temperature from temperature sensor
            JSONObject tempJsonObj = new JSONObject(this.jsonTempString);
            tempJsonObj = tempJsonObj.getJSONObject("temp");
//            Get tempC and tempF from temperature sensor
            dataHashMap.put("tempCTemp", tempJsonObj.getDouble("tempC"));
            dataHashMap.put("tempFTemp", tempJsonObj.getDouble("tempF"));
//            Get temp_press object - temperature from pressure sensor
            JSONObject tempPressJsonObj = new JSONObject(this.jsonTempString);
            tempPressJsonObj = tempPressJsonObj.getJSONObject("temp_press");
//            Get tempC and tempF from pressure sensor
            dataHashMap.put("tempCPress", tempPressJsonObj.getDouble("tempC"));
            dataHashMap.put("tempFPress", tempPressJsonObj.getDouble("tempF"));
//            Get temp_humi object - temperature from humidity sensor
            JSONObject tempHumiJsonObj = new JSONObject(this.jsonTempString);
            tempHumiJsonObj = tempHumiJsonObj.getJSONObject("temp_humi");
//            Get tempC and tempF from humidity sensor
            dataHashMap.put("tempCHumi", tempHumiJsonObj.getDouble("tempC"));
            dataHashMap.put("tempFHumi", tempHumiJsonObj.getDouble("tempF"));
//            Get timestamp
            dataHashMap.put("timestamp", tempHumiJsonObj.getString("tempC"));

            this.temperaturesData.setValue(dataHashMap);
        } catch (JSONException e) {
            System.out.println("Exception - json parse error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Exception - fetching data error");
        }
    }

    // Get fetched temperature
    public MutableLiveData<HashMap<String, Object>> getTemperatureDataChart(){
        fetchTemperatureChart();
        return this.temperaturesData;
    }

    private void fetchPressureChart(){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        try {
            getRequest("pressure", this.jsonPressString);
//            Get pressure object - data from pressure sensor
            JSONObject pressJsonObj = new JSONObject(this.jsonTempString);
//            Get pressure in hPa and mmHg
            dataHashMap.put("pressHpa", pressJsonObj.getDouble("press_hpa"));
            dataHashMap.put("pressMmhg", pressJsonObj.getDouble("press_mmhg"));

            this.pressureData.setValue(dataHashMap);
        } catch (JSONException e) {
            System.out.println("Exception - json parse error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Exception - fetching data error");
        }
    }

    public MutableLiveData<HashMap<String, Object>> getPressureDataChart(){
        fetchPressureChart();
        return this.pressureData;
    }

    private void fetchHumidityChart(){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        try {
            getRequest("humidity", jsonHumiString);
//            Get humidity object - data from humidity sensor
            JSONObject humiJsonObj = new JSONObject(this.jsonHumiString);
//            Get humidity in %
            dataHashMap.put("humi", humiJsonObj.getDouble("humidity"));

            this.humidityData.setValue(dataHashMap);
        } catch (JSONException e) {
            System.out.println("Exception - json parse error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Exception - fetching data error");
        }
    }

    public MutableLiveData<HashMap<String, Object>> getHumidityDataChart(){
        fetchHumidityChart();
        return this.humidityData;
    }

    private void fetchAccelerometerChart(){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        try {
            getRequest("accelerometer", jsonAccelString);
//            Get accelerometer object - data from IMU sensor
            JSONObject accelJsonObj = new JSONObject(this.jsonAccelString);
//            Get roll, pitch, yaw in degrees
            dataHashMap.put("roll", accelJsonObj.getDouble("roll"));
            dataHashMap.put("pitch", accelJsonObj.getDouble("pitch"));
            dataHashMap.put("yaw", accelJsonObj.getDouble("yaw"));

            this.accelerometerData.setValue(dataHashMap);
        } catch (JSONException e) {
            System.out.println("Exception - json parse error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Exception - fetching data error");
        }
    }

    public MutableLiveData<HashMap<String, Object>> getAccelerometerDataChart(){
        fetchAccelerometerChart();
        return this.accelerometerData;
    }

    private void fetchOrientationChart(){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        try {
            getRequest("orientation", jsonOrientString);
//            Get orientation object - data from IMU sensor
            JSONObject orientJsonObj = new JSONObject(this.jsonOrientString);
            JSONObject degJsonObj = orientJsonObj.getJSONObject("degrees");
            JSONObject radJsonObj = orientJsonObj.getJSONObject("radians");
//            Get roll, pitch, yaw in degrees and radians
            dataHashMap.put("roll_deg", degJsonObj.getDouble("roll"));
            dataHashMap.put("pitch_deg", degJsonObj.getDouble("pitch"));
            dataHashMap.put("yaw_deg", degJsonObj.getDouble("yaw"));
            dataHashMap.put("roll_rad", radJsonObj.getDouble("roll"));
            dataHashMap.put("pitch_rad", radJsonObj.getDouble("pitch"));
            dataHashMap.put("yaw_rad", radJsonObj.getDouble("yaw"));

            this.orientationData.setValue(dataHashMap);
        } catch (JSONException e) {
            System.out.println("Exception - json parse error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Exception - fetching data error");
        }
    }

    public MutableLiveData<HashMap<String, Object>> getOrientationDataChart(){
        fetchOrientationChart();
        return this.orientationData;
    }

    private void fetchCompass(){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        try {
            getRequest("compass", jsonCompassString);
//            Get compass object - data from IMU sensor
            JSONObject compassJsonObj = new JSONObject(this.jsonCompassString);
//            Get north value in degrees
            dataHashMap.put("compass", compassJsonObj.getDouble("north"));

            this.compassData.setValue(dataHashMap);
        } catch (JSONException e) {
            System.out.println("Exception - json parse error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Exception - fetching data error");
        }
    }

    public MutableLiveData<HashMap<String, Object>> getCompassData(){
        fetchCompass();
        return this.compassData;
    }

    private void fetchJoystick(){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        try {
            getRequest("joystick", jsonJoystickString);
//            Get joystick object - data from joystick
            JSONObject joystickJsonObj = new JSONObject(this.jsonJoystickString);
//            Get number of moves in x, y axis and middle clicks
            dataHashMap.put("X", joystickJsonObj.getInt("X"));
            dataHashMap.put("Y", joystickJsonObj.getInt("Y"));
            dataHashMap.put("Mid", joystickJsonObj.getInt("Mid"));

            this.joystickData.setValue(dataHashMap);
        } catch (JSONException e) {
            System.out.println("Exception - json parse error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Exception - fetching data error");
        }
    }

    public MutableLiveData<HashMap<String, Object>> getJoystickData(){
        fetchJoystick();
        return this.joystickData;
    }

    private void fetchLeds(){
        HashMap<String, Object> dataHashMap = new HashMap<>();
        try {
            getRequest("leds", jsonLedsString);
//            Get leds object - data from LED matrix
            JSONObject ledsJsonObj = new JSONObject(this.jsonLedsString);
//            Get RGB array of every LED from matrix
            for(int i=0; i<ledsJsonObj.length()-1; i++){
                ArrayList<Integer> arrayList = new ArrayList<>();
                for(int j=0; j<ledsJsonObj.getJSONArray(Integer.toString(i)).length(); j++){
                    arrayList.add(ledsJsonObj.getJSONArray(Integer.toString(i)).getInt(j));
                }
                dataHashMap.put(Integer.toString(i), arrayList);
            }
            dataHashMap.put("timestamp", ledsJsonObj.getString("timestamp"));

            this.ledsData.setValue(dataHashMap);
        } catch (JSONException e) {
            System.out.println("Exception - json parse error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Exception - fetching data error");
        }
    }

    public MutableLiveData<HashMap<String, Object>> getLedsData(){
        fetchLeds();
        return this.ledsData;
    }

//    private void fetchLogs(){
////        TODO getRequest from server to get data
//
//        HashMap<String, ArrayList<Integer>> dataHashMap = new HashMap<>();
//        try {
////            Get logs object - data from all sensors with timestamps
//            JSONObject logsJsonObj = new JSONObject(this.jsonLogsString);
//
//
//            this.logsData.setValue(dataHashMap);
//        } catch (JSONException e) {
//            // Handle exception
//        }
//    }
//
//    public MutableLiveData<HashMap<String, ArrayList<Integer>>> getLogsData(){
//        fetchLogs();
//        return this.logsData;
//    }




    public void getRequest(String value, String destination) throws InterruptedException {
        String url = this.URL + "get/" + "get_" + value + ".php";
        Request request = new Request.Builder()
                .url(url)
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                resultWeb = false;
                e.printStackTrace();
                System.out.println("Connection error");
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    System.out.println("Fetching response succesful");
                    System.out.println(response.headers());
                    jsonTempString = response.body().string();
                    resultWeb = true;
                }
                else{
                    System.out.println("Fetching response unsuccesful, code: " + response.code());
                    resultWeb = false;
                }

                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }
    private void bodyDestinationSolver(String body){

    }

    // Put data to set values on server
//    private void putRequest(int x, int y, int r, int g, int b){
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://" + IP + ":80/ledPost.php";
//        // Prepare body request
//        try {
//            final JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("x", x);
//                jsonObject.put("y", y);
//                jsonObject.put("r", r);
//                jsonObject.put("g", g);
//                jsonObject.put("b", b);
//            } catch (JSONException e) {
//                // handle JSON exception
//            }
//
//            // Building a request
//            JsonObjectRequest request = new JsonObjectRequest(
//                    Request.Method.POST, url, jsonObject,
//                    response -> {
//                        try {
//                            Toast.makeText(this, "LED " + response.get("LED")+ " set", Toast.LENGTH_SHORT).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        Log.i("Response", response.toString());
//                    },
//                    error -> {
//                        int statusCode = error.networkResponse.statusCode;
//                        Toast.makeText(LEDSActivity.this, "Error Response ", Toast.LENGTH_SHORT).show();
//                        Log.e("Error", error.toString());
//                    });
//            Volley.newRequestQueue(getApplicationContext()).add(request);
//        } catch(JSONException ex){
//            Toast.makeText(LEDSActivity.this, "Error JSON parse", Toast.LENGTH_SHORT).show();
//        }
//    }

}
