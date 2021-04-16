package com.example.fantasybaseball;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.SecureRandom;

import androidx.appcompat.app.AppCompatActivity;

public class Simulator extends AppCompatActivity {
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
    Button simButton;
    Cursor mCursor;

    int rand1;
    int rand2;
    int rand3;
    int rand4;
    int rand5;

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
        simButton = findViewById(R.id.simulateButton);

        addButton.setVisibility(View.GONE);
        simButton.setVisibility(View.VISIBLE);

        //Seeding Random number generator and assigning primary random values
        SecureRandom random = new SecureRandom();
        random.setSeed(random.generateSeed(40));
        rand1 = random.nextInt(40) - 20;
        rand2 = random.nextInt(40) - 20;
        rand3 = random.nextInt(40) - 20;
        rand4 = random.nextInt(40) - 20;
        rand5 = random.nextInt(40) - 20;

        String[] mProjection = new String[] {
                playerContentProvider.COLUMN_NAME,
                playerContentProvider.COLUMN_GAMES,
                playerContentProvider.COLUMN_AVG,
                playerContentProvider.COLUMN_OBP,
                playerContentProvider.COLUMN_SLG,
                playerContentProvider.COLUMN_HR,
                playerContentProvider.COLUMN_SB
        };

        //Selects players in database that are on the users team
        String Selection = playerContentProvider.COLUMN_UTEAMPLAYER + " = ?";
        String[] SelectionArgs = new String[] {"true"};

        mCursor = getContentResolver().query(playerContentProvider.CONTENT_URI, mProjection,
                Selection, SelectionArgs, null);

        Simulate();

        //Gives new randomness values for more variation of the simulation, then goes back to the
        //beginning to simulate the stats again
        simButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rand1 = random.nextInt(40) - 20;
                rand2 = random.nextInt(40) - 20;
                rand3 = random.nextInt(40) - 20;
                rand4 = random.nextInt(40) - 20;
                rand5 = random.nextInt(40) - 20;
                mCursor.moveToFirst();
                mCursor.moveToPrevious();
                Simulate();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Simulate();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCursor.moveToFirst();
                mCursor.moveToPrevious();
                Simulate();
            }
        });
    }

    //Passes each value into their respective sim function to simulate their statistics
    public void Simulate(){
        if(mCursor.moveToNext()){
            player1.setText(mCursor.getString(0));
            games1.setText(String.valueOf(simGames(mCursor.getInt(1), rand1)));
            avg1.setText(floatToString(simAverage(mCursor.getFloat(2), rand1)));
            obp1.setText(floatToString(simOPS(mCursor.getFloat(3), rand1)));
            ops1.setText(floatToString(simOPS(mCursor.getFloat(4), rand1)));
            hr1.setText(String.valueOf(simHR(mCursor.getInt(5), rand1)));
            sb1.setText(String.valueOf(simSB(mCursor.getInt(6))));
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
            games2.setText(String.valueOf(simGames(mCursor.getInt(1), rand2)));
            avg2.setText(floatToString(simAverage(mCursor.getFloat(2), rand2)));
            obp2.setText(floatToString(simOPS(mCursor.getFloat(3), rand2)));
            ops2.setText(floatToString(simOPS(mCursor.getFloat(4), rand2)));
            hr2.setText(String.valueOf(simHR(mCursor.getInt(5), rand2)));
            sb2.setText(String.valueOf(simSB(mCursor.getInt(6))));
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
            games3.setText(String.valueOf(simGames(mCursor.getInt(1), rand3)));
            avg3.setText(floatToString(simAverage(mCursor.getFloat(2), rand3)));
            obp3.setText(floatToString(simOPS(mCursor.getFloat(3), rand3)));
            ops3.setText(floatToString(simOPS(mCursor.getFloat(4), rand3)));
            hr3.setText(String.valueOf(simHR(mCursor.getInt(5), rand3)));
            sb3.setText(String.valueOf(simSB(mCursor.getInt(6))));
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
            games4.setText(String.valueOf(simGames(mCursor.getInt(1), rand4)));
            avg4.setText(floatToString(simAverage(mCursor.getFloat(2), rand4)));
            obp4.setText(floatToString(simOPS(mCursor.getFloat(3), rand4)));
            ops4.setText(floatToString(simOPS(mCursor.getFloat(4), rand4)));
            hr4.setText(String.valueOf(simHR(mCursor.getInt(5), rand4)));
            sb4.setText(String.valueOf(simSB(mCursor.getInt(6))));
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
            games5.setText(String.valueOf(simGames(mCursor.getInt(1), rand5)));
            avg5.setText(floatToString(simAverage(mCursor.getFloat(2), rand5)));
            obp5.setText(floatToString(simOPS(mCursor.getFloat(3), rand5)));
            ops5.setText(floatToString(simOPS(mCursor.getFloat(4), rand5)));
            hr5.setText(String.valueOf(simHR(mCursor.getInt(5), rand5)));
            sb5.setText(String.valueOf(simSB(mCursor.getInt(6))));
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

    //Simulates amount of games the player is likely to play with some randomness
    public int simGames(int games, int rand){
        games = (int) Math.round(games * 2.7);
        games = games + rand;
        if (games > 162){
            games = 162;
        }
        else if(games < 20){
            games = 20;
        }
        return games;
    }

    //Simulates their average with some randomness
    public float simAverage(float avg, int rand){
        float newRand = (float) (rand * .001);
        avg = avg + newRand;
        return avg;
    }

    //Simulates both OBP and SLG with some randomness
    public float simOPS(float ops, int rand){
        float newRand = (float) (rand * .002);
        ops = ops + newRand;
        return ops;
    }

    //Simulates HR with some randomness
    public int simHR(int hr, int rand){
        hr = (int) Math.round(hr * 2.7);
        rand = (int) Math.round(rand / 3.5);
        hr = hr + rand;
        if (hr < 1){
            hr = 1;
        }
        return hr;
    }

    //Simulates SB with some randomness
    public int simSB(int sb){
        return (int) Math.round(sb * 2.7);
    }
}
