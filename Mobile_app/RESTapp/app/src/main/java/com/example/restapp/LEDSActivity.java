package com.example.restapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restapp.databinding.ActivityLedsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LEDSActivity extends AppCompatActivity {

    Button previous_button;
    ActivityLedsBinding binding;

    Button postBtn;
    EditText LEDpos;
    EditText red;
    EditText green;
    EditText blue;

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // NO TITLE BAR
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityLedsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        previous_button = (Button) findViewById(R.id.backLedsBtn);
        postBtn = (Button) findViewById(R.id.postLedBtn);
        LEDpos = (EditText) findViewById(R.id.ledNumEditText);
        red = (EditText) findViewById(R.id.rNumEditText);
        green = (EditText) findViewById(R.id.gNumEditText);
        blue = (EditText) findViewById(R.id.bNumEditText);

        EditText[] rgbArr = {red, green, blue};

        setInputFilters(63, 255, LEDpos, rgbArr);

        previous_button.setOnClickListener(v -> {
            Intent intent = new Intent(LEDSActivity.this, MainActivity.class);
            startActivity(intent);
        });
        postBtn.setOnClickListener(v -> {
//            putRequest(); // It works
            postRequest(); // Also works

            LEDpos.setText("");
            red.setText("");
            green.setText("");
            blue.setText("");
        });

        final Handler timeHandler = new Handler();
        timeHandler.postDelayed(new Runnable(){
            public void run(){
                getRequest();
                i += 1;
                timeHandler.postDelayed(this, 1000);
            }
        }, 1000); // takes millis
    }

    private void getRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.156/getEverything.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray rgb = jsonObject.getJSONArray("LEDS");
                        GridAdapter gridAdapter = new GridAdapter(LEDSActivity.this, rgb);
                        binding.gridView.setAdapter(gridAdapter);
                        binding.gridView.setOnItemClickListener((adapterView, view, i, l) ->
                                System.out.println("Clicked")
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.d("TEST", "That didn't work! " + error));

        queue.add(stringRequest);
    }

    private void putRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.156:80/ledPost.php";
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("LED", LEDpos.getText().toString());
            jsonObject.put("R", red.getText().toString());
            jsonObject.put("G", green.getText().toString());
            jsonObject.put("B", blue.getText().toString());
        } catch (JSONException e) {
            // handle exception
        }


        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {

                try {
                    Log.i("json", jsonObject.toString());
                    return jsonObject.toString().getBytes("UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        queue.add(putRequest);
    }

    private void postRequest(){
        String url = "http://192.168.1.156:80/ledPost.php";
        try {
            JSONObject jsonParams = new JSONObject();
            if(!LEDpos.getText().toString().isEmpty()) {
                jsonParams.put("LED", LEDpos.getText().toString());
                jsonParams.put("R", red.getText().toString());
                jsonParams.put("G", green.getText().toString());
                jsonParams.put("B", blue.getText().toString());
            }
            else{
                Toast.makeText(LEDSActivity.this, "LED Number not set!", Toast.LENGTH_SHORT).show();
            }

            // Building a request
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST, url, jsonParams,
                    response -> {
                        try {
                            Toast.makeText(LEDSActivity.this, "LED " + response.get("LED")+ " set", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("Response", response.toString());
                    },
                    error -> {
                        Toast.makeText(LEDSActivity.this, "Error Response ", Toast.LENGTH_SHORT).show();
                        Log.e("Error", error.toString());
                    });
            Volley.newRequestQueue(getApplicationContext()).add(request);
        } catch(JSONException ex){
            Toast.makeText(LEDSActivity.this, "Error JSON parse", Toast.LENGTH_SHORT).show();
        }
    }

    private void setInputFilters(int maxNumOfLEDS, int maxValRGB, EditText ledNumInput, EditText[] rgbArr){
        ledNumInput.setFilters(new InputFilter[]{ new InputFilterMinMax(0, maxNumOfLEDS)});

        for (EditText et : rgbArr) {
            et.setFilters(new InputFilter[]{ new InputFilterMinMax(0, maxValRGB)});
        }
    }

}
