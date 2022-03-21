package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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
                deleteFromFile(1, " ");
            }
        });
    }


    private void deleteFromFile(int positionOfTerm, String delimiter) {

        String wordToDelete = input.getText().toString();
        System.out.println("input "+input);
        String tempFile = "temp.txt";

        int position = positionOfTerm - 1;
        String currentLine;
        String data[];

        try {
            InputStream inputStream = openFileInput("Dictionary.txt");
            System.out.println("inputStream "+inputStream);
            File oldFile = new File(String.valueOf(inputStream));
            File newFile = new File(tempFile);
            FileWriter fw=new FileWriter(tempFile, true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);

            FileReader fr=new FileReader(String.valueOf(inputStream));
            BufferedReader br=new BufferedReader(fr);

            while((currentLine = br.readLine()) != null)
            {
                data = currentLine.split(" ");
                if(!(data[position].equalsIgnoreCase(wordToDelete))){
                    pw.println(currentLine);
                }
            }

            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();

            oldFile.delete();
            File dump = new File(String.valueOf(inputStream));
            newFile.renameTo(dump);

        } catch (IOException e) {
        }
    }
}