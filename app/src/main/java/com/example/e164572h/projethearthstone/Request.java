package com.example.e164572h.projethearthstone;

/**
 * Created by E164572H on 03/04/18.
 */

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by E164385E on 26/03/18.
 */

public class Request {
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
