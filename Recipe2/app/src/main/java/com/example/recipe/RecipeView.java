package com.example.recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecipeView extends AppCompatActivity implements View.OnClickListener {
    ListView l1;
    FloatingActionButton f1;
    EditText e1;
    Button b1;
    SharedPreferences sh;
    String url,hu;
    String[] RecipeName,Description,Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        l1=(ListView)findViewById(R.id.list);
        e1=(EditText)findViewById(R.id.editText4);
        b1=(Button)findViewById(R.id.button7);
        f1=(FloatingActionButton)findViewById(R.id.floatingActionButton2);
        b1.setOnClickListener(this);
        f1.setOnClickListener(this);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        hu = sh.getString("ip", "");
        url = "http://" + hu + ":5000/recipe";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//view service code
                                JSONArray js= jsonObj.getJSONArray("data");//from python
                                RecipeName=new String[js.length()];
                                Description=new String[js.length()];
                                Image=new String[js.length()];

                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    RecipeName[i]=u.getString("RecipeName");//dbcolumn name
                                    Description[i]=u.getString("Description");
                                    Image[i]=u.getString("Image");
                                }

                                l1.setAdapter(new recipe(getApplicationContext(),RecipeName,Description,Image));//custom_view_service.xml and li is the listview object
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }

    @Override
    public void onClick(View v) {
        if(v==f1)
        {
            Intent i = new Intent(getApplicationContext(), add_recipe.class);
            startActivity(i);
        }
        if(v==b1)
        {
            final String recipe=e1.getText().toString();
            sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            hu = sh.getString("ip", "");
            url = "http://" + hu + ":5000/recipe_search";

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//view service code
                                    JSONArray js= jsonObj.getJSONArray("data");//from python
                                    RecipeName=new String[js.length()];
                                    Description=new String[js.length()];
                                    Image=new String[js.length()];

                                    for(int i=0;i<js.length();i++)
                                    {
                                        JSONObject u=js.getJSONObject(i);
                                        RecipeName[i]=u.getString("RecipeName");//dbcolumn name
                                        Description[i]=u.getString("Description");
                                        Image[i]=u.getString("Image");
                                    }

                                    l1.setAdapter(new recipe(getApplicationContext(),RecipeName,Description,Image));//custom_view_service.xml and li is the listview object
                                }


                                // }
                                else {
                                    Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                }

                            }    catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("recipe",recipe);
                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS=100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);



        }
    }
}
