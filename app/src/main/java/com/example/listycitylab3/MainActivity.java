package com.example.listycitylab3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> dataList;
    //private ListView cityList;  Converted to local variable in OnItemClick listener
    private ArrayAdapter<String> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Moscow",
                "Sydney", "Berlin", "Vienna",
                "Tokyo", "Beijing", "Osaka", "New Delhi"
        };

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        ListView cityList = findViewById(R.id.city_list);
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //cityList onclick listener
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterview, View view, int position, long id) {
                // position is index of clicked item

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Edit city");

                EditText cityText = new EditText(MainActivity.this);
                cityText.setHint("City");
                builder.setView(cityText);

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String cityName = cityText.getText().toString();
                        dataList.set(position, cityName);
                        cityAdapter.notifyDataSetChanged();

                    }
                });

                builder.show();

            }
        });

        // Button onclick listener
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add city");

                EditText cityText = new EditText(MainActivity.this);
                cityText.setHint("City");
                builder.setView(cityText);

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String cityName = cityText.getText().toString();
                        cityAdapter.add(cityName);

                    }
                });

                builder.show();

            }
        });

    }
}