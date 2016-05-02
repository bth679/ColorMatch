package com.example.tan.colormatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import java.util.Random;


public class Play extends AppCompatActivity {

    // Declare variables
    private TextView counterText;
    private EditText name;

    /**
     List of the color's codes
     red: "#ff0000"
     blue: "#0000ff"
     yellow: "#ffff00"
     black: #000000"
     orange: #ff9900"
     purple: #660066"
     }*/

    String[] myColors = { "#ff0000", "#0000ff", "#ffff00", "#000000", "#ff9900", "#660066"};
    private String[] spinnerList = { "", "Red", "Blue", "Yellow", "Black", "Orange", "Purple"};


    final Context context = this;

    int counter = 0;
    final Random random = new Random();
    int i = 0;
    int timeChange = 1000;

    String currentCounter = "0";

    Spinner spinner;
    String SpinnerValue;
    String colorSelected = "";
    String currentColor = "";

    // create an instance of the DatabaseHelper class here
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initialize variables
        counterText = (TextView) findViewById(R.id.counter);

        // call the constructor of the DatabaseHelper class
        myDb = new DatabaseHelper(this);
        counterText.setText(currentCounter);

        // Adding listener of the screen
        RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.draw_circle);
        rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the user selects the right color
                if (colorSelected == currentColor) {
                    MediaPlayer player = MediaPlayer.create(Play.this, R.raw.right);
                    player.start();

                    counter += 1;
                    timeReduce(counter, timeChange);
                    currentCounter = "" + counter;
                    counterText.setText(currentCounter);
                }

                // Start the timing system when the user first clicks the button
                if (colorSelected != currentColor) {
                    MediaPlayer player = MediaPlayer.create(Play.this, R.raw.wrong);
                    player.start();

                    // get the alert message & add the data to the DB
                    // First get the alert.xml view
                    LayoutInflater layOut = LayoutInflater.from(context);
                    View alertView = layOut.inflate(R.layout.alert, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // Set alert.xml to alertdialog builder
                    alertDialogBuilder.setView(alertView);

                    name = (EditText) alertView.findViewById(R.id.editTextDialogUserInput);

                    // Set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("SAVE",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // Add code for inserting to DB
                                            AddData(name.getText().toString(), counter);
                                            Intent i = new Intent(Play.this, Play.class);
                                            startActivity(i);
                                        }

                                    })
                            .setNegativeButton("CANCEL",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                            Intent i = new Intent(Play.this, Play.class);
                                            startActivity(i);
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            }
        });

        // Adding listener method on spinner
        spinner = (Spinner) findViewById(R.id.spinnerColor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Play.this, android.R.layout.simple_list_item_1, spinnerList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue = (String) spinner.getSelectedItem();

                switch (SpinnerValue) {
                    case "":
                        break;
                    case "Red":
                        colorSelected = "#ff0000";
                        spinner.setEnabled(false);
                        break;
                    case "Blue":
                        colorSelected = "#0000ff";
                        spinner.setEnabled(false);
                        break;
                    case "Yellow":
                        colorSelected = "#ffff00";
                        spinner.setEnabled(false);
                        break;
                    case "Black":
                        colorSelected = "#000000";
                        spinner.setEnabled(false);
                        break;
                    case "Orange":
                        colorSelected = "#ff9900";
                        spinner.setEnabled(false);
                        break;
                    case "Purple":
                        colorSelected = "#660066";
                        spinner.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
            
        // initialize the circle
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        i = random.nextInt(6 - 1 + 1) + 0;
                        currentColor = myColors[i];
                        Thread.sleep(timeChange);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                circle(currentColor);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    public void AddData (String name, int score){
        boolean isInserted = myDb.insertData(name, score);
        if (isInserted == true)
            Toast.makeText(Play.this, "Player Name has been saved", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Play.this, "Failed to save", Toast.LENGTH_LONG).show();
    }

    public void circle (String color) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(color));
        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        canvas.drawCircle(240, 400, 200, paint);
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.draw_circle);
        ll.setBackgroundDrawable(new BitmapDrawable(bg));
    }

    // Function that reduces the time interval
    public void timeReduce (int score, int i) {
        if (score < 5) {
            i -= 50;
        }
        else if (score < 10) {
            i -= 75;
        }
        else if (score < 15) {
            i = 100;
        }
        else if (score < 25){
            i = 50;
        }
        else if (score < 50){
            i = 20;
        }
        else {
            i = 10;
        }
    }
}