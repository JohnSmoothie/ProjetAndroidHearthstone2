package com.example.e164572h.projethearthstone;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {
    static String langue = "frFR";
    static int compteur = 0;
    static String[] arrayItemsEN = {"Death Knight", "Druid", "Hunter", "Mage", "Paladin", "Priest", "Rogue", "Shaman", "Warlock", "Warrior","Dream", "Neutral", "Hero", "Minion", "Spell", "Enchantment", "Weapon", "Hero Power", "Horde", "Alliance", "Neutral", "Demon", "Dragon", "Elemental", "Mech", "Murloc", "Beast", "Pirate", "Totem"};
    static List<String> listEN = new ArrayList<String>(Arrays.asList(arrayItemsEN));

    static List<String> listLANG= new ArrayList<String>();
    static HashMap<String,String> mapENLANG= new HashMap<String,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Spinner spinnerClasse = (Spinner) findViewById(R.id.spinnerClasse);
        final Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        final Spinner spinnerFaction = (Spinner) findViewById(R.id.spinnerFaction);
        final Spinner spinnerRace = (Spinner) findViewById(R.id.spinnerRace);
        final Spinner spinnerLang = (Spinner) findViewById(R.id.spinnerLang);

        ArrayList<Spinner> spinners = new ArrayList<Spinner>();
        spinners.add(spinnerLang);
        spinners.add(spinnerClasse);
        spinners.add(spinnerType);
        spinners.add(spinnerFaction);
        spinners.add(spinnerRace);


        Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_LONG).show();
        //FillSpinner fill =new FillSpinner(this);

        Log.e("listen", String.valueOf(listEN.size()));
        String[] array1 = {"locales","classes","types","factions","races"};
        getInfo(array1,spinners,getApplicationContext());


        Log.e("listen", String.valueOf(listLANG.size()));
