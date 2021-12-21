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
import com.example.user.work.models.User;
import com.example.user.work.models.BlitzrechnenModels;
import com.example.user.work.sql.DatabaseHelper;
import com.example.user.work.utilities.OnTaskCompletedCallback;

import java.util.ArrayList;
import java.util.List;

public class Blitzrechnen extends AppCompatActivity {

    TextView countdownText;
    ImageView imageMultiply;
    TextView zahl1;
    TextView zahl2;
    TextView Ergebnis;
    int ergebnis;
    int vorherRandomZahl;
    int nachherRandomZahl;
    int MaxImage = 4;
    int Max;
    int Min;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ArrayList<BlitzrechnenModels> blitzrechnenDatases;
    int arrayField = -1;
    int z1;
    int z2;
    int erg;
    int count;
    int ergF;
    int ergFvorher = ergebnis;
    String operation;
    ImageView imageEqual;
    ProgressBar progressBar;
    Thread thread = null;
    boolean progressIsDone =false;
    DatabaseHelper databaseHelper;
    BlitzrechnenModels blitzrechnenModels;
    int foundUserId;
    List<User> listUsers;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blitzrechnen_layout);

        databaseHelper = new DatabaseHelper(this);

        blitzrechnenDatases = new ArrayList<>();
        listUsers = new ArrayList<>();

        countdownText = findViewById(R.id.countdownText);
        imageMultiply = findViewById(R.id.imageOperation);
        zahl1 = findViewById(R.id.zahl1);
        zahl2 = findViewById(R.id.zahl2);
        Ergebnis = findViewById(R.id.ergebnis);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        imageEqual = findViewById(R.id.imageEqual);
        progressBar = findViewById(R.id.progress);
        //zahl1.setVisibility(View.INVISIBLE);

        int gzahl = MaxImage + (int)(Math.random() * ((MaxImage - 1) + 1));
        Log.v("TAG", "Image= "+gzahl);
        setAnimatedTextStart(3);

    }

    public void setAnimatedTextStart(final int zahl){
        Log.v("COUNT", "COUNTDOWN SIZE = " +countdownText.getTextSize());
        countdownText.setVisibility(View.VISIBLE);
        countdownText.setText(""+zahl);
        final float startSize = 80; // Size in pixels
        final float endSize = 0;
        final int animationDuration = 1000; // Animation duration in ms

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                countdownText.setTextSize(animatedValue);
                Log.v("COUNT", "COUNTDOWN SIZE = " +countdownText.getTextSize());
               startCountDown(zahl);
            }
        });
        animator.start();
    }

