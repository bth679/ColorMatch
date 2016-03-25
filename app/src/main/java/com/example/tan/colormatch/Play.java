package com.example.tan.colormatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Play extends AppCompatActivity {

    Timer myTimer;
    MyTimerTask myTimerTask;
    private Button startGame;
    private TextView textTimer;
    int counter = 0;
    int startingMinute;
    int startingSecond;
    int keepingTrack;
    String minute = "";
    String second = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initialize variables
        startGame = (Button) findViewById(R.id.stopButton);
        textTimer = (TextView) findViewById(R.id.textTimer);

        // Set onclick listener
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTimer != null) {
                    myTimer.cancel();
                }

                myTimer = new Timer();
                myTimerTask = new MyTimerTask();
                myTimer.schedule(myTimerTask, 1000, 1000);
            }
        });
    }

        class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                counter += 1;

                //Start timer
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                final String strDate = simpleDateFormat.format(calendar.getTime());

                final String currentMinute = strDate.substring(0, 2);
                final String currentSecond = strDate.substring(3, 5);

                // Start recording the timer
                if (counter == 1) {
                    //startingMinute = Integer.parseInt(currentMinute);
                    //startingSecond = Integer.parseInt(currentSecond);
                    startingMinute = 0;
                    startingSecond = 0;
                }

                // Get the integer of current minute and second
                //int currentMinuteInt = Integer.parseInt(currentMinute);
                //int currentSecondInt = Integer.parseInt(currentSecond);

                // Get the final integer of minute and second
                //int finalMinute = currentMinuteInt - startingMinute;
                //int finalSecond = currentSecondInt - startingSecond;

                //final String finalTimer = "" + finalMinute + ":" + finalSecond;

                startingSecond += 1;
                keepingTrack = startingSecond;
                startingMinute = startingSecond / 60;

                if (keepingTrack >= 60) {
                    keepingTrack = startingSecond - 60 * startingMinute;
                }

                second = "" + keepingTrack;
                if (keepingTrack < 10) {
                    second = "0" + keepingTrack;
                }

                minute = "" + startingMinute;
                if (startingMinute < 10) {
                    minute = "0" + startingMinute;
                }

                final String finalTimer = "" + minute + ":" + second;

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        textTimer.setText(finalTimer);
                    }
                });
            }
        }
}
