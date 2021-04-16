package com.example.fantasybaseball;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddPlayer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);

        String[] Positions = new String[] {
                "C", "1B", "2B", "SS", "3B", "LF", "CF", "RF" };
        String[] Stats = new String[] {"games", "avg", "obp",  "slg", "hr", "sb"};
        Spinner posSpin = (Spinner) findViewById(R.id.positionSpinner);
        ArrayAdapter<String> posAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Positions);

        posAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        posSpin.setAdapter(posAdapter);





    }

}
