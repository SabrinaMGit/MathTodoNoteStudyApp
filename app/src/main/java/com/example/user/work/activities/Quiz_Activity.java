package com.example.user.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.transition.Slide;

import com.example.user.work.R;

public class Quiz_Activity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
    setContentView(R.layout.list_item_quiz);

        TransitionInflater tf = TransitionInflater.from(this);
        Transition t =
                tf.inflateTransition(R.transition.shared_transition);
        getWindow().setSharedElementExitTransition(t);

    img = (ImageView) findViewById(R.id.image);

    // Recieve data
    Intent intent = getIntent();
    int image = intent.getExtras().getInt("Thumbnail") ;

    // Setting values

        img.setImageResource(image);


    }
}

