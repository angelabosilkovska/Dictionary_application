package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    TextView txt;
    SearchView searchbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.button);
        txt = (TextView) findViewById(R.id.textView);
        searchbar = (SearchView) findViewById(R.id.searchView);
    }
}