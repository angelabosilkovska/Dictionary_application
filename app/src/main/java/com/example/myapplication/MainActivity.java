package com.example.myapplication;

import static android.icu.lang.UCharacter.toLowerCase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Button btnNew;
    private Button btnDelete;

    HashMap<String, String> map = new HashMap<String, String>();
    String key,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readFromFile();

        btn = (Button) findViewById(R.id.search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onclick se povika");
                search();
            }
        });

        btnNew = (Button) findViewById(R.id.OpenNewActivity);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        btnDelete = (Button) findViewById(R.id.OpenNewActivity2);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });

    }
    public void readFromFile(){

            try {

                InputStream inputStream = openFileInput("Dictionary.txt");

                Scanner sc = new Scanner(inputStream);
                System.out.println("Scanner"+sc);
                while (sc.hasNext()) {
                    key = toLowerCase(sc.next());
                    value = sc.nextLine();
                    map.put(key, value);
                    System.out.println("Scanner map"+map);
                }
                System.out.println("readFromFile"+map);
            } catch (IOException e) {
                System.out.println("error pri citanje"+e);
            }
    }


    private void search() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        System.out.println("inputManager"+inputManager);
        EditText search = findViewById(R.id.edittext);
        String key1 = search.getText().toString().trim();
        key1 = toLowerCase(key1);
        String value1 = map.get(key1);
        TextView translation= findViewById(R.id.translation);
        System.out.println("value1"+value1);

        if (value1 == null){
            translation.setText("SORRY, WORD NOT FOUND");
        } else {
            translation.setText(value1.toString());
        }
    }

}