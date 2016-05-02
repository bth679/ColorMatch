package com.example.tan.colormatch;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HighScores extends AppCompatActivity {

    // create an instance of the DatabaseHelper class here
    DatabaseHelper myDb;

    // declare the variables needed to manipulate the widgets
    private String player_name, best_time;
    private TextView name, time;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        // call the constructor of the DatabaseHelper class
        myDb = new DatabaseHelper(this);

        // call the method needed to manage the DB
        viewAll();
    }

    public void onClickHighScores(View v){
        MediaPlayer player = MediaPlayer.create(HighScores.this, R.raw.click);
        player.start();


        if(v.getId() == R.id.back1)
        {
            Intent i = new Intent(HighScores.this, MainActivity.class);
            startActivity(i);
        }
    }

    // this method return data from the table
    public void viewAll() {
        Cursor res = myDb.getAllData();
        while (res.moveToNext()) {
            player_name = res.getString(0);
            best_time = res.getString(1);

            switch (count) {
                case 1:
                    name = (TextView) findViewById(R.id.name1);
                    time = (TextView) findViewById(R.id.score1);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 2:
                    name = (TextView) findViewById(R.id.name2);
                    time = (TextView) findViewById(R.id.score2);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 3:
                    name = (TextView) findViewById(R.id.name3);
                    time = (TextView) findViewById(R.id.score3);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 4:
                    name = (TextView) findViewById(R.id.name4);
                    time = (TextView) findViewById(R.id.score4);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 5:
                    name = (TextView) findViewById(R.id.name5);
                    time = (TextView) findViewById(R.id.score5);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 6:
                    name = (TextView) findViewById(R.id.name6);
                    time = (TextView) findViewById(R.id.score6);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 7:
                    name = (TextView) findViewById(R.id.name7);
                    time = (TextView) findViewById(R.id.score7);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 8:
                    name = (TextView) findViewById(R.id.name8);
                    time = (TextView) findViewById(R.id.score8);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 9:
                    name = (TextView) findViewById(R.id.name9);
                    time = (TextView) findViewById(R.id.score9);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;

                case 10:
                    name = (TextView) findViewById(R.id.name10);
                    time = (TextView) findViewById(R.id.score10);
                    name.setText(player_name);
                    time.setText(best_time);
                    break;
            }

            // Only need first 10 highest scores
            if (count >= 10)
            {
                break;
            }

            // Increment the count
            else {
                count += 1;
            }
        }
    }
}
