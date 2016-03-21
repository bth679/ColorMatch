package com.example.tan.colormatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HighScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
    }

    public void onClickHighScores(View v){

        if(v.getId() == R.id.back1)
        {
            Intent i = new Intent(HighScores.this, MainActivity.class);
            startActivity(i);
        }
    }
}
