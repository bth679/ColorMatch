package com.example.tan.colormatch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onClickSetting(View v){
        MediaPlayer player = MediaPlayer.create(Setting.this, R.raw.click);
        player.start();


        if(v.getId() == R.id.back1)
        {
            Intent i = new Intent(Setting.this, MainActivity.class);
            startActivity(i);
        }

        else if(v.getId() == R.id.aboutButton)
        {
            Intent i = new Intent(Setting.this, About.class);
            startActivity(i);
        }
    }
}
