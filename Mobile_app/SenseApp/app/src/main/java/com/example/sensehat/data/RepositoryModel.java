package com.example.sensehat.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Singleton pattern
 */
public class RepositoryModel {

    // TEMP STRING FOR TESTING PURPOSES
    private String jsonTempString;
    private String jsonPressString;
    private String jsonHumiString;
    private String jsonAccelString;
    private String jsonOrientString;
    private String jsonCompassString;
    private String jsonJoystickString;
    private String jsonLedsString;
    private String jsonLogsString;

    OkHttpClient client;
    private String URL;

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
            getRequest("temperature");
//            Get temp object - temperature from temperature sensor
            JSONObject tempJsonObj = new JSONObject(this.jsonTempString);
//            Get timestamp
            dataHashMap.put("timestamp", tempJsonObj.getString("timestamp"));
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
            getRequest("pressure");
//            Get pressure object - data from pressure sensor
            JSONObject pressJsonObj = new JSONObject(this.jsonPressString);
//            Get timestamp
            dataHashMap.put("timestamp", pressJsonObj.getString("timestamp"));
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
            getRequest("humidity");
//            Get humidity object - data from humidity sensor
            JSONObject humiJsonObj = new JSONObject(this.jsonHumiString);
//            Get timestamp
            dataHashMap.put("timestamp", humiJsonObj.getString("timestamp"));
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
            getRequest("accelerometer");
//            Get accelerometer object - data from IMU sensor
            JSONObject accelJsonObj = new JSONObject(this.jsonAccelString);
//            Get timestamp
            dataHashMap.put("timestamp", accelJsonObj.getString("timestamp"));
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
            getRequest("orientation");
//            Get orientation object - data from IMU sensor
            JSONObject orientJsonObj = new JSONObject(this.jsonOrientString);
//            Get timestamp
            dataHashMap.put("timestamp", orientJsonObj.getString("timestamp"));
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
            getRequest("compass");
//            Get compass object - data from IMU sensor
            JSONObject compassJsonObj = new JSONObject(this.jsonCompassString);
//            Get timestamp
            dataHashMap.put("timestamp", compassJsonObj.getString("timestamp"));
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
            getRequest("joystick");
//            Get joystick object - data from joystick
            JSONObject joystickJsonObj = new JSONObject(this.jsonJoystickString);
//            Get timestamp
            dataHashMap.put("timestamp", joystickJsonObj.getString("timestamp"));
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
            getRequest("leds");
//            Get leds object - data from LED matrix
            JSONObject ledsJsonObj = new JSONObject(this.jsonLedsString);
//            Get timestamp
            dataHashMap.put("timestamp", ledsJsonObj.getString("timestamp"));
//            Get RGB array of every LED from matrix
            for(int i=0; i<ledsJsonObj.length()-1; i++){
                ArrayList<Integer> arrayList = new ArrayList<>();
                for(int j=0; j<ledsJsonObj.getJSONArray(Integer.toString(i)).length(); j++){
                    arrayList.add(ledsJsonObj.getJSONArray(Integer.toString(i)).getInt(j));
                }
                dataHashMap.put(Integer.toString(i), arrayList);
            }

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


    public void getRequest(String param) throws InterruptedException {
        String url = this.URL + "get/" + "get_" + param + ".php";
        Request request = new Request.Builder()
                .url(url)
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                System.out.println("Connection error");
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    try {
                        System.out.println("Fetching response succesful");
                        bodyDestinationSolver(response.body().string(), url);
                    } catch (ProtocolException e) {
                        System.out.println("Fetching error - empty response. " + e);
                    }
                }
                else{
                    System.out.println("Fetching response unsuccesful. Response code: " + response.code());
                }
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    private void bodyDestinationSolver(String body, String url){
        if(Objects.equals(url, "http://25.78.72.7/api/get/get_temperature.php")){
            jsonTempString = body;
        }
        else if(Objects.equals(url, "http://25.78.72.7/api/get/get_pressure.php")){
            jsonPressString = body;
        }
        else if(Objects.equals(url, "http://25.78.72.7/api/get/get_humidity.php")){
            jsonHumiString = body;
        }
        else if(Objects.equals(url, "http://25.78.72.7/api/get/get_accelerometer.php")){
            jsonAccelString = body;
        }
        else if(Objects.equals(url, "http://25.78.72.7/api/get/get_orientation.php")){
            jsonOrientString = body;
        }
        else if(Objects.equals(url, "http://25.78.72.7/api/get/get_compass.php")){
            jsonCompassString = body;
        }
        else if(Objects.equals(url, "http://25.78.72.7/api/get/get_joystick.php")){
            jsonJoystickString = body;
        }
        else if(Objects.equals(url, "http://25.78.72.7/api/get/get_leds.php")){
            jsonLedsString = body;
        }
        else if(Objects.equals(url, "http://25.78.72.7/api/get/get_logs.php")){
            jsonLogsString = body;
        }
    }

    public void putLedsRequest(int x, int y, int r, int g, int b){
        String url = this.URL + "put/set_leds.php";
        RequestBody requestBody = new FormBody.Builder()
                .add("x", "6")
                .add("y", "4")
                .add("r", "255")
                .add("g", "4")
                .add("b", "100")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .put(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                System.out.println("Connection error");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if(response.isSuccessful()){
                        System.out.println("Put led succesful");
                }
                else{
                    System.out.println("Put led unsuccesful. Response code: " + response.code() + ". Message: " + response.message());
                }
            }
        });
    }

    public void putIntervalRequest(String interval){
        String url = this.URL + "put/set_interval.php";
        RequestBody requestBody = new FormBody.Builder()
                .add("interval", "6")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .put(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                System.out.println("Connection error");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if(response.isSuccessful()){
                    System.out.println("Put interval succesful");
                    try {
                        System.out.println(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Put interval unsuccesful. Response code: " + response.code() + ". Message: " + response.message());
                }
            }
        });
    }

}
