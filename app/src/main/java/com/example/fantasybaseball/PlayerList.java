package com.example.fantasybaseball;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerList extends AppCompatActivity {

    TextView player1;
    TextView player2;
    TextView player3;
    TextView player4;
    TextView player5;
    Button nextButton;
    Button prevButton;
    Cursor mCursor;

    int playerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_players);

        /*ListView players = findViewById(R.id.player_list);
        ListView positions = findViewById(R.id.pos_list);
        ListView homeruns = findViewById(R.id.hr_list);*/

        player1 = findViewById(R.id.playerName1);
        player2 = findViewById(R.id.playerName2);
        player3 = findViewById(R.id.playerName3);
        player4 = findViewById(R.id.playerName4);
        player5 = findViewById(R.id.playerName5);

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.previousButton);

        playerCount = 0;

        String[] mProjection = new String[] {playerContentProvider.COLUMN_NAME,
                playerContentProvider.COLUMN_POSITION, playerContentProvider.COLUMN_HR};
        mCursor = getContentResolver().query(playerContentProvider.CONTENT_URI, mProjection,
                null, null, null);
        createList(mCursor);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createList(mCursor);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int loopBack = 10;
                playerCount = playerCount - loopBack;
                for(int i = 0; i < loopBack; i++){
                    mCursor.moveToPrevious();
                }
                createList(mCursor);
            }
        });
        /*List<String> playerList = new ArrayList<>();
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
        players.setAdapter(adapter);*/
    }

    public void createList(Cursor mCursor){
        if(mCursor.moveToNext()){
            player1.setText(mCursor.getString(0));
            playerCount++;
        }
        else{
            player1.setText("");
        }
        if(mCursor.moveToNext()){
            player2.setText(mCursor.getString(0));
            playerCount++;
        }
        else{
            player2.setText("");
        }
        if(mCursor.moveToNext()){
            player3.setText(mCursor.getString(0));
            playerCount++;
        }
        else{
            player3.setText("");
        }
        if(mCursor.moveToNext()){
            player4.setText(mCursor.getString(0));
            playerCount++;
        }
        else{
            player4.setText("");
        }
        if(mCursor.moveToNext()){
            player5.setText(mCursor.getString(0));
            playerCount++;
        }
        else{
            player5.setText("");
        }
    }
}
