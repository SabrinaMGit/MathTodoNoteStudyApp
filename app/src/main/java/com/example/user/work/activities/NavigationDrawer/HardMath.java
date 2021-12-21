package com.example.user.work.activities.NavigationDrawer;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.work.R;
import com.example.user.work.models.BlitzrechnenModels;
import com.example.user.work.models.User;
import com.example.user.work.sql.DatabaseHelper;
import com.example.user.work.utilities.OnTaskCompletedCallback;

import java.util.ArrayList;
import java.util.List;

public class HardMath extends AppCompatActivity {
    TextView countdownText;
    TextView formel;
    int vorherRandomZahl;
    int nachherRandomZahl;
    int MaxImage = 4;
    int Max;
    int Min;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    int ergebnis;
    int z1;
    int z2;
    int z3;
    int ersteErg;
    int buttonNumber;
    int erg;
    int count;
    int ergF;
    int ergF1;
    int ergF2;
    int ergF3;
    int ergF4;
    String operation;
    ProgressBar progressBar;
    Thread thread = null;
    boolean progressIsDone =false;
    DatabaseHelper databaseHelper;
    BlitzrechnenModels blitzrechnenModels;
    int foundUserId;
    List<User> listUsers;
    Blitzrechnen blitzrechnen;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardmath_layout);
        blitzrechnen = new Blitzrechnen();
        databaseHelper = new DatabaseHelper(this);

        listUsers = new ArrayList<>();

        countdownText = findViewById(R.id.countdownText);
        formel = findViewById(R.id.formel);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        progressBar = findViewById(R.id.progress);
        //zahl1.setVisibility(View.INVISIBLE);
        startTheGame();

    }

    public void startTheGame(){
        Max = 30;
        Min = 1;
        do {
            z1 = blitzrechnen.setRandomZahl(Max,Min);
            z2= blitzrechnen.setRandomZahl(Max,Min);
            z3= blitzrechnen.setRandomZahl(Max,Min);
            ersteErg = z1 + z2;
            ergebnis = ersteErg - z3;
            Log.v("TAG","z1 = "+z1);
            Log.v("TAG","z2 = "+z2);
            Log.v("TAG","z3 = "+z3);
            Log.v("TAG","ersteErg = "+ersteErg);
            Log.v("TAG","ergebnis = "+ergebnis);
        }while(ergebnis<=0);
        Log.v("TAG","ENDE z1 = "+z1);
        Log.v("TAG","ENDE z2 = "+z2);
        Log.v("TAG","ENDE z3 = "+z3);
        Log.v("TAG","ENDE ersteErg = "+ersteErg);
        Log.v("TAG","ENDE ergebnis = "+ergebnis);
        formel.setText("( "+z1+" + "+z2+" ) - "+z3+" = "+ergebnis);
        buttonNumber = blitzrechnen.setButtonErgebnis();
        setAllButtonNumbers(buttonNumber,z2,Max,Min);
    }

    public void setAllButtonNumbers(int buttonNumber, int ergebnis, int Max, int Min){
        switch (buttonNumber){
            case 1:
                button1.setText(""+ergebnis);
                for(int buttonZ = 2; buttonZ <= 4; buttonZ++) {
                    Log.v("INFO", "ForSchleife Button Zahl= " + buttonZ);
                 do{
                     ergF = blitzrechnen.setRandomZahl(Max, Min);
                }while (isFalseErgebnisEqual(ergF, ergebnis)==true);
                    setFalseResultsInButton(ergF,buttonZ);
                }
                generateOutputButton(buttonNumber);
                break;
            case 2:
                button2.setText(""+ergebnis);
                for(int buttonZ = 1; buttonZ <= 4; buttonZ++)
                {
                    if (buttonZ!=2) {
                        Log.v("INFO" ,"ForSchleife Button Zahl= "+buttonZ);
                        do{
                            ergF = blitzrechnen.setRandomZahl(Max, Min);
                        }while (isFalseErgebnisEqual(ergF, ergebnis)==true);
                        setFalseResultsInButton(ergF,buttonZ);
                    }
                }
                generateOutputButton(buttonNumber);
                break;
            case 3:
                button3.setText(""+ergebnis);
                for(int buttonZ = 1; buttonZ <= 4; buttonZ++)
                {
                    if (buttonZ!=3) {
                        Log.v("INFO" ,"ForSchleife Button Zahl= "+buttonZ);
                        do{
                            ergF = blitzrechnen.setRandomZahl(Max, Min);
                        }while (isFalseErgebnisEqual(ergF, ergebnis)==true);
                        setFalseResultsInButton(ergF,buttonZ);
                    }
                }
                generateOutputButton(buttonNumber);
                break;
            case 4:
                button4.setText(""+ergebnis);
                for(int buttonZ = 1; buttonZ <= 3; buttonZ++)
                {
                    Log.v("INFO" ,"ForSchleife Button Zahl= "+buttonZ);
                    do{
                        ergF = blitzrechnen.setRandomZahl(Max, Min);
                    }while (isFalseErgebnisEqual(ergF, ergebnis)==true);
                    setFalseResultsInButton(ergF,buttonZ);
                }
                generateOutputButton(buttonNumber);
                break;
            default:
                button1.setText(""+ergebnis);
                break;
        }
    }

    public int generateFalseResults(int ergF, int Max, int Min){
        int rZahl;
        do {
            rZahl = blitzrechnen.setRandomZahl(ergF+10, Min);
            //zahl2 = setNachherRandomZahl(rZahl, ergebnis, Max);
        }while(ergF==rZahl);
        Log.v("INFO", "Random False Zahl Check is not Equal" +rZahl);
        return rZahl;
    }

    public int isEqualWithAnotherResult(int ergF, int ergFvorher){
        while(ergF == ergFvorher) {
            ergF = generateFalseResults(ergF, Max, Min);
            Log.v("RESULT", "ergF check = " + ergF); }
        ergFvorher = ergF;
        Log.v("RESULT", "ergFvorher check = "+ergFvorher);
        return ergF;
    }

    public boolean isFalseErgebnisEqual(int ergF, int ergebnis){
        if(ergF == ergF2 || ergF == ergF3 || ergF == ergF4 || ergF == ergF1 || ergF == ergebnis){
            return true;
        }else
            return false;
    }

    public void setFalseResultsInButton(int ergF, int buttonZahl){

        switch (buttonZahl) {
            case 1:
                ergF1 = ergF;
                button1.setText(""+ergF);
                break;
            case 2:
                ergF2 = ergF;
                button2.setText(""+ergF);
                break;
            case 3:
                ergF3 = ergF;
                button3.setText(""+ergF);
                break;
            case 4:
                ergF4 = ergF;
                button4.setText(""+ergF);
                break;
        }
    }
    public void generateOutputButton(int  buttonNumber){
        switch (buttonNumber){
            case 1:
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startTheGame();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case 2:
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startTheGame();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case 3:
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startTheGame();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case 4:
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startTheGame();
                    }
                });
                break;
        }
    }
}
