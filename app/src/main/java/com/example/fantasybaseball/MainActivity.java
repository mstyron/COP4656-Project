package com.example.fantasybaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_players);

        String[] Positions = new String[] {
                "Pitcher", "Catcher", "1B", "2B", "SS", "3B", "RF", "MF", "LF" };
        String[] Stats = new String[] {
                "Hits", "HR", "SB"};
        Spinner posSpin = (Spinner) findViewById(R.id.positionSpinner);
        Spinner statSpin = (Spinner) findViewById(R.id.statSpinner);

        ArrayAdapter<String> posAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Positions);
        ArrayAdapter<String> statAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Stats);

        posAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        posSpin.setAdapter(posAdapter);
        statSpin.setAdapter(statAdapter);


    }
}