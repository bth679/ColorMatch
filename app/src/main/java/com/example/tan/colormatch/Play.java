package com.example.tan.colormatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

public class Play extends AppCompatActivity {

    // Declare variables
    private Button startGame;
    private TextView textTimer, counterText;
    private EditText name;
    final Context context = this;

    int counter = 0;

    String currentCounter = "0";

    // create an instance of the DatabaseHelper class here
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initialize variables
        startGame = (Button) findViewById(R.id.stopButton);
        counterText = (TextView) findViewById(R.id.counter);

        // call the constructor of the DatabaseHelper class
        myDb = new DatabaseHelper(this);

        counterText.setText(currentCounter);

        // initialize the circle
        circle("#CD5C5C");
    }

    public void red(View view) {
        String cir = "#ff0000";
        View lay = findViewById(R.id.draw_circle);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(cir));
        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        canvas.drawCircle(240, 400, 200, paint);
        lay.setBackgroundColor(Color.parseColor(cir));
    }
    public void blue(View view) {
        circle("#0000ff");
    }
    public void yellow(View view) {
        circle("#ffff00");
    }
    public void black(View view) {
        circle("#000000");
    }
    public void orange(View view) {
        circle("#ff9900");
    }
    public void purple(View view) {
        circle("#660066");
    }
    public void onClickStop(View v) {

        if (v.getId() == R.id.stopButton) {
            counter += 1;
            currentCounter = "" + counter;
            counterText.setText(currentCounter);


            // Start the timing system when the user first clicks the button
            if (counter == 8) {
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
}