//    private void startProgressBar(boolean isSetProgress) {
//        if (isSetProgress==true) {
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                int progress = 0;
//                while (progress < 500 && (!Thread.currentThread().isInterrupted())) {
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        Thread.currentThread().interrupt();
//                    }
//                    progressBar.setProgress(progress++);
//                }
//                if (!Thread.currentThread().isInterrupted()) {
//                    progressIsDone=true;
//                    Log.v("PROG", "Thread is done = "+progressIsDone);
//                    Blitzrechnen.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            timeIsOver();
//                        }
//                    });
//
//                }
//            }
//        });
//        thread.start();
//    }else if(isSetProgress==false){
//            thread.interrupt();
//            startProgressBar(true);
//        }
//    }
private void startProgressBar(boolean isSetProgress) {

        if (thread != null){
            thread.interrupt();
            thread =null;

        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                int progress = 0;
                while (progress < 500 && (!Thread.currentThread().isInterrupted())) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    progressBar.setProgress(progress++);
                }
                if (!Thread.currentThread().isInterrupted()) {
                    progressIsDone=true;
                    Log.v("PROG", "Thread is done = "+progressIsDone);
                    Blitzrechnen.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timeIsOver();
                        }
                    });

                }
            }
        });
        thread.start();
    }
    public void timeIsOver(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Zeit ist abgelaufen");
        alertDialog.setMessage("Noch einmal spielen?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Neu Starten",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startTheGame(setRandomGrundrechenart());
                    }
                });
        alertDialog.show();
    }
    public void startCountDown(int count){
        //setAnimatedTextStart(count);
        Log.v("DEBUG", "Count"+count);
        if (countdownText.getTextSize()==0) {
            if (count==1) {
                //startProgressBar(true);
                startTheGame(setRandomGrundrechenart());
            }else {
                count--;
                setAnimatedTextStart(count);
            }
        }
    }
    public void startTheGame(int grundrechenart){
        startProgressBar(false);
        ++arrayField;
        imageEqual.setImageResource(R.drawable.equal);
        Log.v("DEBUG", "setTheArrayField++= "+arrayField);
        int buttonNumber;
        switch (grundrechenart) {
            case 1:
                Log.v("INFO", "plus");
                Max = 50;
                Min = 10;
                //set image plus
                imageMultiply.setImageResource(R.drawable.plus);
                //generate first Zahl
                nachherRandomZahl = setRandomZahl(Max, Min);
                nachherRandomZahl = setNachherRandomZahl(nachherRandomZahl, vorherRandomZahl, Max);
                zahl1.setText(""+ nachherRandomZahl);
                vorherRandomZahl = nachherRandomZahl;
                //generate second Zahl
                nachherRandomZahl = setRandomZahl(Max, Min);
                nachherRandomZahl = setNachherRandomZahl(nachherRandomZahl, vorherRandomZahl, Max);
                zahl2.setText("" + nachherRandomZahl);
                ergebnis = vorherRandomZahl + nachherRandomZahl;
                //set the result of the calculator
                Ergebnis.setText("" + ergebnis);
                //set the numbers all in seperate variable to save it
                z1=vorherRandomZahl;
                z2=nachherRandomZahl;
                erg=ergebnis;
                operation = "plus";
                //set the numbers and the result in an Array
                //blitzrechnenDatases.add(arrayField, new blitzrechnenModels(arrayField,z1,z2,erg,"plus",false,0));
                //debug.setText(""+blitzrechnenDatases.get(arrayField).getZahl1());
                Log.v("INFO", "ARRAYLIST= " +blitzrechnenDatases);
                //set the result in a button and remember the button number
                buttonNumber = setButtonErgebnis();
                Log.v("INFO", "buttonNumber= " + buttonNumber);
                setAllButtonNumbers(buttonNumber, ergebnis, Max, Min);
                break;
            case 2:
                Log.v("INFO", "minus");
                //set image minus
                imageMultiply.setImageResource(R.drawable.minus);
                //generate first Zahl
                    nachherRandomZahl = setRandomZahl(50, 30);
                    nachherRandomZahl = setNachherRandomZahl(nachherRandomZahl, vorherRandomZahl, Max);
                    vorherRandomZahl = nachherRandomZahl;
                    //generate second Zahl
                    nachherRandomZahl = setRandomZahl(vorherRandomZahl-1, 1);
                    nachherRandomZahl = setNachherRandomZahl(nachherRandomZahl, vorherRandomZahl, Max);
                    //calculate result
                    ergebnis = vorherRandomZahl - nachherRandomZahl;
                    Log.v("INFO", "Ergebnis= " +ergebnis);
                zahl1.setText("" + vorherRandomZahl);
                zahl2.setText("" + nachherRandomZahl);
                //set the result of the calculator
                Ergebnis.setText("" + ergebnis);
                //
                Max = 30;
                Min = 5;
                //set the result in a button and remember the button number
                buttonNumber = setButtonErgebnis();
                Log.v("INFO", "buttonNumber= " + buttonNumber);
                setAllButtonNumbers(buttonNumber, ergebnis, Max, Min);
                break;
            case 3:
                Log.v("INFO", "mal");
                imageMultiply.setImageResource(R.drawable.circle_small);
                Max = 10;
                Min = 2;
                //set image plus
                //generate first Zahl
                nachherRandomZahl = setRandomZahl(Max, Min);
                nachherRandomZahl = setNachherRandomZahl(nachherRandomZahl, vorherRandomZahl, Max);
                zahl1.setText("" + nachherRandomZahl);
                vorherRandomZahl = nachherRandomZahl;
                //generate second Zahl
                nachherRandomZahl = setRandomZahl(Max, Min);
                nachherRandomZahl = setNachherRandomZahl(nachherRandomZahl, vorherRandomZahl, Max);
                zahl2.setText("" + nachherRandomZahl);
                ergebnis = vorherRandomZahl * nachherRandomZahl;
                //set the result of the calculator
                Ergebnis.setText("" + ergebnis);
                //set the result in a button and remember the button number
                buttonNumber = setButtonErgebnis();
                Log.v("INFO", "buttonNumber= " + buttonNumber);
                Max = 100;
                Min = 5;
                setAllButtonNumbers(buttonNumber, ergebnis, Max, Min);
                break;
            case 4:
                imageMultiply.setImageResource(R.drawable.division);
                Log.v("INFO", "geteilt");
                do {
                    //generate first Zahl
                    nachherRandomZahl = setRandomZahl(50, 30);
                    nachherRandomZahl = setNachherRandomZahl(nachherRandomZahl, vorherRandomZahl, Max);
                    vorherRandomZahl = nachherRandomZahl;
                    //generate second Zahl
                    nachherRandomZahl = setRandomZahl(vorherRandomZahl - 1, 2);
                    nachherRandomZahl = setNachherRandomZahl(nachherRandomZahl, vorherRandomZahl, Max);
                    //calculate result
                    ergebnis = vorherRandomZahl / nachherRandomZahl;
                }while (isTeilbar(vorherRandomZahl,nachherRandomZahl)==false);
                Log.v("INFO", "Ergebnis= " +ergebnis);
                zahl1.setText("" + vorherRandomZahl);
                zahl2.setText("" + nachherRandomZahl);
                //set the result of the calculator
                Ergebnis.setText("" + ergebnis);
                //set the result in a button and remember the button number
                buttonNumber = setButtonErgebnis();
                Log.v("INFO", "buttonNumber= " + buttonNumber);
                int Max = 40;
                int Min = 5;
                setAllButtonNumbers(buttonNumber, ergebnis, Max, Min);
                break;
        }
    }

    public int setRandomZahl(int Max, int Min){
        int zahlR1 = Min + (int)(Math.random() * ((Max - Min) + 1));
        Log.v("INFO" ,"RandomZahl= "+zahlR1+ ", Max= "+Max+ ", Min= " +Min);
        return zahlR1;
    }

    public boolean isZahlGleich(int nachherRandomZahl, int vorherRandomZahl){
        if (nachherRandomZahl == vorherRandomZahl)
            return true;
        else
            return false;
    }
    public boolean isZahlGleichMax(int nachherRandomZahl, int Max){
            if (nachherRandomZahl == Max)
                return true;
            else
                return false;
    }
    public int setNachherRandomZahl(int nachherRandomZahl, int vorherRandomZahl, int Max) {
        if (isZahlGleich(nachherRandomZahl, vorherRandomZahl) == true) {
            int zahl;
            if (isZahlGleichMax(nachherRandomZahl, Max) == true) {
                zahl =nachherRandomZahl-1;
            } else {
                zahl = nachherRandomZahl+1;
            }
            return zahl;
        }
        else{
            Log.v("INFO" ,"setNachherRandomZahl= "+nachherRandomZahl);
            return nachherRandomZahl;
        }
    }
    public int setButtonErgebnis(){
        int zahl = setRandomZahl(4,1);
        return zahl;
    }
    public int setRandomGrundrechenart(){
        int zahl = setRandomZahl(4,1);
        return zahl;
    }

    public boolean isTeilbar(int vorherRandomZahl, int nachherRandomZahl){
        if (vorherRandomZahl%nachherRandomZahl==0) {
            Log.v("INFO", "isTeilbar= TRUE ");
            return true;
        }
        else {
            Log.v("INFO", "isTeilbar= FALSE ");
            return false;
        }
    }

    public void setAllButtonNumbers(int buttonNumber, int ergebnis, int Max, int Min){
        switch (buttonNumber){
            case 1:
                button1.setText(""+ergebnis);
                for(int buttonZ = 2; buttonZ <= 4; buttonZ++)
                {
                    Log.v("INFO" ,"ForSchleife Button Zahl= "+buttonZ);
                    setFalseResultsInButton(ergebnis,buttonZ,Max,Min);
                }
                generateOutputButton(buttonNumber);
                break;
            case 2:
                button2.setText(""+ergebnis);
                for(int buttonZ = 1; buttonZ <= 4; buttonZ++)
                {
                    if (buttonZ!=2) {
                        Log.v("INFO" ,"ForSchleife Button Zahl= "+buttonZ);
                        setFalseResultsInButton(ergebnis,buttonZ,Max,Min);
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
                        setFalseResultsInButton(ergebnis,buttonZ,Max,Min);
                    }
                }
                generateOutputButton(buttonNumber);
                break;
            case 4:
                button4.setText(""+ergebnis);
                for(int buttonZ = 1; buttonZ <= 3; buttonZ++)
                {
                    Log.v("INFO" ,"ForSchleife Button Zahl= "+buttonZ);
                    setFalseResultsInButton(ergebnis,buttonZ,Max,Min);
                }
                generateOutputButton(buttonNumber);
                break;
            default:
                button1.setText(""+ergebnis);
                break;
        }
    }

    public int generateFalseResults(int ergebnis, int Max, int Min){
        int rZahl;
        do {
            rZahl = setRandomZahl(ergebnis+10, Min);
            //zahl2 = setNachherRandomZahl(rZahl, ergebnis, Max);
        }while(ergebnis==rZahl);
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

    public void setFalseResultsInButton(int ergebnis, int buttonZahl,int Max, int Min){

        switch (buttonZahl) {
            case 1:
                ergF =isEqualWithAnotherResult(ergebnis, ergFvorher);
                Log.v("INFO" ,"ergebnis Falsch Ausgabe 1= "+ergF);
                button1.setText(""+ergF);
                break;
            case 2:
                ergF = generateFalseResults(ergebnis, Max, Min);
                ergF =isEqualWithAnotherResult(ergF, ergFvorher);
                Log.v("INFO" ,"ergebnis Falsch Ausgabe 2= "+ergF);
                button2.setText(""+ergF);
                break;
            case 3:
                ergF = generateFalseResults(ergebnis, Max, Min);
                ergF =isEqualWithAnotherResult(ergF, ergFvorher);
                Log.v("INFO" ,"ergebnis Falsch Ausgabe 3= "+ergF);
                button3.setText(""+ergF);
                break;
            case 4:
                ergF = generateFalseResults(ergebnis, Max, Min);
                ergF =isEqualWithAnotherResult(ergF, ergFvorher);
                Log.v("INFO" ,"ergebnis Falsch Ausgabe 4= "+ergF);
                button4.setText(""+ergF);
                break;
        }
    }

    public void setInDatabase(){
        getToDoTaskFromSQLite(new OnTaskCompletedCallback() {
            @Override
            public void onTaskCompleted() {

            }
        });
    }

    public void generateOutputButton(int  buttonNumber){
        switch (buttonNumber){
            case 1:
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 1 True!",Toast.LENGTH_SHORT).show();
                        //blitzrechnenDatases.add(arrayField, new blitzrechnenModels(arrayField,z1,z2,erg,"plus",true,10));
                        //Log.v("DEBUG" ,"ARRAYFIELD= "+arrayField);
                        startTheGame(setRandomGrundrechenart());
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 2 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 3 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 4 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 2:
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 1 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 2 True!",Toast.LENGTH_SHORT).show();
                        startTheGame(setRandomGrundrechenart());
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 3 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 4 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 3:
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 1 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 2 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 3 True!",Toast.LENGTH_SHORT).show();
                        startTheGame(setRandomGrundrechenart());
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 4 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 4:
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 1 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 2 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 3 Wrong!",Toast.LENGTH_SHORT).show();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Blitzrechnen.this, "Button 4 True!",Toast.LENGTH_SHORT).show();
                        startTheGame(setRandomGrundrechenart());
                    }
                });
                break;
        }
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    public void getToDoTaskFromSQLite(final OnTaskCompletedCallback callback) {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            private OnTaskCompletedCallback listener = callback;

            @Override
            protected Void doInBackground(Void... params) {
                blitzrechnenDatases.clear();
                blitzrechnenDatases.addAll(databaseHelper.getAllMatheBlitzrechnen(foundUserId));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //adapter.notifyDataSetChanged();
                if (blitzrechnenDatases != null && !blitzrechnenDatases.isEmpty()) {
                    listener.onTaskCompleted();
                } else {
                    Log.v("ERROR", "Es sind keine User vorhanden!");
                }
            }
        }.execute();
    }
}
