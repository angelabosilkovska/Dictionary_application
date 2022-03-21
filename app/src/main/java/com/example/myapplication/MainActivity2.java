package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity2 extends AppCompatActivity {

    private Button btnSave;
    private EditText text1;
    private EditText text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text1 = (EditText) findViewById(R.id.input1);
        text2 = (EditText) findViewById(R.id.input2);

        btnSave = (Button) findViewById(R.id.save);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("onclick2 se povika");
                writeToFile(getApplicationContext());
            }
        });

    }


    private void writeToFile(Context context){

//TODO check for duplicates

        System.out.println("getFilesDir()"+getFilesDir().getAbsolutePath() );
        String textToWrite = text1.getText().toString() +" " + text2.getText().toString() + "\n";
        File file = new File(context.getFilesDir(), "Dictionary.txt");
        try {
            FileOutputStream writer = new FileOutputStream(file,true);
            writer.write(textToWrite.getBytes(StandardCharsets.UTF_8));
            writer.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

}
