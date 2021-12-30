package com.example.recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IPSet extends AppCompatActivity implements View.OnClickListener {
    EditText t1;
    Button b1;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ipset);
        t1=(EditText) findViewById(R.id.editText5);
        b1=(Button)findViewById(R.id.button3);
        b1.setOnClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        t1.setText(sh.getString("ip",""));
    }

    @Override
    public void onClick(View view) {
        final String ipadrs=t1.getText().toString();
        Toast.makeText(this, ipadrs, Toast.LENGTH_LONG).show();
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        final String url="http://" + ipadrs + ":5000/";
        SharedPreferences.Editor ed=sh.edit();
        ed.putString("ip",ipadrs);
        ed.putString("url",url);
        ed.commit();
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
}
