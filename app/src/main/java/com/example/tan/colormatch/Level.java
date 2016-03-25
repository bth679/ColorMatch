package com.example.tan.colormatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Level extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }

    public void onClickLevel(View v) {
        if(v.getId() == R.id.back1)
        {
            Intent i = new Intent(Level.this, Setting.class);
            startActivity(i);
        }
    }
}
