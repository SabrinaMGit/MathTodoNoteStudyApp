package com.example.user.work.activities.NavigationDrawer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.user.work.R;
import com.example.user.work.activities.Work;
import com.example.user.work.adapter.ToDoListAdapter;
import com.example.user.work.animation.RevealAnimation;
import com.example.user.work.fragments.notizenFragmente.notizBottomview;
import com.example.user.work.helpers.SwipeAndDragHelper;
import com.example.user.work.models.ToDoListModels;
import com.example.user.work.models.User;
import com.example.user.work.sql.DatabaseHelper;
import com.example.user.work.utilities.OnTaskCompletedCallback;

import java.util.ArrayList;
import java.util.List;

public class ToDoList extends AppCompatActivity {

    private static final String TAG = "UserListActivity";
    FloatingActionButton fab;
    DatabaseHelper databaseHelper;
    List<ToDoListModels> toDoListModels;
    ToDoListAdapter adapter;
    String emailFromIntent;
    List<User> listUsers;
    private DrawerLayout drawer;
    private int foundUserId;
    ToDoListModels toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_drawer_main);

        emailFromIntent = getIntent().getStringExtra("EMAIL");
        foundUserId = getIntent().getIntExtra("ID", foundUserId);

        listUsers = new ArrayList<>();
        toDoListModels = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        RecyclerView userRecyclerView = findViewById(R.id.recyclerview_id);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToDoListAdapter();




        getDataFromSQLite(new OnTaskCompletedCallback() {
            @Override
            public void onTaskCompleted() {
                //emailFromIntent
                User foundUser = findUser();
                if (foundUser != null) {
                    foundUserId = foundUser.getId();
                }

        getToDoTaskFromSQLite(new OnTaskCompletedCallback() {
            @Override
            public void onTaskCompleted() {
                //ToDoListModels foundToDoList = findToDoTask(foundUserId);
                //if (foundToDoList != null) {
                    adapter.setToDoList(toDoListModels);
                }
        });
            }
        });
        SwipeAndDragHelper swipeAndDragHelper = new SwipeAndDragHelper(adapter);
        final ItemTouchHelper touchHelper = new ItemTouchHelper(swipeAndDragHelper);
        adapter.setTouchHelper(touchHelper);
        userRecyclerView.setAdapter(adapter);
        touchHelper.attachToRecyclerView(userRecyclerView);


        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startRevealActivity(v);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


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
                                Intent intent = new Intent(ToDoList.this, ToDoList.class);
                                startActivity(intent);
                                break;
                            case R.id.mathe:
                                Intent mathe = new Intent(ToDoList.this, MatheMain.class);
                                startActivity(mathe);
                                Toast.makeText(ToDoList.this, "Mathe Game", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.notizen:
                                Intent note = new Intent(ToDoList.this, notizBottomview.class);
                                startActivity(note);
                                Toast.makeText(ToDoList.this, "Notizen :)", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.reatzel:
                                Intent books = new Intent(ToDoList.this, Work.class);
                                startActivity(books);
                                Toast.makeText(ToDoList.this, "Quiz and Books ^^", Toast.LENGTH_SHORT).show();
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

    /**
     * This method is to fetch all user records from SQLite
     */
    public void getToDoTaskFromSQLite(final OnTaskCompletedCallback callback) {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            private OnTaskCompletedCallback listener = callback;

            @Override
            protected Void doInBackground(Void... params) {
                toDoListModels.clear();
                toDoListModels.addAll(databaseHelper.getAllToDoTask(foundUserId));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //adapter.notifyDataSetChanged();
                if (toDoListModels != null && !toDoListModels.isEmpty()) {
                    listener.onTaskCompleted();
                } else {
                    Log.v("ERROR", "Es sind keine User vorhanden!");
                }
            }
        }.execute();
    }

    /**
     * This method is to fetch all user records from SQLite
     *
     * @param callback
     */
    public void getDataFromSQLite(final OnTaskCompletedCallback callback) {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            private OnTaskCompletedCallback listener = callback;

            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(databaseHelper.getAllUser());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //adapter.notifyDataSetChanged();
                if (listUsers != null && !listUsers.isEmpty()) {
                    listener.onTaskCompleted();
                } else {
                    Log.v("ERROR", "Es sind keine User vorhanden!");
                }
            }
        }.execute();
    }


    public User findUser() {
        for (User user : listUsers) {
            if (user.getEmail().equals(emailFromIntent)) {
                Log.v("TASK", "user = " + user);
                return user;
            }
        }
        return null;
    }

    public ToDoListModels findToDoTask(int id) {
        for (ToDoListModels toDoListModels : toDoListModels) {
            if (toDoListModels.getId() == id) {
                Log.v("TASK", "ToDoListModels = " + toDoListModels);
                return toDoListModels;
            }
        }
        return null;
    }


    private void startRevealActivity(View v) {
        //calculates the center of the View v you are passing
        int revealX = (int) (v.getX() + v.getWidth() / 2);
        int revealY = (int) (v.getY() + v.getHeight() / 2);

        //create an intent, that launches the second activity and pass the x and y coordinates
        Intent intent = new Intent(this, ToDoList_SetTasks.class);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        intent.putExtra("ID", foundUserId);
        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }

}
