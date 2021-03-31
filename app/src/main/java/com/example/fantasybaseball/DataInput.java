package com.example.fantasybaseball;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.*;

public class DataInput {
    public static void inputData(Context context){
        try{
            InputStream stream = context.getResources().openRawResource(R.raw.hitters);
            BufferedReader input = new BufferedReader(new InputStreamReader(stream));
            String line;
            while((line = input.readLine()) != null) {
                String[] player = line.split(", ");
                ContentValues values = new ContentValues();
                values.put(playerContentProvider.COLUMN_NAME, player[0]);
                values.put(playerContentProvider.COLUMN_POSITION, player[1]);
                values.put(playerContentProvider.COLUMN_GAMES, Integer.parseInt(player[2]));
                values.put(playerContentProvider.COLUMN_AVG, Float.parseFloat(player[3]));
                values.put(playerContentProvider.COLUMN_OBP, Float.parseFloat(player[4]));
                values.put(playerContentProvider.COLUMN_SLG, Float.parseFloat(player[5]));
                values.put(playerContentProvider.COLUMN_HR, Integer.parseInt(player[6]));
                values.put(playerContentProvider.COLUMN_SB, Integer.parseInt(player[7]));
                context.getContentResolver().insert(playerContentProvider.CONTENT_URI, values);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
