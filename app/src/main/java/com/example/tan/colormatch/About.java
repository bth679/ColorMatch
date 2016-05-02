package com.example.tan.colormatch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void onClickAbout(View v) {
        MediaPlayer player = MediaPlayer.create(About.this, R.raw.click);
        player.start();

        if(v.getId() == R.id.back1)
        {
            Intent i = new Intent(About.this, Setting.class);
            startActivity(i);
        }
    }
}
