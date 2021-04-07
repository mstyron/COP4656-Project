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
    TextView games1;
    TextView games2;
    TextView games3;
    TextView games4;
    TextView games5;
    TextView avg1;
    TextView avg2;
    TextView avg3;
    TextView avg4;
    TextView avg5;
    Button nextButton;
    Button prevButton;
    Cursor mCursor;

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
        games1 = findViewById(R.id.games1);
        games2 = findViewById(R.id.games2);
        games3 = findViewById(R.id.games3);
        games4 = findViewById(R.id.games4);
        games5 = findViewById(R.id.games5);
        avg1 = findViewById(R.id.avg1);
        avg2 = findViewById(R.id.avg2);
        avg3 = findViewById(R.id.avg3);
        avg4 = findViewById(R.id.avg4);
        avg5 = findViewById(R.id.avg5);

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.previousButton);

        String[] mProjection = new String[] {playerContentProvider.COLUMN_NAME,
                playerContentProvider.COLUMN_GAMES, playerContentProvider.COLUMN_AVG};
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
            games1.setText(String.valueOf(mCursor.getInt(1)));
            avg1.setText(floatToString(mCursor.getFloat(2)));
        }
        else{
            player1.setText("");
            games1.setText("");
            avg1.setText("");
        }
        if(mCursor.moveToNext()){
            player2.setText(mCursor.getString(0));
            games2.setText(String.valueOf(mCursor.getInt(1)));
            avg2.setText(floatToString(mCursor.getFloat(2)));
        }
        else{
            player2.setText("");
            games2.setText("");
            avg2.setText("");
        }
        if(mCursor.moveToNext()){
            player3.setText(mCursor.getString(0));
            games3.setText(String.valueOf(mCursor.getInt(1)));
            avg3.setText(floatToString(mCursor.getFloat(2)));
        }
        else{
            player3.setText("");
            games3.setText("");
            avg3.setText("");
        }
        if(mCursor.moveToNext()){
            player4.setText(mCursor.getString(0));
            games4.setText(String.valueOf(mCursor.getInt(1)));
            avg4.setText(floatToString(mCursor.getFloat(2)));
        }
        else{
            player4.setText("");
            games4.setText("");
            avg4.setText("");
        }
        if(mCursor.moveToNext()){
            player5.setText(mCursor.getString(0));
            games5.setText(String.valueOf(mCursor.getInt(1)));
            avg5.setText(floatToString(mCursor.getFloat(2)));
        }
        else{
            player5.setText("");
            games5.setText("");
            avg5.setText("");
        }
    }

    public String floatToString(float num){
        return String.format("%.3f", num);
    }
}
