package com.example.user.work.activities.NavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;


import com.example.user.work.animation.RevealAnimation;
import com.example.user.work.data.ToDoListTasksData;
import com.example.user.work.R;
import com.example.user.work.adapter.SetTasksAdapter;

import java.util.HashMap;
import java.util.List;

public class ToDoList_SetTasks extends AppCompatActivity {
    RevealAnimation mRevealAnimation;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    FloatingActionButton fab;
    EditText editText;
    ToDoListTasksData tasksData;
    SetTasksAdapter adapter;
    String aufgabenName;
    private int foundUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_tasksettings);

        foundUserId = getIntent().getIntExtra("ID", foundUserId);
        Log.v("TAG", "found ID ist set correct ?" + foundUserId);

        RecyclerView userRecyclerView = findViewById(R.id.recyclerview_id);
        editText = findViewById(R.id.edit_text);

        tasksData = new ToDoListTasksData();
        tasksData.getListHash().put(tasksData.getToDoListTasks().get(0), tasksData.getChild());

        final Intent intent = this.getIntent();   //get the intent to recieve the x and y coords, that you passed before
        adapter = new SetTasksAdapter(this,tasksData.getToDoListTasks(), tasksData.getChild(), foundUserId);
        userRecyclerView.setAdapter(adapter);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        CoordinatorLayout rootLayout = findViewById(R.id.root); //there you have to get the root layout of your second activity
        mRevealAnimation = new RevealAnimation(rootLayout, intent, this);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aufgabenName = editText.getText().toString();
                adapter.addToDatabase(aufgabenName);
                Intent intent = new Intent(ToDoList_SetTasks.this, ToDoList.class);
                intent.putExtra("ID", foundUserId);
                startActivity(intent);
                //onBackPressed();
            }
        });

        //userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed()
    {
        mRevealAnimation.unRevealActivity();
    }
}
