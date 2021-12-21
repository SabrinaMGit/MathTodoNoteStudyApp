package com.example.user.work.activities.NavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.work.R;
import com.example.user.work.activities.Work;
import com.example.user.work.adapter.MatheViewAdapter;
import com.example.user.work.models.MatheGameView;

import java.util.ArrayList;

public class MatheMain extends AppCompatActivity {

    ArrayList<MatheGameView> matheGame;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mathe_drawer);


        RecyclerView userRecyclerView = findViewById(R.id.recyclerview_id);

        matheGame = new ArrayList<>();
        matheGame.add(new MatheGameView(0,"2048", 0, R.drawable.timer_sand, false));
        matheGame.add(new MatheGameView(1,"Blitzrechnen", 0, R.drawable.timer_sand, true));
        matheGame.add(new MatheGameView(2,"Hard Math", 0, R.drawable.flash, true));
        matheGame.add(new MatheGameView(3,"Richtig / Falsch", 0, R.drawable.check, true));
        matheGame.add(new MatheGameView(4,"Trainieren", 0, R.drawable.table, false));
        matheGame.add(new MatheGameView(5,"Multiplikationstabelle", 0, R.drawable.table_large, true));
        matheGame.add(new MatheGameView(6,"Eingabe", 0, R.drawable.ic_input, true));
        matheGame.add(new MatheGameView(7,"Schulte-Tabelle", 0, R.drawable.table_large, false));
        matheGame.add(new MatheGameView(8,"Balance", 0, R.drawable.scale_balance, true));


        MatheViewAdapter myAdapter = new MatheViewAdapter(this,matheGame);
        userRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        userRecyclerView.setAdapter(myAdapter);

        //userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

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
                                Intent intent = new Intent(MatheMain.this, com.example.user.work.activities.NavigationDrawer.ToDoList.class);
                                startActivity(intent);
                                break;
                            case R.id.mathe:
                                Intent mathe = new Intent(MatheMain.this, MatheMain.class);
                                startActivity(mathe);
                                Toast.makeText(MatheMain.this, "Mathe Game :O",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.notizen:
                                Intent note = new Intent(MatheMain.this, NotizenAllView.class);
                                startActivity(note);
                                Toast.makeText(MatheMain.this, "Notizen :)",Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.reatzel:
                                Intent books = new Intent(MatheMain.this, Work.class);
                                startActivity(books);
                                Toast.makeText(MatheMain.this, "Quiz and Books ^^",Toast.LENGTH_SHORT).show();
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
    }

}
