package com.example.fantasybaseball;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TeamList extends AppCompatActivity {
    TextView player1;
    TextView player2;
    TextView player3;
    TextView player4;
    TextView player5;
    TextView player6;
    TextView player7;
    TextView player8;
    TextView player9;
    TextView player10;
    EditText playerRemove;
    Button sim;
    Button remove;

    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list);

        player1 = findViewById(R.id.teamPlayer1);
        player2 = findViewById(R.id.teamPlayer2);
        player3 = findViewById(R.id.teamPlayer3);
        player4 = findViewById(R.id.teamPlayer4);
        player5 = findViewById(R.id.teamPlayer5);
        player6 = findViewById(R.id.teamPlayer6);
        player7 = findViewById(R.id.teamPlayer7);
        player8 = findViewById(R.id.teamPlayer8);
        player9 = findViewById(R.id.teamPlayer9);
        player10 = findViewById(R.id.teamPlayer10);
        playerRemove = findViewById(R.id.playerEditText);
        sim = findViewById(R.id.simButton);
        remove = findViewById(R.id.removeButton);

        //finds all players on the users team
        String[] mProjection = new String[] {
                playerContentProvider.COLUMN_POSITION,
                playerContentProvider.COLUMN_NAME};
        String selection = playerContentProvider.COLUMN_UTEAMPLAYER + " = ?";
        String[] selectionArgs = new String[] {"true"};

        mCursor = getContentResolver().query(playerContentProvider.CONTENT_URI,
                mProjection, selection, selectionArgs, null);

        display();

        //Starts the simulation activity
        sim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Simulator.class);
                startActivity(intent);
            }
        });

        //Removes a player from the team
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(playerRemove.getText());
                playerRemove.setText("");
                ContentValues contentValues = new ContentValues();
                contentValues.put(playerContentProvider.COLUMN_UTEAMPLAYER, "false");
                String Selection = playerContentProvider.COLUMN_NAME + " = ?";
                String[] SelectionArgs = new String[] {name};
                int updated = getContentResolver().update(playerContentProvider.CONTENT_URI, contentValues,
                        Selection, SelectionArgs);
                if(updated == 0){
                    Toast.makeText(getApplicationContext(), "Player not found.",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    String[] mProjection = new String[] {
                            playerContentProvider.COLUMN_POSITION,
                            playerContentProvider.COLUMN_NAME};
                    String selection = playerContentProvider.COLUMN_UTEAMPLAYER + " = ?";
                    String[] selectionArgs = new String[] {"true"};

                    mCursor = getContentResolver().query(playerContentProvider.CONTENT_URI,
                            mProjection, selection, selectionArgs, null);
                    clearDisplay();
                    display();
                }
            }
        });
    }
    //Displays each player on the team and their position
    public void display(){
        if(mCursor.moveToNext()) {
            player1.setText(String.format("1.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player2.setText(String.format("2.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player3.setText(String.format("3.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player4.setText(String.format("4.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player5.setText(String.format("5.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player6.setText(String.format("6.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player7.setText(String.format("7.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player8.setText(String.format("8.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player9.setText(String.format("9.  %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }
        if(mCursor.moveToNext()) {
            player10.setText(String.format("10. %s  %s", mCursor.getString(0), mCursor.getString(1)));
        }

    }

    public void clearDisplay(){
        player1.setText("");
        player2.setText("");
        player3.setText("");
        player4.setText("");
        player5.setText("");
        player6.setText("");
        player7.setText("");
        player8.setText("");
        player9.setText("");
        player10.setText("");
    }
}
