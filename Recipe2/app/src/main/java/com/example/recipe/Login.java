package com.example.recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText t1, t2;
    Button b1;
    TextView tt;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1 = (EditText) findViewById(R.id.editText6);
        t2 = (EditText) findViewById(R.id.editText14);
        tt=(TextView)findViewById(R.id.textView6);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1 = (Button) findViewById(R.id.button4);
        b1.setOnClickListener(this);
        tt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == tt) {
            Intent i=new Intent(getApplicationContext(),Registration.class);

            startActivity(i);


        }
        if (view == b1) {

            final String username = t1.getText().toString();
            final String pass = t2.getText().toString();




        if(username.equalsIgnoreCase(""))
        {
            t1.setError("Enter Username");
        }

            if(pass.equalsIgnoreCase(""))
            {
                t2.setError("Enter Password");
            }



            Toast.makeText(Login.this, "" + username, Toast.LENGTH_SHORT).show();

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/login";



            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                String typ=jsonObj.getString("type1");//from pycharm type1
                                    String id = jsonObj.getString("id1");//from pycharm id

//                                    id to sharedpreferences
                                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor e1 = sh.edit();
                                    e1.putString("logid", id);
                                    e1.commit();
                                    Intent ij = new Intent(getApplicationContext(), Home_Page.class);
                                    startActivity(ij);

//                                if(typ.equalsIgnoreCase("user"))
//                                {
//                                    Intent ij=new Intent(getApplicationContext(),Userhome.class);
//                                    startActivity(ij);
//                                }
//                                if(typ.equalsIgnoreCase("worker"))
//                                {
//                                    Intent i2=new Intent(getApplicationContext(),WorkerHome.class);
//                                    startActivity(i2);
//                                }
                                }


                                // }
                                else {
                                    Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
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

                //                value Passing android to python
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("username", username);//passing to python
                    params.put("passwd", pass);//passing to python
//                Toast.makeText(Login.this, ""+username, Toast.LENGTH_SHORT).show();
                    return params;
                }
            };


            int MY_SOCKET_TIMEOUT_MS = 100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);


        }

    }
}
