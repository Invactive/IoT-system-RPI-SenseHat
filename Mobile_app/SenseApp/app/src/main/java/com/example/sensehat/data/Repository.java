package com.example.sensehat.data;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;

/**
 * Singleton pattern
 */
public class Repository{

    // TEMP STRING FOR TESTING PURPOSES
    public String jsonTempString = "{\"temp\": {\"tempC\": 67.0, \"tempF\": 152.60000000000002}, \"temp_humi\": {\"tempC\": 67.0, \"tempF\": 152.60000000000002}, \"temp_press\": {\"tempC\": 67.15625, \"tempF\": 152.88125000000002}}";
    private final String IP;
    private String urlParam;
//    private String dummyData = "dummy";
//    private String dummyData = "dummy";
//    private String dummyData = "dummy";
//    private String dummyData = "dummy";
//    private String dummyData = "dummy";
//    private String dummyData = "dummy";

    public Repository() {
        this.IP = "25.78.72.7";
    }

    // GET current temperature for chart
    public Dictionary getTemperature(){
//        TODO getRequest from server to get data
        try {
            JSONObject tempJson = new JSONObject(this.jsonTempString);
            tempJson = tempJson.getJSONObject("temp");
            return tempJson.getDouble("tempC");
//            Return dictionary of tempC from temp, temp_press and temp_humi
        } catch (JSONException e) {
            // Handle exception
            return null;
        }
    }


















//    public MutableLiveData<JSONObject> getTemperature(){
//        JSONObject json = getRequest("/api/get/get_temperature.php");
//
//        MutableLiveData<JSONObject> data = new MutableLiveData<>();
//        data.setValue(json);
//        return data;
//    }

    // Get data, pass urlParam to get data you need
//    public JSONObject getRequest(String urlParam) {
//        RequestQueue queue = Volley.newRequestQueue(context);
//        String url = "http://" + IP + this.urlParam;
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                response -> {
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }, error -> Log.d("TEST", "That didn't work! " + error));
//        queue.add(stringRequest);
//
//        return jsonObject;
//    }

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
