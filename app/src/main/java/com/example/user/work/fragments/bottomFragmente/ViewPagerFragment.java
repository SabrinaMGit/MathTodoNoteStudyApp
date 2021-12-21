package com.example.user.work.fragments.bottomFragmente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.TextView;


import com.example.user.work.R;
import com.example.user.work.adapter.ViewPagerAdapter;
import com.example.user.work.fragments.topFragmente.topfrag1;


public class ViewPagerFragment extends DialogFragment {
    //public static final String PAGE_TITLE = "PageViewFrag";

    AudioManager mAudio;
    SeekBar alarm;
    SeekBar music;
    SeekBar ring;
    SeekBar system;
    SeekBar voice;
    RatingBar rBar;
    TextView tView;
    Button btn;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //ViewPagerAdapter viewPagerAdapter;
    //FragmentPagerAdapter adapterViewPager;
    private FragmentActivity myContext;
    public ViewPagerFragment() {
        // Required empty public constructor
    }

    public static ViewPagerFragment newInstance() {
        ViewPagerFragment fragment = new ViewPagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viewpager_fragment, container, false);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        //viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        //viewPager.setAdapter(viewPagerAdapter);
        addTabs(viewPager);
        return rootView;
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new topfrag1(), "Informatik");
        adapter.addFrag(new topfrag1(), "Elektrotechnik");
        adapter.addFrag(new topfrag1(), "Englisch");
        adapter.addFrag(new topfrag1(), "Physik");
        adapter.addFrag(new topfrag1(), "Mathe");
        adapter.addFrag(new topfrag1(), "Politik");
        adapter.addFrag(new topfrag1(), "Wirtschaft");
        adapter.addFrag(new topfrag1(), "Beruehmte");
        adapter.addFrag(new topfrag1(), "Spanisch");
        adapter.addFrag(new topfrag1(), "Chemie");
        adapter.addFrag(new topfrag1(), "Biologie");
        viewPager.setAdapter(adapter);
    }

    private void initControls(SeekBar seek, final int stream) {
        seek.setMax(mAudio.getStreamMaxVolume(stream));
        seek.setProgress(mAudio.getStreamVolume(stream));
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                mAudio.setStreamVolume(stream, progress, AudioManager.FLAG_PLAY_SOUND); }
            public void onStartTrackingTouch(SeekBar bar) { }
            public void onStopTrackingTouch(SeekBar bar) { }
        });
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdialog_settings, null);
        builder.setView(view);

        alarm = (SeekBar) view.findViewById(R.id.alarm);
        music = (SeekBar) view.findViewById(R.id.music);
        ring = (SeekBar) view.findViewById(R.id.ring);
        system = (SeekBar) view.findViewById(R.id.system);
        voice = (SeekBar) view.findViewById(R.id.voice);

        /*MobileAds.initialize(getActivity().getApplicationContext(),getString(R.string.admob_app_id));
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.load(adRequest);*/

        rBar = (RatingBar)view.findViewById(R.id.ratingBar1);
        tView = (TextView)view.findViewById(R.id.textview111);
        btn = (Button)view.findViewById(R.id.btnGet);
        /*btn.setOnClickListener(new OnClickListener(){
                int noofstars = rBar.getNumStars();
                float getrating = rBar.getRating();
                tView.setText("Rating: "+getrating+"/"+noofstars);
        });*/


        builder.setTitle("Settings");
        builder.setMessage("System Volume Settings");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO
            }
        });
        mAudio = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        initControls(alarm, AudioManager.STREAM_ALARM);
        initControls(music, AudioManager.STREAM_MUSIC);
        initControls(ring, AudioManager.STREAM_RING);
        initControls(system, AudioManager.STREAM_SYSTEM);
        initControls(voice, AudioManager.STREAM_VOICE_CALL);
        return builder.create();
    }
}
