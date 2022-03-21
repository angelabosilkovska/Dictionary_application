package com.example.myapplication;

import static android.icu.lang.UCharacter.toLowerCase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity3 extends AppCompatActivity {

    private Button delete;
    private EditText input;


    public MainActivity3() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        input = (EditText) findViewById(R.id.input);

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteFromFile();
            }
        });
    }


    private void deleteFromFile() {
        Context context = getApplicationContext();
        String wordToDelete = input.getText().toString();
        System.out.println("input "+input);
        HashMap<String, String> map = new HashMap<String, String>();
        String key,value;

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
            map.remove(wordToDelete);
            System.out.println("MAP"+map);
        } catch (IOException e) {
            System.out.println("error pri citanje"+e);
        }

        BufferedWriter bf = null;

        try {
            System.out.println("ovde stigna");
            File file = new File(context.getFilesDir(), "Dictionary.txt");

            bf= new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, String> entry :
                    map.entrySet()) {
                System.out.println("ovde");
                bf.write(entry.getKey() + " "
                        + entry.getValue());

                bf.newLine();
            }

            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {

                bf.close();
            }
            catch (Exception e) {
            }
        }

    }
}