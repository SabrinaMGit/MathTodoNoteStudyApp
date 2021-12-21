package com.example.user.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.user.work.R;
import com.example.user.work.activities.NavigationDrawer.MatheMain;
import com.example.user.work.activities.NavigationDrawer.NotizenAllView;
import com.example.user.work.activities.NavigationDrawer.ToDoList;
import com.example.user.work.fragments.bottomFragmente.ViewPagerFragment;
import com.example.user.work.fragments.bottomFragmente.bottomfrag2;
import com.example.user.work.fragments.bottomFragmente.bottomfrag3;
import com.example.user.work.fragments.bottomFragmente.bottomfrag4;

public class Work extends AppCompatActivity
         {

Toolbar toolbar;
             private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //getSupportActionBar().hide();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //above//implements NavigationView.OnNavigationItemSelectedListener


        BottomNavigationView mBottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);




        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ViewPagerFragment.newInstance());
        transaction.commit();


        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mBottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawer.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        //Fragment selFragment = null;
                        switch (menuItem.getItemId()) {
                            case R.id.todolist:
                                Intent intent = new Intent(Work.this, ToDoList.class);
                                startActivity(intent);
                                break;
                            case R.id.mathe:
                                Intent mathe = new Intent(Work.this, MatheMain.class);
                                startActivity(mathe);
                                Toast.makeText(Work.this, "Mathe Game",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.notizen:
                                Intent note = new Intent(Work.this, NotizenAllView.class);
                                startActivity(note);
                                Toast.makeText(Work.this, "Notizen :)",Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.reatzel:
                                Intent books = new Intent(Work.this, Work.class);
                                startActivity(books);
                                Toast.makeText(Work.this, "Quiz and Books ^^",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_share:
                                Intent share = new Intent();
                                share.setAction(share.ACTION_SEND);
                                share.putExtra(Intent.EXTRA_SUBJECT, "Do You Know It?");
                                share.putExtra(share.EXTRA_TEXT, "Wow it is so much interesting, I can't believe what here is written");
                                share.setType("text/plain");
                                startActivity(share);
                                break;
                            case R.id.nav_send:
                                //selFragment = bottomfrag4.newInstance();
                                break;
                        }
                        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selFragment);
                        transaction.commit();*/
                        return true;
                    }
                });

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);



        Slide s = new Slide();
        s.setDuration(1000);
        getWindow().setEnterTransition(s);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.action_item1:
                    selectedFragment = ViewPagerFragment.newInstance();
                    break;
                case R.id.action_item2:
                    selectedFragment = bottomfrag2.newInstance();
                    break;
                case R.id.action_item3:
                    selectedFragment = bottomfrag3.newInstance();
                    break;
                case R.id.action_item4:
                    selectedFragment = bottomfrag4.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };

             public void onButtonClick(View view)
             {
                 ViewPagerFragment t = new ViewPagerFragment();
                 t.show(getSupportFragmentManager(),"my dialog");
             }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.work, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/


}
