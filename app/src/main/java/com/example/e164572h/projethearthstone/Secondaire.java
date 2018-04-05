package com.example.e164572h.projethearthstone;

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
import com.android.volley.toolbox.JsonArrayRequest;
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
    static String categoryEnglish;
    static String itemSelectedEnglish;
    static String itemForeignLang;
    static String lang;
    static HashMap<String,String> mapId_Name = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondaire);

//        final

        String itemFromChoice = getIntent().getStringExtra("item");
        String categoryOfChoice = getIntent().getStringExtra("category");
        String categoryOfChoiceEN = getIntent().getStringExtra("ITEM_EN");
        String language = getIntent().getStringExtra("LANG");
        categoryEnglish = categoryOfChoice;
        itemSelectedEnglish = categoryOfChoiceEN;
        lang = language;
        itemForeignLang = itemFromChoice;

        Log.e("second item",itemFromChoice);
        Log.e("second cat",categoryOfChoice);
        Log.e("second catEN",categoryOfChoiceEN);
        final ListView listview = (ListView) findViewById(R.id.list);

        fillListView(listview,this);
        itemSelected(listview);
//        "https://omgvamp-hearthstone-v1.p.mashape.com/cards/"+categoryOfChoiceEn+"/{type}"
//        getCardByCategory(categoryOfChoice,itemFromChoice,getApplicationContext());

    }


    public void fillListView(ListView list, Context context){
        final ListView listeView = list;
        final Context con= context;

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/"+categoryEnglish+"/"+itemSelectedEnglish+"?locale="+lang;
        Log.e("URL",url);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        try {
                            Log.e("tostring",response.toString());
                            ArrayList<String> listOfItems = new ArrayList<String>();
                            for(int i=0; i<response.length();i++){
                                JSONObject obj = response.getJSONObject(i);
                                String id = obj.getString("cardId");
                                String name = obj.getString("name");
                                listOfItems.add(name);
                                mapId_Name.put(name,id);
                            }
//                            Log.e("STRING",response.toString());
//                            List<String> list = new ArrayList<String>();
//                            int len = tabJSONClasses.length();
//                            for(int i=0;i<len;i++){
//                                list.add(tabJSONClasses.get(i).toString());
//                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(con, android.R.layout.simple_list_item_1, listOfItems);
                            listeView.setAdapter(adapter);
//                            Log.e("Response", response.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("X-Mashape-Key", "46DIpS18WYmsh5bbTTT6lTfz1lX6p18165XjsnkWMVTW7qBecj");
                headers.put("Accept", "application/json");
                return headers;
            }

//            public Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("locale", langue);
//                return params;
//            }
        };


// add it to the RequestQueue
        queue.add(getRequest);
    }


    public void itemSelected(ListView listView){

        final ListView listeofItems = listView;
//
//        spinner.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("TOUCH", "ON TOUCH");
//                isSpinnerTouched = true;
//                compteur++;
//                return false;
//            }
//        });
        listeofItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentView, View selectedItem, int position, long id) {
//            Log.e("CHECK", String.valueOf(check));
//                Log.e("Compteur", String.valueOf(compteur));
//                if (compteur>1){
//                    Log.e("Compteur", String.valueOf(compteur));

                String itemSelected = (String) parentView.getItemAtPosition(position);
                listeofItems.setSelection(position);

                Intent versTertiaire = new Intent(Secondaire.this,Tertiaire.class);
                String id_cardSelected = mapId_Name.get(itemSelected);
                versTertiaire.putExtra("id_card",id_cardSelected);
                versTertiaire.putExtra("lang",lang);


//                    Log.e("selection", item);
//                    Log.e("slectionEnglish",itemEN);
//                    Log.e("categ", categ);
//                Log.e("ITEM_EN", itemEN);
                startActivity(versTertiaire);
//                }
            }
        });
    }



}