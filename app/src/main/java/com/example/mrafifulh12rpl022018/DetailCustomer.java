package com.example.mrafifulh12rpl022018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        final String valueId = id.toString();

        String nama = getIntent().getStringExtra("nama");
        final String valueNama = nama.toString();

        String nohp = getIntent().getStringExtra("nohp");
        final String valueNohp = nohp.toString();

        String noktp = getIntent().getStringExtra("noktp");
        final String valueNoktp = noktp.toString();

        String alamat = getIntent().getStringExtra("alamat");
        final String valueAlamat = alamat.toString();

        String email = getIntent().getStringExtra("email");
        final String valueEmail = email.toString();


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

        objId = (Button) findViewById(R.id.objId);
        objId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailCustomer.this, EditCustomer.class);
                intent.putExtra("id", valueId);
                intent.putExtra("nama", valueNama);
                intent.putExtra("email", valueEmail);
                intent.putExtra("alamat", valueAlamat);
                intent.putExtra("nohp", valueNohp);
                intent.putExtra("noktp", valueNoktp);
                startActivity(intent);
            }
        });

    }
}