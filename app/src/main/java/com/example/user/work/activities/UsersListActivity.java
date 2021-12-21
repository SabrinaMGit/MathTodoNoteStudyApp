package com.example.user.work.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.work.R;
import com.example.user.work.adapter.UsersRecyclerAdapter;
import com.example.user.work.models.ToDoListModels;
import com.example.user.work.models.User;
import com.example.user.work.sql.DatabaseHelper;
import com.example.user.work.utilities.OnTaskCompletedCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class UsersListActivity extends AppCompatActivity {

    String emailFromIntent;
    private AppCompatActivity activity = UsersListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    ToDoListModels toDoList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        //getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = findViewById(R.id.textViewName);
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUsers = new ArrayList<>();

        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        emailFromIntent = getIntent().getStringExtra("EMAIL");

        getDataFromSQLite(new OnTaskCompletedCallback() {
            @Override
            public void onTaskCompleted() {
                //emailFromIntent
                User foundUser = findUser();
                if (foundUser != null) {
                    int id = foundUser.getId();
                    toDoList = new ToDoListModels();
                    toDoList.setId(id);
                    toDoList.setName("Erster Versuch");
                    toDoList.setNotizen("Notizen");
                    toDoList.setListe("App");
                    toDoList.setDatum("Datum");
                    toDoList.setErrinern("Jetzt");
                    toDoList.setPrioritaet("High");
                    toDoList.setHighlight("High");
                    toDoList.setAbgeschlossen("Ja");
                    databaseHelper.addToDoTask(toDoList);
                    textViewName.setText(" " + Integer.toString(id));
                }
            }
        });
    }

    /**
     * This method is to fetch all user records from SQLite
     *
     * @param callback
     */
    private void getDataFromSQLite(final OnTaskCompletedCallback callback) {
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
                usersRecyclerAdapter.notifyDataSetChanged();
                listener.onTaskCompleted();
                /*if (listUsers != null && !listUsers.isEmpty()) {

                } else {
                    Log.v("ERROR", "Es sind keine User vorhanden!");
                }*/
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
}
