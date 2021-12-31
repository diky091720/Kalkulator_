package com.example.kalkulatords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView hasil;
    EditText num1, num2;
    RadioButton tambah, kurang, kali, bagi;
    Button hitung;
    CheckBox check;

    int angka1, angka2;
    float total;

    ArrayList<String> arrayList;
    ArrayAdapter<String> mHistory;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<String>();
        mHistory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(mHistory);
        hasil = (TextView) findViewById(R.id.hasil);
        hitung = (Button) findViewById(R.id.hitung);

        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);

        tambah = (RadioButton) findViewById(R.id.tambah);
        kurang = (RadioButton) findViewById(R.id.kurang);
        kali = (RadioButton) findViewById(R.id.kali);
        bagi = (RadioButton) findViewById(R.id.bagi);

        check = (CheckBox) findViewById(R.id.Check);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                angka1 = Integer.parseInt(num1.getText().toString());
                angka2 = Integer.parseInt(num2.getText().toString());

                if (check.isChecked()){
                    if (tambah.isChecked()) {
                        total = angka1 + angka2;
                        hasil.setText(String.valueOf(total));
                        mHistory.add(angka1 + " + " + angka2 +" = "+ total);
                    } else if (kurang.isChecked()) {
                        total = angka1 - angka2;
                        hasil.setText(String.valueOf(total));
                        mHistory.add(angka1 + " - " + angka2 +" = "+ total);
                    } else if (kali.isChecked()) {
                        total = angka1 * angka2;
                        hasil.setText(String.valueOf(total));
                        mHistory.add(angka1 + " * " + angka2 +" = "+ total);
                    } else if (bagi.isChecked()) {
                        total = angka1 / angka2;
                        hasil.setText(String.valueOf(total));
                        mHistory.add(angka1 + " / " + angka2 +" = "+ total);
                    }
                }
                else {
                    if (tambah.isChecked()) {
                        total = angka1 + angka2;
                        hasil.setText(String.valueOf(total));

                    } else if (kurang.isChecked()) {
                        total = angka1 - angka2;
                        hasil.setText(String.valueOf(total));

                    } else if (kali.isChecked()) {
                        total = angka1 * angka2;
                        hasil.setText(String.valueOf(total));

                    } else if (bagi.isChecked()) {
                        total = angka1 / angka2;
                        hasil.setText(String.valueOf(total));
                    }
                }

                sharedPreferences = getSharedPreferences("angka1", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("angka1", String.valueOf(num1));
                editor.putString("angka2", String.valueOf(num2));
                editor.commit();
                Toast.makeText(MainActivity.this, "Tersimpan", Toast.LENGTH_SHORT).show();
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int angka = i;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Hapus Riwayat")
                        .setMessage("Mau Menghapus Riwayat?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrayList.remove(angka);
                                mHistory.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
                return true;
            }
        });
    }
}