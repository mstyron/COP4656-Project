package com.example.fantasybaseball;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_players);

        ListView players = findViewById(R.id.player_list);
        ListView positions = findViewById(R.id.pos_list);
        ListView homeruns = findViewById(R.id.hr_list);

        String[] mProjection = new String[] {playerContentProvider.COLUMN_NAME,
                playerContentProvider.COLUMN_POSITION, playerContentProvider.COLUMN_HR};
        Cursor mCursor = getContentResolver().query(playerContentProvider.CONTENT_URI, mProjection,
                null, null, null);

        List<String> playerList = new ArrayList<>();
        List<String> posList = new ArrayList<>();
        List<String> hrList = new ArrayList<>();

        while(mCursor.moveToNext()){
            playerList.add(mCursor.getString(0));
            posList.add(mCursor.getString(1));
            hrList.add(String.valueOf(mCursor.getInt(2)));
        }
        List<Map<String,String>> playersList = new ArrayList<Map<String,String>>();
        HashMap<String,String> map;
        for (String i : playerList){
            map = new HashMap<String, String>();
            map.put("name", i);
            playersList.add(map);
        }

        String[] from = new String[] {playerContentProvider.COLUMN_NAME};
        int[] to = new int[] {android.R.id.text1};
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), playersList, android.R.layout.simple_list_item_2, from, to);
        players.setAdapter(adapter);
    }
}
