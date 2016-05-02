package com.example.tan.colormatch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final Intent svc=new Intent(this, bgmusic.class);

        Switch toggle = (Switch) findViewById(R.id.switch1);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The music is enabled
                } else {
                    // The music is disabled
                    stopService(svc);
                }
            }
        });
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
