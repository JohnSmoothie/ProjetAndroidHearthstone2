package com.example.e164572h.projethearthstone;

/**
 * Created by Florian on 29/03/2018.
 */


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;

import java.util.ArrayList;

public class FillSpinner {
    private Context cont;

    public FillSpinner(Context context){
        this.cont = context;
    }

    public void fillSpinner(String category, Spinner spinner){
        Request request = new Request(cont);
        JSONArray jArray = request.getInfo(category);

        ArrayList<String>  liste = new ArrayList<String>();
        String s = jArray.toString();
        Log.d("DEBUGGER",s);
        Integer [] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(cont, android.R.layout.simple_spinner_dropdown_item, data);
        spinner.setAdapter(adapter);
    }

}
