package com.example.restapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restapp.databinding.ActivityDataBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataActivity extends AppCompatActivity {

    ActivityDataBinding binding;
    Button previous_button;
    TableLayout tab;

    int rowCounter = 0;

    int rate = 2000;

    ArrayList<String> JoyArr = new ArrayList<>();

    ArrayList<String> TempArr = new ArrayList<>();
    ArrayList<String> HumiArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // NO TITLE BAR
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tab = (TableLayout) findViewById(R.id.table);
        previous_button = (Button) findViewById(R.id.backDataBtn);

        previous_button.setOnClickListener(v -> {
            Intent intent = new Intent(DataActivity.this, MainActivity.class);
            startActivity(intent);
        });

        addtable();

        final Handler timeHandler = new Handler();
        timeHandler.postDelayed(new Runnable(){
            public void run(){
                getRequest();
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                int second = currentTime.get(Calendar.SECOND);
                System.out.println(JoyArr);
                System.out.println(JoyArr.size());
                rowCounter += 1;
                if(JoyArr.size() > 0) {
                    drawTable(hour, minute, second);
                }
                timeHandler.postDelayed(this, rate);
            }
        }, rate); // takes millis

    }

    private void addtable(){
        TableRow tabRow = new TableRow(this);
        TextView header1 = new TextView(this);
        header1.setText("Timestamp");
        header1.setGravity(Gravity.CENTER);
        header1.setPadding(30, 30, 30, 30);
        header1.setTextColor(Color.WHITE);
        tabRow.addView(header1);

        TextView header2 = new TextView(this);
        header2.setText("Temperature");
        header2.setGravity(Gravity.CENTER);
        header2.setPadding(30, 30, 30, 30);
        header2.setTextColor(Color.WHITE);
        tabRow.addView(header2);

        TextView header3 = new TextView(this);
        header3.setText("Humidity");
        header3.setGravity(Gravity.CENTER);
        header3.setTextColor(Color.WHITE);
        header3.setPadding(30, 30, 30, 30);
        tabRow.addView(header3);

        TextView header4 = new TextView(this);
        header4.setText("Joystick");
        header4.setGravity(Gravity.CENTER);
        header4.setPadding(30, 30, 30, 30);
        header4.setTextColor(Color.WHITE);
        tabRow.addView(header4);

        tab.addView(tabRow);
    }

    private void getRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.156/getEverything.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String JoyX = jsonObject.getString("X");
                        String JoyY = jsonObject.getString("Y");
                        String JoyMid = jsonObject.getString("Mid");
                        String Joystick = JoyX + ',' + JoyY + ',' + JoyMid;
                        JoyArr.add(Joystick);
                        limiter(JoyArr);
                        String Temp = jsonObject.getString("temperature");
                        TempArr.add(Temp);
                        limiter(TempArr);
                        String Humi = jsonObject.getString("humidity");
                        HumiArr.add(Humi);
                        limiter(HumiArr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.d("TEST", "That didn't work! " + error));

        queue.add(stringRequest);
    }

    private void limiter(ArrayList<String> arr){
        if(arr.size() > 10){
            arr.remove(0);
        }
    }

    private void drawTable(int hour, int minute, int second){
        TableRow recordRow = new TableRow(this);
        recordRow.setId(rowCounter);

        TextView t1v = new TextView(this);

        if(rowCounter > 10){
            tab.removeView((TableRow)findViewById(rowCounter-10));
        }
        t1v.setText(Integer.toString(hour) + ":" + Integer.toString(minute) + ":" + Integer.toString(second));
        t1v.setPadding(30, 30, 30, 30);
        t1v.setTextColor(Color.WHITE);
        t1v.setGravity(Gravity.CENTER);
        recordRow.addView(t1v);

        TextView t2v = new TextView(this);
        t2v.setText(TempArr.get(TempArr.size()-1));
        t2v.setPadding(30, 30, 30, 30);
        t2v.setTextColor(Color.WHITE);
        t2v.setGravity(Gravity.CENTER);
        recordRow.addView(t2v);

        TextView t3v = new TextView(this);
        t3v.setText(HumiArr.get(TempArr.size()-1));
        t3v.setPadding(30, 30, 30, 30);
        t3v.setTextColor(Color.WHITE);
        t3v.setGravity(Gravity.CENTER);
        recordRow.addView(t3v);

        TextView t4v = new TextView(this);
        t4v.setText(JoyArr.get(TempArr.size()-1));
        t4v.setPadding(30, 30, 30, 30);
        t4v.setTextColor(Color.WHITE);
        t4v.setGravity(Gravity.CENTER);
        recordRow.addView(t4v);

        tab.addView(recordRow);
    }

}
