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

public class EditCustomer extends AppCompatActivity {

    EditText txtid, txtnama, txtalamat, txtemail, txtnohp, txtnoktp;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);
        getSupportActionBar().hide();

        String id = getIntent().getStringExtra("id");

        String nama = getIntent().getStringExtra("nama");

        String nohp = getIntent().getStringExtra("nohp");

        String noktp = getIntent().getStringExtra("noktp");

        String alamat = getIntent().getStringExtra("alamat");

        String email = getIntent().getStringExtra("email");



        txtid = (EditText) findViewById(R.id.id);
        txtid.setText(id);

        txtnama = (EditText) findViewById(R.id.nama);
        txtnama.setText(nama);

        txtemail = (EditText) findViewById(R.id.email);
        txtemail.setText(email);

        txtalamat = (EditText) findViewById(R.id.alamat);
        txtalamat.setText(alamat);

        txtnohp = (EditText) findViewById(R.id.nohp);
        txtnohp.setText(nohp);

        txtnoktp = (EditText) findViewById(R.id.noktp);
        txtnoktp.setText(noktp);

        btnUpdate = (Button) findViewById(R.id.UpdateData);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText updtNama, updtEmail, updtAlamat, updtNohp, updtNoktp, updtId;

                updtId = (EditText) findViewById(R.id.id);
                updtNama = (EditText) findViewById(R.id.nama);
                updtEmail = (EditText) findViewById(R.id.email);
                updtAlamat = (EditText) findViewById(R.id.alamat);
                updtNohp = (EditText) findViewById(R.id.nohp);
                updtNoktp = (EditText) findViewById(R.id.noktp);

                AndroidNetworking.post("http://192.168.6.4/sepeda/update.php")
                        .addBodyParameter("id", updtId.getText().toString())
                        .addBodyParameter("nama", updtNama.getText().toString())
                        .addBodyParameter("email", updtEmail.getText().toString())
                        .addBodyParameter("alamat", updtAlamat.getText().toString())
                        .addBodyParameter("nohp", updtNohp.getText().toString())
                        .addBodyParameter("noktp", updtNoktp.getText().toString())
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
                                        Toast.makeText(EditCustomer.this, "Update Sukses", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), ListData.class));
                                    }else{
                                        Toast.makeText(EditCustomer.this, "Update Gagal", Toast.LENGTH_SHORT).show();
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