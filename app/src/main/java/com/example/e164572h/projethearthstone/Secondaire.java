package com.example.e164572h.projethearthstone;

/**
 * Created by E164572H on 03/04/18.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Secondaire extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondaire);

//        final

        String itemFromChoice = getIntent().getStringExtra("item");
        String categoryOfChoice = getIntent().getStringExtra("category");
        String categoryOfChoiceEN = getIntent().getStringExtra("ITEM_EN");
        Log.e("second item",itemFromChoice);
        Log.e("second cat",categoryOfChoice);
        Log.e("second catEN",categoryOfChoiceEN);
//        getCardByCategory(categoryOfChoice,itemFromChoice,getApplicationContext());
    }

//    public void getCardByCategory(String category,String item, ListView list, Context context){
//        final String cat = category;
//        final String it = item;
//        final ListView listeView = list;
//        final Context con= context;
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        final String url = "https://omgvamp-hearthstone-v1.p.mashape.com/info?locale="+langue;
//        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url,null,
//                new Response.Listener<JSONObject>()
//                {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // display response
//                        try {
//                            JSONArray tabJSONClasses = response.getJSONArray(cat);
//                            Log.e("STRING",response.toString());
//                            List<String> list = new ArrayList<String>();
//                            int len = tabJSONClasses.length();
//                            for(int i=0;i<len;i++){
//                                list.add(tabJSONClasses.get(i).toString());
//                            }
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(con, android.R.layout.simple_spinner_dropdown_item, list);
//                            spin.setAdapter(adapter);
//                            Log.e("Response", response.toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("Error.Response", error.toString());
//                    }
//                }
//        ) {
//
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<String, String>();
//                headers.put("X-Mashape-Key", "46DIpS18WYmsh5bbTTT6lTfz1lX6p18165XjsnkWMVTW7qBecj");
//                headers.put("Accept", "application/json");
//                return headers;
//            }
//
////            public Map<String, String> getParams() {
////                Map<String, String> params = new HashMap<String, String>();
////                params.put("locale", langue);
////                return params;
////            }
//        };
//
//
//// add it to the RequestQueue
//        queue.add(getRequest);
//    }

}
