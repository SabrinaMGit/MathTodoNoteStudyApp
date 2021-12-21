package com.example.user.work.models;

import android.support.annotation.DrawableRes;

public class ToDoListTask {
    private int id;
    @DrawableRes private int image;
    private String name;
    private String note;
    private boolean edit;

    public ToDoListTask(int id, @DrawableRes int image, String name, String note, boolean edit) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.note = note;
        this.edit = edit;
    }

    public ToDoListTask(ToDoListTask task) {
        this.id = task.id;
        this.image = task.image;
        this.name = task.name;
        this.note = task.note;
        this.edit = task.edit;
    }

    public int getId() {
        return id;
    }

    public int getImage() { return image; }

    public String getName() {
        return name;
    }

    public boolean getEdit() {
        return edit;
    }

    public String getNote() {
        return note;
    }
}
