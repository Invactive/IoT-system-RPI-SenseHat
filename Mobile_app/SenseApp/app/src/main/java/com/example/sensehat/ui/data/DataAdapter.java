package com.example.sensehat.ui.data;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;

public class DataAdapter extends BaseAdapter {
    Context context;
    JSONArray rgb;

    LayoutInflater inflater;

    public DataAdapter(Context context, JSONArray rgb){
        this.context = context;
        this.rgb = rgb;
    }

    @Override
    public int getCount() {
        return rgb.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null)
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null){}
        try {
            int r = (int) rgb.getJSONArray(i).get(0);
            int g = (int) rgb.getJSONArray(i).get(1);
            int b = (int) rgb.getJSONArray(i).get(2);
            String hexRGB = String.format("#%02x%02x%02x", rgb.getJSONArray(i).get(0), rgb.getJSONArray(i).get(1), rgb.getJSONArray(i).get(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
