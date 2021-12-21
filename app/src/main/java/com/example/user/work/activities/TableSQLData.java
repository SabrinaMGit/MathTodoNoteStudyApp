package com.example.user.work.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.work.R;
import com.example.user.work.helpers.InputValidation;
import com.example.user.work.models.User;
import com.example.user.work.sql.DatabaseHelper;

import java.util.List;

public class TableSQLData extends AppCompatActivity {

    private final AppCompatActivity activity = TableSQLData.this;
    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_tablerow);
        initViws();
        initObjects();
        getUser();
    }

    private void initViws() {
        textView = findViewById(R.id.text);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    private void getUser() {
        List<User> users = databaseHelper.getAllUser();
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(user.toString()).append("\n");
        }
        textView.setText("" + stringBuilder.toString());
    }
}
