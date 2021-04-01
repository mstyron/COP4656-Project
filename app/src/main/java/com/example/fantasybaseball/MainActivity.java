package com.example.fantasybaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataInput.inputData(this);

        String[] Positions = new String[] {
                "Pitcher", "Catcher", "1B", "2B", "SS", "3B", "RF", "CF", "LF" };
        String[] Stats = new String[] {"Games", "AVG", "OBP",  "SLG", "HR", "SB"};
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

        Button submit = findViewById(R.id.button1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayerList.class);
                startActivity(intent);
            }
        });


    }
}