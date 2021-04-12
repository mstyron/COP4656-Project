package com.example.fantasybaseball;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
    TextView obp1;
    TextView obp2;
    TextView obp3;
    TextView obp4;
    TextView obp5;
    TextView ops1;
    TextView ops2;
    TextView ops3;
    TextView ops4;
    TextView ops5;
    TextView hr1;
    TextView hr2;
    TextView hr3;
    TextView hr4;
    TextView hr5;
    TextView sb1;
    TextView sb2;
    TextView sb3;
    TextView sb4;
    TextView sb5;
    Button nextButton;
    Button prevButton;
    Button addButton;
    Cursor mCursor;

    boolean p1Clicked = false;
    boolean p2Clicked = false;
    boolean p3Clicked = false;
    boolean p4Clicked = false;
    boolean p5Clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_players);

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
        obp1 = findViewById(R.id.obp1);
        obp2 = findViewById(R.id.obp2);
        obp3 = findViewById(R.id.obp3);
        obp4 = findViewById(R.id.obp4);
        obp5 = findViewById(R.id.obp5);
        ops1 = findViewById(R.id.ops1);
        ops2 = findViewById(R.id.ops2);
        ops3 = findViewById(R.id.ops3);
        ops4 = findViewById(R.id.ops4);
        ops5 = findViewById(R.id.ops5);
        hr1 = findViewById(R.id.hr1);
        hr2 = findViewById(R.id.hr2);
        hr3 = findViewById(R.id.hr3);
        hr4 = findViewById(R.id.hr4);
        hr5 = findViewById(R.id.hr5);
        sb1 = findViewById(R.id.sb1);
        sb2 = findViewById(R.id.sb2);
        sb3 = findViewById(R.id.sb3);
        sb4 = findViewById(R.id.sb4);
        sb5 = findViewById(R.id.sb5);

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.previousButton);
        addButton = findViewById(R.id.addButton);

        String[] mProjection = new String[] {
                playerContentProvider.COLUMN_NAME,
                playerContentProvider.COLUMN_GAMES,
                playerContentProvider.COLUMN_AVG,
                playerContentProvider.COLUMN_OBP,
                playerContentProvider.COLUMN_SLG,
                playerContentProvider.COLUMN_HR,
                playerContentProvider.COLUMN_SB
        };
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = "";
                if (p1Clicked){
                    playerName = String.valueOf(player1.getText());
                }
                else if (p2Clicked){
                    playerName = String.valueOf(player2.getText());
                }
                else if (p3Clicked){
                    playerName = String.valueOf(player3.getText());
                }
                else if (p4Clicked){
                    playerName = String.valueOf(player4.getText());
                }
                else if (p5Clicked){
                    playerName = String.valueOf(player5.getText());
                }
                else{
                    Toast.makeText(getApplicationContext(), "No player selected",
                            Toast.LENGTH_LONG).show();
                }
                if (playerName.compareTo("") != 0){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(playerContentProvider.COLUMN_UTEAMPLAYER, true);
                    String Selection = playerContentProvider.COLUMN_NAME + " = ?";
                    String[] SelectionArgs = new String[] {playerName};
                    getContentResolver().update(playerContentProvider.CONTENT_URI, contentValues,
                            Selection, SelectionArgs);
                    Toast.makeText(getApplicationContext(), "Player added to team",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected));
                player2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));

                p1Clicked = true;
                p2Clicked = false;
                p3Clicked = false;
                p4Clicked = false;
                p5Clicked = false;
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected));
                player1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));

                p1Clicked = false;
                p2Clicked = true;
                p3Clicked = false;
                p4Clicked = false;
                p5Clicked = false;
            }
        });
        player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected));
                player2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));

                p1Clicked = false;
                p2Clicked = false;
                p3Clicked = true;
                p4Clicked = false;
                p5Clicked = false;
            }
        });
        player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected));
                player2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));

                p1Clicked = false;
                p2Clicked = false;
                p3Clicked = false;
                p4Clicked = true;
                p5Clicked = false;
            }
        });
        player5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected));
                player2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));
                player1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border));

                p1Clicked = false;
                p2Clicked = false;
                p3Clicked = false;
                p4Clicked = false;
                p5Clicked = true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.exit:
                finish();
                break;
        }
        return true;
    }

    public void createList(Cursor mCursor){
        if(mCursor.moveToNext()){
            player1.setText(mCursor.getString(0));
            games1.setText(String.valueOf(mCursor.getInt(1)));
            avg1.setText(floatToString(mCursor.getFloat(2)));
            obp1.setText(floatToString(mCursor.getFloat(3)));
            ops1.setText(floatToString(mCursor.getFloat(3) + mCursor.getFloat(4)));
            hr1.setText(String.valueOf(mCursor.getInt(5)));
            sb1.setText(String.valueOf(mCursor.getInt(6)));
        }
        else{
            player1.setText("");
            games1.setText("");
            avg1.setText("");
            obp1.setText("");
            ops1.setText("");
            hr1.setText("");
            sb1.setText("");
        }
        if(mCursor.moveToNext()){
            player2.setText(mCursor.getString(0));
            games2.setText(String.valueOf(mCursor.getInt(1)));
            avg2.setText(floatToString(mCursor.getFloat(2)));
            obp2.setText(floatToString(mCursor.getFloat(3)));
            ops2.setText(floatToString(mCursor.getFloat(3) + mCursor.getFloat(4)));
            hr2.setText(String.valueOf(mCursor.getInt(5)));
            sb2.setText(String.valueOf(mCursor.getInt(6)));
        }
        else{
            player2.setText("");
            games2.setText("");
            avg2.setText("");
            obp2.setText("");
            ops2.setText("");
            hr2.setText("");
            sb2.setText("");
        }
        if(mCursor.moveToNext()){
            player3.setText(mCursor.getString(0));
            games3.setText(String.valueOf(mCursor.getInt(1)));
            avg3.setText(floatToString(mCursor.getFloat(2)));
            obp3.setText(floatToString(mCursor.getFloat(3)));
            ops3.setText(floatToString(mCursor.getFloat(3) + mCursor.getFloat(4)));
            hr3.setText(String.valueOf(mCursor.getInt(5)));
            sb3.setText(String.valueOf(mCursor.getInt(6)));
        }
        else{
            player3.setText("");
            games3.setText("");
            avg3.setText("");
            obp3.setText("");
            ops3.setText("");
            hr3.setText("");
            sb3.setText("");
        }
        if(mCursor.moveToNext()){
            player4.setText(mCursor.getString(0));
            games4.setText(String.valueOf(mCursor.getInt(1)));
            avg4.setText(floatToString(mCursor.getFloat(2)));
            obp4.setText(floatToString(mCursor.getFloat(3)));
            ops4.setText(floatToString(mCursor.getFloat(3) + mCursor.getFloat(4)));
            hr4.setText(String.valueOf(mCursor.getInt(5)));
            sb4.setText(String.valueOf(mCursor.getInt(6)));
        }
        else{
            player4.setText("");
            games4.setText("");
            avg4.setText("");
            obp4.setText("");
            ops4.setText("");
            hr4.setText("");
            sb4.setText("");
        }
        if(mCursor.moveToNext()){
            player5.setText(mCursor.getString(0));
            games5.setText(String.valueOf(mCursor.getInt(1)));
            avg5.setText(floatToString(mCursor.getFloat(2)));
            obp5.setText(floatToString(mCursor.getFloat(3)));
            ops5.setText(floatToString(mCursor.getFloat(3) + mCursor.getFloat(4)));
            hr5.setText(String.valueOf(mCursor.getInt(5)));
            sb5.setText(String.valueOf(mCursor.getInt(6)));
        }
        else{
            player5.setText("");
            games5.setText("");
            avg5.setText("");
            obp5.setText("");
            ops5.setText("");
            hr5.setText("");
            sb5.setText("");
        }
    }

    public String floatToString(float num){
        return String.format("%.3f", num);
    }
}