//        buildDictEN_LANG();
        Log.e("MAP",mapENLANG.toString());
        this.itemSelected("classes",spinnerClasse);

        this.itemSelected("types",spinnerType);
        this.itemSelected("factions",spinnerFaction);
        this.itemSelected("races",spinnerRace);


        spinnerLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String lang = (String) parentView.getItemAtPosition(position);
                //Log.e("ITEM",lang);

                spinnerLang.setSelection(position);
                if(compteur>0){
                    langue = lang;
                    Log.e("langue",langue);
                    compteur = 0;
                    spinnerClasse.setAdapter(null);
                    spinnerType.setAdapter(null);
                    spinnerFaction.setAdapter(null);

                    spinnerRace.setAdapter(null);
                    //listLANG.clear();
                    ArrayList<Spinner> spinners = new ArrayList<Spinner>();
                    spinners.add(spinnerClasse);
                    spinners.add(spinnerType);
                    spinners.add(spinnerFaction);
                    spinners.add(spinnerRace);
                    String[] array = {"classes","types","factions","races"};
                    getInfo(array,spinners,getApplicationContext());


                }
                compteur++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
    private boolean isSpinnerTouched = false;


    public void itemSelected(String category,Spinner spinner){
        final String categ=category;
        final Spinner spin = spinner;

        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("TOUCH", "ON TOUCH");
                isSpinnerTouched = true;
                compteur++;
                return false;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItem, int position, long id) {
//            Log.e("CHECK", String.valueOf(check));
                Log.e("Compteur", String.valueOf(compteur));
                if (compteur>1){
                    Log.e("Compteur", String.valueOf(compteur));

                    String item = (String) parentView.getItemAtPosition(position);
                    Log.e("item_sélectionné",item);
                    spin.setSelection(position);

                    Intent versSecondaire = new Intent(MainActivity.this,Secondaire.class);
                    versSecondaire.putExtra("item",item);

                    String itemEN = mapENLANG.get(item);
                    Log.e("slectionEnglish",itemEN);
                    versSecondaire.putExtra("ITEM_EN",itemEN);
                    versSecondaire.putExtra("category",categ);

                    Log.e("selection", item);
                    Log.e("slectionEnglish",itemEN);
                    Log.e("categ", categ);
//                Log.e("ITEM_EN", itemEN);
                    startActivity(versSecondaire);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    public void gestionSpinner() {

        //exemple adapter
//        Integer [] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, data);
//        spinnerClasse.setAdapter(adapter);
    }

    public void getInfo(String[] categories,ArrayList<Spinner> spinners,Context context) {
        final String[] cat = categories;
        final ArrayList<Spinner> tabSpin = spinners;
        final Context con = context;

        Log.e("langue getInfo",langue);
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://omgvamp-hearthstone-v1.p.mashape.com/info";


        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
//                        String[] arrayOfFields = {"classes","types","factions","races"};
                        try {
                            String[] arrayOfFields = cat;

                            for(int i=0;i<arrayOfFields.length;i++){
                                Log.e("fields",arrayOfFields[i]);
                                JSONArray tabJSONClasses = response.getJSONArray(arrayOfFields[i]);
                                Log.e("STRING",response.toString());
                                List<String> list = new ArrayList<String>();

                                for(int t=0;t<tabJSONClasses.length();t++){
                                    String elt = tabJSONClasses.get(t).toString();
                                    Log.e("elt1", elt);
                                    list.add(elt);
                                    if(arrayOfFields[i] != "locales"){
                                        listLANG.add(elt);
                                    }

                                }
                                Log.e("LISTadap",list.toString());
                                SpinnerAdapter adapter = new ArrayAdapter<String>(con, android.R.layout.simple_spinner_dropdown_item, list);
                                if(i == 0) {
                                    List<String> listlang = new ArrayList<String>();
                                    listlang.add("frFR");
                                    listlang.add("enGB");
                                    adapter = new adapterLang(con, listlang);
                                    Log.e("sq", listlang.toString());
                                }
                                tabSpin.get(i).setAdapter(adapter);
                            }
                            int lenEN = listLANG.size();
                            Log.e("sizelistEN", String.valueOf(listEN.size()));
                            Log.e("sizelistLANG", String.valueOf(listLANG.size()));
                            Log.e("EN", String.valueOf(listEN.toString()));
                            Log.e("LANG", String.valueOf(listLANG.toString()));
                            for(int i=0;i<lenEN;i++) {
                                String key = listLANG.get(i);
                                String value = listEN.get(i);
                                mapENLANG.put(key, value);
                            }

                        } catch (JSONException e) {
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
//
//            public Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("locale", langue);
//                return params;
//            }
        };
        queue.add(getRequest);
    }


// add it to the RequestQueue





    public void getKeyResultsEnglish(){
//        final String cat = category;
//        final Spinner spin = spinner;
//        final Context con= context;
        Log.e("langue getInfo",langue);
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://omgvamp-hearthstone-v1.p.mashape.com/info?locale=enUS";

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        String[] arrayOfFields = {"classes","types","factions","races"};
                        try {
                            for(String field : arrayOfFields){
                                JSONArray tabJSONClasses = response.getJSONArray(field);
                                Log.e("STRING",response.toString());
                                List<String> list = new ArrayList<String>();
                                int len = tabJSONClasses.length();
                                for(int i=0;i<len;i++){
                                    String elt = tabJSONClasses.get(i).toString();
                                    Log.e("elt1", elt);

                                    listEN.add(elt);
                                }
                                Log.e("Response1", response.toString());
                            }
                            int lenEN = listLANG.size();
                            Log.e("sizelistEN", String.valueOf(listEN.size()));
                            Log.e("sizelistLANG", String.valueOf(listLANG.size()));
                            Log.e("EN", String.valueOf(listEN.toString()));
                            Log.e("LANG", String.valueOf(listLANG.toString()));
                            for(int i=0;i<lenEN;i++) {
                                String key = listLANG.get(i);
                                String value = listEN.get(i);
                                mapENLANG.put(key, value);
                            }
                            Log.e("maptostring", String.valueOf(mapENLANG.toString()));


                        } catch (JSONException e) {
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

    public void buildDictEN_LANG(){
        int lenEN = listEN.size();
        int lenLANG = listLANG.size();
        Log.e("LONG EN", String.valueOf(lenEN));
        Log.e("LONG LANG", String.valueOf(lenLANG));

        for(int i=0;i<lenEN;i++){
            String key = listLANG.get(i);
            String value = listEN.get(i);
            mapENLANG.put(key,value);
        }
    }


}

class adapterLang extends BaseAdapter {

    private Context context;
    private List<String> listlang;


    public adapterLang(Context context, List<String> listlang) {
        this.context = context;
        this.listlang = listlang;
    }

    @Override
    public int getCount() {
        return listlang.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_lang, parent, false);

        String lang = this.listlang.get(position);

        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);

        if(lang == "frFR") {
            image.setImageResource(R.mipmap.fr);
        }

        if(lang == "enGB") {
            image.setImageResource(R.mipmap.en);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.textView);

        textView.setText(lang);

        return convertView;
    }
}