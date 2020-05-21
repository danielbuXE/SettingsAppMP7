package com.example.mp2danielfornari;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.android.WinnerActivity.extra.Message";
    public static final String EXTRA_WINNERDIFFERENCE = "com.example.android.WinnerActivity.extra.X";

    private TextView TeamBLabel; //its actually team A label
    private TextView TeamALabel; //its actually team B label

    private TextView ScoreBoardA; //the count for the score in the xml file
    private TextView ScoreBoardB;

    private Button TeamAButton;  //the button in the xml to score
    private Button TeamBButton;

    private int TeamAScore ; //just to calculate not in the xml file
    private int TeamBScore ;

    private String TheWinner;
    private int WinnerDifference;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //here is the main game background set up

        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
        String optionGameback = spf.getString(getString(R.string.main_background), "soccer");

        RelativeLayout layoutBackC = (RelativeLayout) findViewById(R.id.layoutObject);


        Drawable backgroundOption = null;
        if(optionGameback.equals("Soccer")){
            backgroundOption = getResources().getDrawable(R.drawable.sobackground);
        }

        else if(optionGameback.equals("Baseball")){
            backgroundOption = getResources().getDrawable(R.drawable.basebackground);
        }
        else if(optionGameback.equals("Basketball")) {
            backgroundOption = getResources().getDrawable(R.drawable.baskbackground);
        }

        layoutBackC.setBackground(backgroundOption);

        //it ends here



        TeamBLabel = (TextView) findViewById(R.id.TeamBLabel); //actually team A label
        TeamALabel = (TextView) findViewById(R.id.TeamALabel); //actually team B label
        ScoreBoardA = (TextView) findViewById(R.id.ScoreBoardA);
        ScoreBoardB = (TextView) findViewById(R.id.ScoreBoardB);
        TeamAButton = (Button) findViewById(R.id.TeamAbutton);
        TeamBButton = (Button) findViewById(R.id.TeamBbutton);

        //edit text team naming

        final String TeamBName = spf.getString(getString(R.string.text_name), "Team A");
        TeamBLabel.setText(TeamBName);

        final String TeamAName = spf.getString(getString(R.string.text_name2),"Team B");
        TeamALabel.setText(TeamAName);

        TeamAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamAScore++;
                ScoreBoardA.setText(String.valueOf(TeamAScore));
                if(TeamAScore == 5 || TeamBScore == 5) {
                    TheWinner = TeamBName;
                    WinnerDifference = TeamAScore - TeamBScore;
                    openWinnerActivity();
                }
            }
        }) ;

        TeamBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamBScore++;
                ScoreBoardB.setText(String.valueOf(TeamBScore));
                if(TeamAScore == 5 || TeamBScore == 5) {
                    TheWinner = TeamAName;
                    WinnerDifference = TeamBScore - TeamAScore;
                    openWinnerActivity();
                }
            }
        }) ;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Sett_menu:
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openWinnerActivity() {
        Intent intent = new Intent(this, WinnerActivity.class);

        String message = "The Winner is " + TheWinner + " With the difference of " + WinnerDifference + " points";

        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
        finish();
    }


}
