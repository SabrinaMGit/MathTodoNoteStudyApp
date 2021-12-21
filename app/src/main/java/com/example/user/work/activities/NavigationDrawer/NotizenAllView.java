package com.example.user.work.activities.NavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.work.data.NotizenData;
import com.example.user.work.R;
import com.example.user.work.activities.Work;
import com.example.user.work.adapter.NotizenViewAdapter;
import com.example.user.work.models.Notizen;

import java.util.ArrayList;
import java.util.List;

public class NotizenAllView extends AppCompatActivity {


    private DrawerLayout drawer;
    private FloatingActionButton fab;
    NotizenData notizenData;
    List<Notizen> notizens;
    Boolean isNewEntry = false;
    String notiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notizenallview_drawer);




        RecyclerView userRecyclerView = findViewById(R.id.recyclerview_id);


        notizens = new ArrayList<>();
        notizens.add(new Notizen(0, "2048", "hey", false));
        notizens.add(new Notizen(1, "Blitzrechnen", "h", true));
        notizens.add(new Notizen(2, "Hard Math", "h", true));
        notizens.add(new Notizen(3, "Richtig / Falsch", "h", true));
        notizens.add(new Notizen(4, "Trainieren", "h", false));
        notizens.add(new Notizen(5, "Multiplikationstabelle", "h", true));
        notizens.add(new Notizen(6, "Eingabe", "h", true));
        notizens.add(new Notizen(7, "Schulte-Tabelle", "h", false));
        notizens.add(new Notizen(8, "Balance", "h", true));
        notizens.add(new Notizen(9, "Balance", "h", true));

        //notizenData = new NotizenData();


        NotizenViewAdapter myAdapter = new NotizenViewAdapter(this,notizens);
        userRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        userRecyclerView.setAdapter(myAdapter);


        Intent intent = getIntent();
        Log.v("TAG", "isEntry= " +isNewEntry);


        if (isNewEntry == true) {

            notiz = intent.getExtras().getString("Notiz");
            Log.v("TAG", "Notiz= " + notiz);
            int spalte = notizens.size();
            ++spalte;
            notizens.add(new Notizen(spalte, notiz, "Title", false));
            isNewEntry = false;
        }


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
                                Intent intent = new Intent(NotizenAllView.this, com.example.user.work.activities.NavigationDrawer.ToDoList.class);
                                startActivity(intent);
                                break;
                            case R.id.mathe:
                                Intent mathe = new Intent(NotizenAllView.this, MatheMain.class);
                                startActivity(mathe);
                                Toast.makeText(NotizenAllView.this, "Mathe Game :O",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.notizen:
                                Intent note = new Intent(NotizenAllView.this, NotizenMain.class);
                                startActivity(note);
                                Toast.makeText(NotizenAllView.this, "Notizen :)",Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.reatzel:
                                Intent books = new Intent(NotizenAllView.this, Work.class);
                                startActivity(books);
                                Toast.makeText(NotizenAllView.this, "Quiz and Books ^^",Toast.LENGTH_SHORT).show();
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

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    public Boolean getNewEntry() {
        return isNewEntry;
    }

    public void setNewEntry(Boolean newEntry) {
        isNewEntry = newEntry;
    }
}
