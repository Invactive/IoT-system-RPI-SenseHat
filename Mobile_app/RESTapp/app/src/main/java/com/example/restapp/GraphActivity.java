package com.example.restapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

public class GraphActivity extends AppCompatActivity {

    Button previous_button;
    ImageButton settingsBtn;
    GraphView graphTemp;
    GraphView graphHumi;
    LineGraphSeries<DataPoint> seriesTemp;
    LineGraphSeries<DataPoint> seriesHumi;
    Stack<Float> arrTemp = new Stack<>();
    Stack<Float> arrHumi = new Stack<>();

    Date currentTime;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    int rate = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // NO TITLE BAR
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_graph);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Toast.makeText(GraphActivity.this, "Set refresh rate by clicking spanner icon", Toast.LENGTH_LONG).show();

        previous_button = (Button) findViewById(R.id.backGraphBtn);
        settingsBtn = (ImageButton) findViewById(R.id.settingsBtn);
        graphTemp = (GraphView) findViewById(R.id.graphTemp);
        graphHumi = (GraphView) findViewById(R.id.graphHumi);
        graphTemp.setTitle("Temperature");
        graphHumi.setTitle("Humidity");
        GridLabelRenderer graphTempRenderer = graphTemp.getGridLabelRenderer();
        graphTempRenderer.setHorizontalLabelsAngle(45);
        GridLabelRenderer graphHumiRenderer = graphHumi.getGridLabelRenderer();
        graphHumiRenderer.setHorizontalLabelsAngle(45);
        graphTempRenderer.setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX){
                if(isValueX){
                    return sdf.format(new Date((long) value));
                }
                else{
                    return super.formatLabel(value, isValueX);
                }
            }
        });
        graphHumiRenderer.setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX){
                if(isValueX){
                    return sdf.format(new Date((long) value));
                }
                else{
                    return super.formatLabel(value, isValueX);
                }
            }
        });



        seriesTemp = new LineGraphSeries<>();
        seriesHumi = new LineGraphSeries<>();
        previous_button.setOnClickListener(v -> {
            Intent intent = new Intent(GraphActivity.this, MainActivity.class);
            startActivity(intent);
        });

        settingsBtn.setOnClickListener(v ->
                showInputDialog()
        );

        final Handler timeHandler = new Handler();
        timeHandler.postDelayed(new Runnable(){
            public void run(){
                currentTime = Calendar.getInstance().getTime();
                getRequestDrawGraphs(currentTime);
                timeHandler.postDelayed(this, rate);
            }
        }, rate); // takes millis
    }

    private void getRequestDrawGraphs(Date currentTime){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.156/getEverything.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("TEST", ("Response is: " + response));
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        // GRAPH TEMP
                        String temp = jsonObject.get("temperature").toString();
                        float tempFloat = Float.parseFloat(temp.replaceAll("[^\\d.]", ""));
                        arrTemp.push(tempFloat);
                        seriesTemp.appendData(new DataPoint(currentTime, tempFloat), true, 10);
                        graphTemp.addSeries(seriesTemp);

                        // GRAPH HUMIDITY
                        String humi = jsonObject.get("humidity").toString();
                        float humiFloat = Float.parseFloat(humi.replaceAll("[^\\d.]", ""));
                        arrHumi.push(humiFloat);
                        seriesHumi.appendData(new DataPoint(currentTime, humiFloat), true, 10);
                        graphHumi.addSeries(seriesHumi);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.d("TEST", "That didn't work! " + error));
        queue.add(stringRequest);
    }

    private void showInputDialog() {
        final Dialog dialog = new Dialog(GraphActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.refr_rate_dialog);

        //Initializing the view of the dialog.
        final EditText refrRateET = dialog.findViewById(R.id.refrRateET);
        Button submitButton = dialog.findViewById(R.id.submit_button);

        submitButton.setOnClickListener(v -> {
            if(!refrRateET.getText().toString().isEmpty()) {
                rate = Integer.parseInt(refrRateET.getText().toString());
            }
            dialog.dismiss();
        });

        dialog.show();
    }

}
