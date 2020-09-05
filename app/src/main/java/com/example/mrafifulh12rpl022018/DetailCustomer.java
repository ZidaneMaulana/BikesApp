package com.example.mrafifulh12rpl022018;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailCustomer extends AppCompatActivity {

    TextView txtnama, txtalamat, txtemail, txtnohp, txtnoktp;
    Button objId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);

        String id = getIntent().getStringExtra("id");
        String valueId = id.toString();

        String nama = getIntent().getStringExtra("nama");
        String valueNama = nama.toString();

        String nohp = getIntent().getStringExtra("nohp");
        String valueNohp = nohp.toString();

        String noktp = getIntent().getStringExtra("noktp");
        String valueNoktp = noktp.toString();

        String alamat = getIntent().getStringExtra("alamat");
        String valueAlamat = alamat.toString();

        String email = getIntent().getStringExtra("email");
        String valueEmail = email.toString();


        txtnama = (TextView) findViewById(R.id.nama);
        txtnama.setText(nama);

        txtemail = (TextView) findViewById(R.id.email);
        txtemail.setText(email);

        txtalamat = (TextView) findViewById(R.id.alamat);
        txtalamat.setText(alamat);

        txtnohp = (TextView) findViewById(R.id.nohp);
        txtnohp.setText(nohp);

        txtnoktp = (TextView) findViewById(R.id.noktp);
        txtnoktp.setText(noktp);

    }
}