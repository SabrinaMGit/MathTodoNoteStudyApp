package com.example.user.work.fragments.bottomFragmente;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.work.R;
import com.example.user.work.adapter.newsViewAdapter;
import com.example.user.work.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class bottomfrag4 extends Fragment implements View.OnClickListener{

    public static bottomfrag4 newInstance() {
        bottomfrag4 fragment = new bottomfrag4();
        return fragment;
    }

    /*View rootView;
    @SuppressWarnings("FieldCanBeLocal")
    private PieChart chart;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    FloatingActionButton fab;
    LinearLayout ll;
    ImageView image;
    BottomSheetBehavior behavior;
    NestedScrollView nestedView;
    CollapsingToolbarLayout ctl;
    Toolbar tb;
    View rootView;
    private Context mContext;
    List<Quiz> news ;
    //private BlurLayout mSampleLayout, mSampleLayout2, mSampleLayout3, mSampleLayout4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag4, container, false);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        ll = (LinearLayout) rootView.findViewById(R.id.show);
        image = (ImageView) rootView.findViewById(R.id.image);
        nestedView = (NestedScrollView)rootView.findViewById(R.id.nestedScrollView);
        ctl = (CollapsingToolbarLayout)rootView.findViewById(R.id.collapseit);
        tb = (Toolbar)rootView.findViewById(R.id.tb);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);


        fab.setOnClickListener(this);
        image.postDelayed(new Runnable() {
            @Override
            public void run() {
                revealEffectImage();
            }
        }, 1000);

        fab.postDelayed(new Runnable() {
            @Override
            public void run() {
                revealEffectFab();
            }
        }, 1500);


        mCalendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/"+ dayOfMonth ;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                //Intent intent = new Intent(getActivity().getApplicationContext(),MainActivity.class);
                //intent.putExtra("date",date);
                //startActivity(intent);

                //behavior = BottomSheetBehavior.from(nestedView);
                //behavior.setState(BottomSheetBehavior.STATE_EXPANDED);



            }
        });

        news = new ArrayList<>();
        news.add(new Quiz("Informatik","Categorie Book","Description book",R.drawable.informatik));
        news.add(new Quiz("Elektrotechnik","Categorie Book","Description book",R.drawable.arduino));
        news.add(new Quiz("Englisch","Categorie Book","Description book",R.drawable.englisch));
        news.add(new Quiz("Spanisch","Categorie Book","Description book",R.drawable.spain));
        news.add(new Quiz("Mathe","Categorie Book","Description book",R.drawable.mathe));
        news.add(new Quiz("Politik","Categorie Book","Description book",R.drawable.politik));
        news.add(new Quiz("Wirtschaft","Categorie Book","Description book",R.drawable.wirtschaft));
        news.add(new Quiz("Beruehmte Personen","Categorie Book","Description book",R.drawable.napoleon));
        news.add(new Quiz("Sport","Categorie Book","Description book",R.drawable.sport));
        news.add(new Quiz("Chemie","Categorie Book","Description book",R.drawable.chemie));

        RecyclerView myrv = (RecyclerView) rootView.findViewById(R.id.recyclerview_id);
        newsViewAdapter myAdapter = new newsViewAdapter(getActivity(),news);
        myrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrv.setAdapter(myAdapter);




        /*chart = rootView.findViewById(R.id.pieChart1);
        chart.getDescription().setEnabled(false);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");

        chart.setCenterTextTypeface(tf);
        chart.setCenterText(generateCenterText());
        chart.setCenterTextSize(10f);
        chart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        chart.setHoleRadius(45f);
        chart.setTransparentCircleRadius(50f);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);*/


        return rootView;
    }

    /*private SpannableString generateCenterText() {
        /*SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s; }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT > 20) {
                    int cx = image.getRight();
                    int cy = image.getBottom();
                    int finalRadius = (int) Math.hypot(image.getWidth(),
                            image.getHeight());
                    Animator a = ViewAnimationUtils.createCircularReveal(ll, cx,
                            cy, 0, finalRadius);
                    a.setDuration(500);
                    a.setInterpolator(new DecelerateInterpolator());
                    ll.setVisibility(View.VISIBLE);
                    a.start();
                    break;
                }
        }
    }

    void revealEffectImage() {
        if (Build.VERSION.SDK_INT > 20) {
            int cx = (image.getLeft() + image.getRight()) / 2;
            int cy = (image.getTop() + image.getBottom()) / 2;
            int finalRadius = (int) Math.hypot(image.getWidth(),
                    image.getHeight());
            Animator imageReveal = ViewAnimationUtils.createCircularReveal(image, cx,
                    cy, 0, finalRadius);
            imageReveal.setDuration(1000);
            imageReveal.setInterpolator(new DecelerateInterpolator());
            image.setVisibility(View.VISIBLE);
            imageReveal.start();
        }
    }

    @SuppressLint("RestrictedApi")
    void revealEffectFab() {
        if (Build.VERSION.SDK_INT > 20) {
            int cx = fab.getMeasuredWidth() / 2;
            int cy = fab.getMeasuredHeight() / 2;
            int finalRadius = Math.max(fab.getWidth(), fab.getHeight());
            Animator fabReveal = ViewAnimationUtils.createCircularReveal(fab, cx,
                    cy, 0, finalRadius);
            fabReveal.setDuration(1000);
            fabReveal.setInterpolator(new DecelerateInterpolator());
            fab.setVisibility(View.VISIBLE);
            fabReveal.start();
        }
    }
}