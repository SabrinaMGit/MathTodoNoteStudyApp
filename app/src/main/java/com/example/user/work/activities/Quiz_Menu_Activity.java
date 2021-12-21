package com.example.user.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.ToggleButton;

import com.example.user.work.R;

public class Quiz_Menu_Activity extends AppCompatActivity{


    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;
    private int image;
    private ToggleButton buttonFavorite;
    private ScaleAnimation scaleAnimation;
    private BounceInterpolator bounceInterpolator;
    private NestedScrollView bottomsheetView;
    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtDesc);
        img = (ImageView) findViewById(R.id.bookthumbnail);
        buttonFavorite = (ToggleButton) findViewById(R.id.button_favorite);
        bottomsheetView = (NestedScrollView)findViewById(R.id.bottomsheet);

        behavior = BottomSheetBehavior.from(bottomsheetView);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_COLLAPSED){
                }
                if(newState== BottomSheetBehavior.STATE_EXPANDED){

                }
            }

            @Override
            public void onSlide(@NonNull View buttomSheet, float slideOffset) {

            }
        });

        TransitionInflater tf = TransitionInflater.from(this);
        Transition t =
                tf.inflateTransition(R.transition.shared_transition);
        getWindow().setSharedElementExitTransition(t);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        image = intent.getExtras().getInt("Thumbnail");

        // Setting values

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        img.setImageResource(image);

        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        buttonFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //animation
                compoundButton.startAnimation(scaleAnimation);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }




    public void onClickQuiz(View view)
    {
        Intent intentQuiz = new Intent(Quiz_Menu_Activity.this ,Quiz_Activity.class);

        intentQuiz.putExtra("Thumbnail",image);

        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(Quiz_Menu_Activity.this,img, "shared");
        startActivity(intentQuiz,compat.toBundle());

        //Intent intent = new Intent(Quiz_Menu_Activity.this, Quiz_Activity.class);
        //startActivity(intent);
    }
}
