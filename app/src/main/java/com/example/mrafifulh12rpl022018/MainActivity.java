package com.example.mrafifulh12rpl022018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    EditText txtemail;
    EditText txtpassword;
    Button btnlogin;
    TextView tvregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        tvregister = (TextView)findViewById(R.id.tvregister);
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Registrasi.class));
            }
        });
        txtemail = (EditText)findViewById(R.id.txtemail);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post("http://192.168.1.7/bike_booking/login.php")
                        .addBodyParameter("email", txtemail.getText().toString())
                        .addBodyParameter("password", txtpassword.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // do anything with response
                                try {
                                    JSONObject hasil = response.getJSONObject("result");
                                    Log.d("HANS", "url: " + hasil.toString());
                                    Boolean respon= hasil.getBoolean("response");
                                    if (respon){
                                        Toast.makeText(MainActivity.this, "Sukses Login", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainMenu.class));
                                    }else{
                                        Toast.makeText(MainActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                                Log.d("HANS", "onError: " + error.getErrorBody());
                                Log.d("HANS", "onError: " + error.getLocalizedMessage());
                                Log.d("HANS", "onError: " + error.getErrorDetail());
                                Log.d("HANS", "onError: " + error.getResponse());
                                Log.d("HANS", "onError: " + error.getErrorCode());
                            }
                        });
            }
        });
    }
}