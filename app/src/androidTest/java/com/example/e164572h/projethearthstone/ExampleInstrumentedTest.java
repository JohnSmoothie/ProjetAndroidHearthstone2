package com.example.e164572h.projethearthstone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.florian.testspinner.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.e164572h.projethearthstone", appContext.getPackageName());
    }

    /**
     * Created by E164385E on 26/03/18.
     */

    public static class FillSpinner {
        private Context cont;

        public FillSpinner(Context context){
            this.cont = context;
        }

        public void fillSpinner(String category, Spinner spinner){
            Request request = new Request(cont);
            JSONArray jArray = request.getInfo(category);

            ArrayList<String> liste = new ArrayList<String>();
            String s = jArray.toString();
            Log.d("DEBUGGER",s);
            Integer [] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(cont, android.R.layout.simple_spinner_dropdown_item, data);
            spinner.setAdapter(adapter);
        }

    }

    public static class MainActivity extends AppCompatActivity {
        static String langue = "frFR";
        static int compteur = 0;
        static String[] arrayItemsEN = {"Death Knight", "Druid", "Hunter", "Mage", "Paladin", "Priest", "Rogue", "Shaman", "Warlock", "Warrior","Dream", "Neutral", "Hero", "Minion", "Spell", "Enchantment", "Weapon", "Hero Power", "Horde", "Alliance", "Neutral", "Demon", "Dragon", "Elemental", "Mech", "Murloc", "Beast", "Pirate", "Totem"};
        static List<String> listEN = new ArrayList<String>(Arrays.asList(arrayItemsEN));

        static List<String> listLANG= new ArrayList<String>();
        static HashMap<String,String> mapENLANG= new HashMap<String,String>();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(com.example.florian.testspinner.R.layout.activity_main);


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
                    Log.e("ITEM",lang);

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


            JsonObjectRequest getRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null,
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
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(con, android.R.layout.simple_spinner_dropdown_item, list);
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

            JsonObjectRequest getRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url,null,
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

    /**
     * Created by E164385E on 26/03/18.
     */

    public static class Request {
        private Context context;
        private RequestQueue queue;
        private JSONArray responseToReturn;

        public Request(Context context){
            this.context = context;
            this.queue = Volley.newRequestQueue(context);
        }
        public JSONArray getInfo(String category){
            final String categ = category;
            final String url = "https://omgvamp-hearthstone-v1.p.mashape.com/info";


            StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
                public void onResponse(String response) {
                    String s = "";
                    try {
                        JSONObject repObj = new JSONObject(response);
                        Log.e("json", repObj.toString());
                        //s += repObj.getString("nom") + "\n";
                        JSONArray adresse = repObj.getJSONArray(categ);
                        System.out.println(adresse);
                        responseToReturn=adresse;

                        //s+= adresse.getString("numero") + " " + adresse.getString("voie") + "\n" + adresse.getString("codePostal") + " " + adresse.getString("commune");
                    } catch (JSONException je) {
                        Log.e("test JSON", je.getMessage());
                    }


                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.getMessage());
                }
            }) {
                /*
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nom", "BOIX");
                    return params;
                }
                */
            };
            queue.add(stringRequest);
            return responseToReturn;
        }
    }

    public static class Secondaire extends AppCompatActivity {
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
}
