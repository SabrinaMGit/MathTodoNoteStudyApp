package com.example.user.work.data;

import android.util.Log;

import com.example.user.work.models.ToDoListModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srijith on 08-10-2017.
 */

public class ToDoListData {
    String name;
    String date;
    boolean solve;
    boolean alarm;
    String notize;
    String priorität;


    private List<ToDoListModels> toDoList = new ArrayList<ToDoListModels>() {
       /* {
            add(new ToDoList(0, "App", "Notiz", solve, date, alarm, priorität));
            add(new ToDoList(1, "Mathe", date, solve, date, alarm, priorität));
            add(new ToDoList(2, "Englisch",notize, solve, date, alarm, priorität));
            add(new ToDoList(3, "App", notize, solve, date, alarm, priorität));
            add(new ToDoList(4, "Informatik", notize, solve, date, alarm, priorität));
            add(new ToDoList(5, "Mathe",notize, solve, date, alarm, priorität));
            add(new ToDoList(6, "Englisch", notize, solve, date, alarm, priorität));
            add(new ToDoList(7, "Informatik", notize, solve, date, alarm, priorität));
            add(new ToDoList(8, "App", notize, solve, date, alarm, priorität));
            add(new ToDoList(9, "Mathe", notize, solve, date, alarm, priorität));
            add(new ToDoList(10, "Englisch", notize, solve, date, alarm, priorität));
            add(new ToDoList(11, "Informatik", notize, solve, date, alarm, priorität));
        }*/
    };

    public List<ToDoListModels> getToDoList() {
        return toDoList;
    }

    public void setDate(String date) {
        this.date = date;
        Log.v("STATE", "secondPlace = " +this.date);
    }
    public void setNotize(String notize) {
        this.notize = notize;
    }
}
