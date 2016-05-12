package com.ozcaan11.l50.sqlkitapbilgileri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KitapEkle extends AppCompatActivity {
    Button btnEkle;
    EditText ad, yazar, fiyat, sayfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_ekle);
        ad = (EditText) findViewById(R.id.etAd);
        yazar = (EditText) findViewById(R.id.etYazar);
        fiyat = (EditText) findViewById(R.id.etFiyat);
        sayfa = (EditText) findViewById(R.id.etSayfa);
        btnEkle = (Button) findViewById(R.id.btnDuzenle);

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext());

                Kitap kitap = new Kitap();
                kitap.setKitap_adi(ad.getText().toString());
                kitap.setKitap_yazar(yazar.getText().toString());
                kitap.setKitap_fiyat(Integer.parseInt(fiyat.getText().toString()));
                kitap.setKitap_sayfa_sayisi(Integer.parseInt(sayfa.getText().toString()));

                db.kitapEkle(kitap);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
