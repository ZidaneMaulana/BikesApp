package com.example.mrafifulh12rpl022018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Registrasi extends AppCompatActivity {

    EditText txtemail;
    EditText txtnamalengkap;
    EditText txtpassword;
    EditText txtcpassword;
    EditText txtnohp;
    EditText txtnoktp;
    EditText txtalamat;
    Button btnregister;
    TextView tvlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        getSupportActionBar().hide();
        txtemail = (EditText)findViewById(R.id.txtemail);
        txtnamalengkap = (EditText)findViewById(R.id.txtnamalengkap);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        txtcpassword = (EditText)findViewById(R.id.txtcpassword);
        txtnohp = (EditText)findViewById(R.id.txtnohp);
        txtalamat = (EditText)findViewById(R.id.txtalamat);
        txtnoktp = (EditText)findViewById(R.id.txtnoktp);
        btnregister = (Button) findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = txtpassword.getText().toString();
                String cpass = txtcpassword.getText().toString();

                if( !pass.equals(cpass) ){
                    Toast.makeText(Registrasi.this, "Password Tidak Sama!!!", Toast.LENGTH_SHORT).show();
                }else{
                    AndroidNetworking.post("http://192.168.6.4/sepeda/register.php")
                            .addBodyParameter("noktp", txtnoktp.getText().toString())
                            .addBodyParameter("email", txtemail.getText().toString())
                            .addBodyParameter("password", txtpassword.getText().toString())
                            .addBodyParameter("nama", txtnamalengkap.getText().toString())
                            .addBodyParameter("nohp", txtnohp.getText().toString())
                            .addBodyParameter("alamat", txtalamat.getText().toString())
                            .addBodyParameter("roleuser", "0")
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // do anything with response
                                    try {
                                        JSONObject hasil = response.getJSONObject("result");
                                        Log.d("RBA", "url: " + hasil.toString());
                                        Boolean respon= hasil.getBoolean("response");
                                        if (respon){
                                            Toast.makeText(Registrasi.this, "Sukses Registrasi", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MainMenu.class));
                                            finish();
                                        }else{
                                            Toast.makeText(Registrasi.this, "Gagal Registrasi", Toast.LENGTH_SHORT).show();
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

            }
        });
        tvlogin = (TextView) findViewById(R.id.tvlogin);

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}