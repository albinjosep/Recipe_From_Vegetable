package com.example.recipe;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    EditText t1,t2,t3,t4,t5,t6,bio;
    Button b1;
    RadioButton m,f;
    String gender="male",name,username,pass,confirmpass,dob,phone,bio1;
    ImageView img;
    ProgressDialog pd;
    Bitmap bitmap;
    SharedPreferences sh;
    String apiURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        t1=(EditText) findViewById(R.id.editText);
        t2=(EditText)findViewById(R.id.editText2);
        t3=(EditText)findViewById(R.id.editText7);
        t4=(EditText)findViewById(R.id.editText8);
        t5=(EditText)findViewById(R.id.editText9);
        t6=(EditText)findViewById(R.id.editText10);
        b1=(Button) findViewById(R.id.button);
        m=(RadioButton) findViewById(R.id.radioButton3);
        m.setChecked(true);
        f=(RadioButton) findViewById(R.id.radioButton2);
        bio=(EditText)findViewById(R.id.editText12);
        img= (ImageView) findViewById(R.id.imageView5);
        b1.setOnClickListener(this);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        apiURL = "http://" + ip + ":5000/register";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        catch (Exception e)

        {}

    }

    @Override
    public void onClick(View view) {
        name = t1.getText().toString();
        username=t2.getText().toString();
        dob=t3.getText().toString();
        phone=t4.getText().toString();
        pass=t5.getText().toString();
        confirmpass=t6.getText().toString();
        bio1=bio.getText().toString();

        if(name.equalsIgnoreCase(""))
        {
            t1.setError("Enter Name");
        }

        else if(username.equalsIgnoreCase(""))
        {
            t2.setError("Enter Username");
        }

        else if(dob.equalsIgnoreCase(""))
        {
            t3.setError("Enter Date Of Birth");
        }

        else if(phone.equalsIgnoreCase(""))
        {
            t4.setError("Enter Phone Number");
        }

        else if(pass.equalsIgnoreCase(""))
        {
            t5.setError("Enter Password");
        }

        else if(confirmpass.equalsIgnoreCase(""))
        {
            t6.setError("Confirm Passsword");
        }

        else if(bio1.equalsIgnoreCase(""))
        {
            bio.setError("Enter Bio");
        }

        else if(phone.length()!=10)
        {
            t4.setError("Enter a valid phone number");
        }

        else if(pass.length()<6)
        {
            t5.setError("Password should be atleast 6 characters");
        }



        else {


            uploadBitmap();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                img.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //converting to bitarray
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    private void uploadBitmap() {


        pd=new ProgressDialog(Registration.this);
        pd.setMessage("Uploading....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, apiURL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {

                            pd.dismiss();

                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("status"), Toast.LENGTH_SHORT).show();


                            Intent i =new Intent(getApplicationContext(),Login.class);
                            startActivity(i);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences o=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("name", name);//passing to python
                params.put("username", username);//passing to python
                params.put("phone", phone);
                params.put("dob", dob);
                params.put("bio", bio1);
                params.put("pswd", pass);
                params.put("cpswd", confirmpass);
                params.put("gender", gender);
                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
}
