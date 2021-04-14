package com.example.fantasybaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
        String[] Stats = new String[] {"games", "avg", "obp",  "slg", "hr", "sb"};
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





        Button submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String position=posSpin.getSelectedItem().toString();
                String stats=statSpin.getSelectedItem().toString();

                Intent intent = new Intent(getApplicationContext(), PlayerList.class);
                Bundle bundle=new Bundle();
                bundle.putString("position",position);
                bundle.putString("stat",stats);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button view = findViewById(R.id.viewTeamButton);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamList.class);
                startActivity(intent);
            }
        });

    }



}