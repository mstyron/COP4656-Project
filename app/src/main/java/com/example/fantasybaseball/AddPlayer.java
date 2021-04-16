package com.example.fantasybaseball;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddPlayer extends AppCompatActivity {

    Spinner posSpin;
    EditText name;
    EditText games;
    EditText avg;
    EditText obp;
    EditText slg;
    EditText hr;
    EditText sb;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);

        name = findViewById(R.id.name_edit);
        games = findViewById(R.id.games_edit);
        avg = findViewById(R.id.avg_edit);
        obp = findViewById(R.id.obp_edit);
        slg = findViewById(R.id.slg_edit);
        hr = findViewById(R.id.hr_edit);
        sb = findViewById(R.id.sb_edit);
        add = findViewById(R.id.addPlayerButton);

        String[] Positions = new String[] {
                "C", "1B", "2B", "SS", "3B", "LF", "CF", "RF" };
        String[] Stats = new String[] {"games", "avg", "obp",  "slg", "hr", "sb"};
        posSpin = (Spinner) findViewById(R.id.positionSpinner);
        ArrayAdapter<String> posAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Positions);

        posAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        posSpin.setAdapter(posAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(playerContentProvider.COLUMN_NAME, String.valueOf(name.getText()));
                values.put(playerContentProvider.COLUMN_POSITION, String.valueOf(posSpin.getSelectedItem()));
                values.put(playerContentProvider.COLUMN_GAMES, Integer.parseInt(String.valueOf(games.getText())));
                values.put(playerContentProvider.COLUMN_AVG, Float.parseFloat(String.valueOf(avg.getText())));
                values.put(playerContentProvider.COLUMN_OBP, Float.parseFloat(String.valueOf(obp.getText())));
                values.put(playerContentProvider.COLUMN_SLG, Float.parseFloat(String.valueOf(slg.getText())));
                values.put(playerContentProvider.COLUMN_HR, Integer.parseInt(String.valueOf(hr.getText())));
                values.put(playerContentProvider.COLUMN_SB, Integer.parseInt(String.valueOf(sb.getText())));
                values.put(playerContentProvider.COLUMN_UTEAMPLAYER, false);
                getContentResolver().insert(playerContentProvider.CONTENT_URI, values);

                finish();
            }
        });

    }

}
