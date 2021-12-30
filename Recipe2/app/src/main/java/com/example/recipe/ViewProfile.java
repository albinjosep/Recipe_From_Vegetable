package com.example.recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewProfile extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    ImageView i;
    SharedPreferences sh;
    String ip,url,lid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView4);
        t3=(TextView)findViewById(R.id.textView9);
        t4=(TextView)findViewById(R.id.textView13);
        t5=(TextView)findViewById(R.id.textView15);
        t6=(TextView)findViewById(R.id.textView17);
        i=(ImageView) findViewById(R.id.imageView2);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip","");
        url="http://"+ip+":5000/profile";
        lid = sh.getString("logid", "");

    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                            JSONObject jj= jsonObj.getJSONObject("data");

                            t1.setText("UserName     :"+jj.getString("username"));
                            t2.setText("Name    :"+jj.getString("name"));
                            t3.setText("Gender   :"+jj.getString("gender"));
                            t4.setText("BornDay    :"+jj.getString("bornday"));
                            t5.setText("Contact      :"+jj.getString("number"));
                            t6.setText("Bio      :"+jj.getString("bio"));

                            String image=jj.getString("image");
                            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            String ip=sh.getString("ip","");
                            String url="http://" + ip + ":5000"+image;
                            Toast.makeText(ViewProfile.this, ""+url, Toast.LENGTH_SHORT).show();
                            Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()). into(i);//circle
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

        //                value Passing android to python
        @Override
        protected Map<String, String> getParams() {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Map<String, String> params = new HashMap<String, String>();

            params.put("login",lid);//passing to python

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


