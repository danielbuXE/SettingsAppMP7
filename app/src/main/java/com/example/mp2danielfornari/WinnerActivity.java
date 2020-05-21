package com.example.mp2danielfornari;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {
    private TextView ScoreLabel;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);


        SharedPreferences spf2 = PreferenceManager.getDefaultSharedPreferences(this);
        String optionWinnerback = spf2.getString(getString(R.string.winner_background),"fireworks");

        ConstraintLayout layoutBackW = (ConstraintLayout) findViewById(R.id.layoutObject2);

        Drawable backgroundOptionWinner = null;

        Log.i(" optionWinnerback = ", optionWinnerback);
        if(optionWinnerback.equals("fireworks")){
            backgroundOptionWinner = getResources().getDrawable(R.drawable.fireworks);
        }

        else if(optionWinnerback.equals("medal")){
            backgroundOptionWinner = getResources().getDrawable(R.drawable.medalbackground);
        }
        else if(optionWinnerback.equals("cup")) {
            backgroundOptionWinner = getResources().getDrawable(R.drawable.trophybackground);
        }
        else if(optionWinnerback.equals("thumbs up")){
            backgroundOptionWinner = getResources().getDrawable(R.drawable.thumbsupbackground);
        }

        layoutBackW.setBackground(backgroundOptionWinner);



        ScoreLabel = (TextView) findViewById(R.id.ScoreLabel);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        ScoreLabel.setText(message);


    }
}
