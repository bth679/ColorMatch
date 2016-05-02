package com.example.tan.colormatch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {

    //MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent svc=new Intent(this, bgmusic.class);
        startService(svc);
        //player = MediaPlayer.create(MainActivity.this, R.raw.ColorMatchMusic);
        //player.start();
    }

    public void onButtonClick(View v){
        MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.click);
        player.start();

        if(v.getId() == R.id.playButton)
        {
            Intent i = new Intent(MainActivity.this, Play.class);
            startActivity(i);
        }

        else if(v.getId() == R.id.highscoresButton)
        {
            Intent i = new Intent(MainActivity.this, HighScores.class);
            startActivity(i);
        }

        else if(v.getId() == R.id.settingButton)
        {
            Intent i = new Intent(MainActivity.this, Setting.class);
            startActivity(i);
        }
    }
}
