package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    TextView txt;
    SearchView searchbar;
    HashMap<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.textView);

        btn1 = (Button) findViewById(R.id.button3);



        SearchView simpleSearchView = (SearchView) findViewById(R.id.searchView); // inititate a search view
        CharSequence query = simpleSearchView.getQuery(); // get the query string currently in the text field
        simpleSearchView.setQueryHint("Enter word");



        readFromFile();

    }
    public void readFromFile(){


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("recnik.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                String[] parts = mLine.split(":", 2);
                if (parts.length >= 2)
                {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, value);
                } else {
                    System.out.println("ignoring line: " + mLine);
                }
            }
           for (String key : map.keySet())
           {
               System.out.println(key + "  " + map.get(key));
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }
}