package com.example.tan.colormatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

public class Play extends AppCompatActivity {

    // Declare variables
    private Button startGame;
    private TextView textTimer, counterText;
    private EditText name, score;
    final Context context = this;

    int counter = 0;

    long minute, second;
    long timeStart, timeEnd, timeTotal;
    String finalTimer = "00:00";
    String currentCounter = "0";

    String minuteString = "";
    String secondString = "";

    // create an instance of the DatabaseHelper class here
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initialize variables
        startGame = (Button) findViewById(R.id.stopButton);
        textTimer = (TextView) findViewById(R.id.textTimer);
        counterText = (TextView) findViewById(R.id.counter);

        // call the constructor of the DatabaseHelper class
        myDb = new DatabaseHelper(this);

        textTimer.setText(finalTimer);
        counterText.setText(currentCounter);

    }

    public void onClickStop(View v) {

        if (v.getId() == R.id.stopButton) {
            counter += 1;
            currentCounter = "" + counter;
            counterText.setText(currentCounter);

            // Start the timing system when the user first clicks the button
            if (counter == 1) {
                timeStart = System.currentTimeMillis();
                finalTimer = "" + timeStart;
            }

            // Stop the time and record it after the user has completed the puzzle
            else if (counter == 10) {
                timeEnd = System.currentTimeMillis();
                timeTotal = (timeEnd - timeStart) / 1000;

                // Check 1st case
                if (timeTotal >= 60)
                    minute = timeTotal / 60;
                    second = timeTotal % 60;
                minuteString = "" + minute;
                secondString = "" + second;

                // Check 2nd case
                if (timeTotal < 60)
                    minuteString = "00";
                    second = timeTotal;

                if (minute < 10)
                    minuteString = "0" + minute;
                if (second < 10)
                    secondString = "0" + second;
                finalTimer = minuteString + ":" + secondString;
                textTimer.setText(finalTimer);

                // get the alert message & add the data to the DB
                // First get the alert.xml view
                LayoutInflater layOut = LayoutInflater.from(context);
                View alertView = layOut.inflate(R.layout.alert, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // Set alert.xml to alertdialog builder
                alertDialogBuilder.setView(alertView);

                name = (EditText) alertView.findViewById(R.id.editTextDialogUserInput);
                //score = (EditText) alertView.findViewById(R.id.bestTimeScore);
                //score.setText(finalTimer);

                // Set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("SAVE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Add code for inserting to DB
                                        AddData(name.getText().toString(), finalTimer);
                                    }

                                })
                        .setNegativeButton("CANCEL",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        }
    }

    public void AddData (String name, String time){
        boolean isInserted = myDb.insertData(name, time);
        if (isInserted == true)
            Toast.makeText(Play.this, "Player Name has been saved", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Play.this, "Failed to save", Toast.LENGTH_LONG).show();
    }
}