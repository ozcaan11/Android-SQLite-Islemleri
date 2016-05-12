package com.ozcaan11.l50.sqlkitapbilgileri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KitapDetay extends AppCompatActivity {
    Button btnDuzenle, btnSil;
    EditText ad, yazar, fiyat, sayfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_detay);

        final int id = Integer.parseInt(getIntent().getStringExtra("id"));

        btnDuzenle = (Button) findViewById(R.id.btnDuzenle);
        btnSil = (Button) findViewById(R.id.btnSil);

        ad = (EditText) findViewById(R.id.dtAd);
        yazar = (EditText) findViewById(R.id.dtYazar);
        fiyat = (EditText) findViewById(R.id.dtFiyat);
        sayfa = (EditText) findViewById(R.id.dtSayfa);
        final Database db = new Database(this);

        Kitap kKitap = db.kitapDetay(id);
        ad.setText(kKitap.getKitap_adi());
        yazar.setText(kKitap.getKitap_yazar());
        sayfa.setText(String.valueOf(kKitap.getKitap_sayfa_sayisi()));
        fiyat.setText(String.valueOf(kKitap.getKitap_fiyat()));

        btnDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kitap kitap = new Kitap(id, ad.getText().toString(), yazar.getText().toString(), Integer.parseInt(sayfa.getText().toString()), Integer.parseInt(fiyat.getText().toString()));
                db.kitapDuzenle(kitap);
                Toast.makeText(KitapDetay.this, "Başarılı bir şekilde düzenlendi!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kitap kitap = new Kitap();
                kitap.setKitap_id(id);
                db.kitapSil(kitap);
                Toast.makeText(KitapDetay.this, "Başarılı bir şekilde silindi!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
