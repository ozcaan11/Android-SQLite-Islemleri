package com.ozcaan11.l50.sqlkitapbilgileri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btnYeniKitapEkle;
    Spinner spinner;
    TextView txtToplam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnYeniKitapEkle = (Button) findViewById(R.id.btnYeniKitapEkle);

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayList<String> liste = new ArrayList<String>();
        liste.add("Kitap adına göre (A-Z)");
        liste.add("Yazara göre (A-Z)");
        liste.add("Sayfa sayısına göre (AZALAN)");
        liste.add("Fiyata göre (AZALAN)");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, liste);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * 0 - Kitap adına göre (A-Z)
                 * 1 - Yazara göre (A-Z)
                 * 2 - Sayfa sayısına göre (AZALAN)
                 * 3 - Fiyata göre (AZALAN)
                 * */
                long itemId = parent.getItemIdAtPosition(position);
                switch ((int) itemId) {
                    case 0:
                        Yenile(Database.KITAP_ADI + " ASC");
                        break;
                    case 1:
                        Yenile(Database.KITAP_YAZARI + " ASC");
                        break;
                    case 2:
                        Yenile(Database.KITAP_SAYFA_SAYISI + " DESC");
                        break;
                    case 3:
                        Yenile(Database.KITAP_FIYATI + " DESC");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnYeniKitapEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KitapEkle.class));
            }
        });
    }

    public void Yenile(String orderby) {
        Database db = new Database(getApplicationContext());
        listView = (ListView) findViewById(R.id.listView);
        txtToplam = (TextView) findViewById(R.id.txtToplam);
        assert listView != null;
        listView.clearFocus();

        final ArrayList<String> arrayList = new ArrayList<>();
        final List<Kitap> kitapList = db.tumKitaplar(orderby);


        final int[] kitap_idleri = new int[kitapList.size()];

        if (kitapList.size() == 0) {
            txtToplam.setText("Veritabanında hiç kitap yok.");
        } else {
            for (int i = 0; i < kitapList.size(); i++) {
                String herbirkitap = kitapList.get(i).getKitap_adi() + "     ( " + kitapList.get(i).getKitap_yazar().toUpperCase() + " )\n";
                arrayList.add(i, herbirkitap);
                kitap_idleri[i] = kitapList.get(i).getKitap_id();
            }
            txtToplam.setText("Veritabanında toplam " + String.valueOf(kitapList.size()) + " adet kitap var.");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), KitapDetay.class);
                intent.putExtra("id", String.valueOf(kitap_idleri[position]));
                startActivity(intent);
            }
        });
    }
}
